package com.zcbspay.platform.hz.batch.application.producer.spring;

import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.google.common.base.Charsets;
import com.zcbspay.platform.hz.batch.application.bean.ResultBean;
import com.zcbspay.platform.hz.batch.application.enums.HZBatchEnum;
import com.zcbspay.platform.hz.batch.application.interfaces.Producer;
import com.zcbspay.platform.hz.batch.application.redis.RedisFactory;

public class HZBatchSpringProducer implements Producer{
	private final static Logger logger = LoggerFactory.getLogger(HZBatchSpringProducer.class);
	private static final String KEY = "HZBATCH:";
	private static final  ResourceBundle RESOURCE = ResourceBundle.getBundle("producer_hz_batch");
	//RocketMQ消费者客户端
	private DefaultMQProducer producer;
	//主题
	private String topic;
	private String namesrvAddr;
	
	public void init() throws MQClientException{
		logger.info("【初始化HZBatchSpringProducer】");
		if(StringUtils.isEmpty(namesrvAddr)){
			namesrvAddr = RESOURCE.getString("single.namesrv.addr");
		}
		logger.info("【namesrvAddr】"+namesrvAddr);          
		producer = new DefaultMQProducer(RESOURCE.getString("hz.batch.producer.group"));
		producer.setNamesrvAddr(namesrvAddr);
		Random random = new Random();
        producer.setInstanceName(RESOURCE.getString("hz.batch.instancename")+random.nextInt(9999));
        topic = RESOURCE.getString("hz.batch.subscribe");
        logger.info("【初始化HZBatchSpringProducer结束】");
        producer.start();
	}
	
	
	@Override
	public SendResult sendJsonMessage(String message, Object tags)
			throws MQClientException, RemotingException, InterruptedException,
			MQBrokerException {
		if(producer==null){
			throw new MQClientException(-1,"SimpleOrderProducer为空");
		}
		HZBatchEnum hzRealTimeEnum = (HZBatchEnum) tags;
		Message msg = new Message(topic, hzRealTimeEnum.getCode(), message.getBytes(Charsets.UTF_8));
		SendResult sendResult = producer.send(msg);
		return sendResult;
	}

	@Override
	public void closeProducer() {
		// TODO Auto-generated method stub
		producer.shutdown();
		producer = null;
	}

	@Override
	public ResultBean queryReturnResult(SendResult sendResult) {
		logger.info("【HZBatchSpringProducer Callback receive Result message】{}",JSON.toJSONString(sendResult));
		logger.info("msgID:{}",sendResult.getMsgId());
		String json = getJsonByCycle(sendResult.getMsgId());
		logger.info("从redis中取得key【{}】值为{}",KEY+sendResult.getMsgId(),json);
		if(StringUtils.isNotEmpty(json)){
			ResultBean resultBean = JSON.parseObject(json, ResultBean.class);
			logger.info("msgID:{},结果数据:{}",sendResult.getMsgId(),JSON.toJSONString(resultBean));
			return resultBean;
		}else{
			try {
				TimeUnit.MILLISECONDS.sleep(900);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("end time {}",System.currentTimeMillis());
		return null;
	}
	private String getJsonByCycle(String msgId){
		Jedis jedis = RedisFactory.getInstance().getRedis();
		List<String> brpop = jedis.brpop(40, KEY+msgId);
		if(brpop.size()>0){
			String tn = brpop.get(1);
			if(StringUtils.isNotEmpty(tn)){
				return tn;
			}
		}
		jedis.close();
		return null;
	}
}
