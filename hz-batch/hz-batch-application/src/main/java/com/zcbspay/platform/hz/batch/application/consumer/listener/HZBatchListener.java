package com.zcbspay.platform.hz.batch.application.consumer.listener;

import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.google.common.base.Charsets;
import com.zcbspay.platform.hz.batch.application.enums.HZBatchEnum;
import com.zcbspay.platform.hz.batch.application.service.ConcentrateCacheResultService;
import com.zcbspay.platform.hz.batch.application.service.ConcentrateTradeService;
import com.zcbspay.platform.hz.batch.application.service.bean.TradeBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.ProtocolSignBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.ResultBean;


@Service("hzBatchListener")
public class HZBatchListener implements MessageListenerConcurrently{
	private static final Logger log = LoggerFactory.getLogger(HZBatchListener.class);
	private static final ResourceBundle RESOURCE = ResourceBundle.getBundle("producer_hz_batch");
	private static final String KEY = "HZBATCH:";
	
	@Autowired
	private ConcentrateTradeService concentrateTradeService;
	@Autowired
	private ConcentrateCacheResultService concentrateCacheResultService;
	
	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
			ConsumeConcurrentlyContext arg1) {
		String json = null;
		for (MessageExt msg : msgs) {
			ResultBean resultBean = null;
			if (msg.getTopic().equals(RESOURCE.getString("hz.batch.subscribe"))) {
				HZBatchEnum hzBatchEnum = HZBatchEnum.fromValue(msg.getTags());
				if(hzBatchEnum == HZBatchEnum.BATCH_COLLECT){//批量代收
					json = new String(msg.getBody(), Charsets.UTF_8);
					log.info("接收到的MSG:" + json);
					log.info("接收到的MSGID:" + msg.getMsgId());
					TradeBean tradeBean = JSON.parseObject(json,TradeBean.class);
					if (tradeBean == null) {
						log.warn("MSGID:{}JSON转换后为NULL,无法生成订单数据,原始消息数据为{}",msg.getMsgId(), json);
						break;
					}
					try {
						resultBean = concentrateTradeService.batchCollection(tradeBean);
					} catch (Throwable e) {
						// TODO: handle exception
						resultBean = new ResultBean("09", e.getMessage());
					}
					
					
				}else if(hzBatchEnum == HZBatchEnum.BATCH_PAYMENT){//批量代付
					json = new String(msg.getBody(), Charsets.UTF_8);
					log.info("接收到的MSG:" + json);
					log.info("接收到的MSGID:" + msg.getMsgId());
					TradeBean tradeBean = JSON.parseObject(json,TradeBean.class);
					if (tradeBean == null) {
						log.warn("MSGID:{}JSON转换后为NULL,无法生成订单数据,原始消息数据为{}",msg.getMsgId(), json);
						break;
					}
					try {
						resultBean = concentrateTradeService.batchPayment(tradeBean);
					} catch (Throwable e) {
						// TODO: handle exception
						resultBean = new ResultBean("09", e.getMessage());
					}
				}else if(hzBatchEnum == HZBatchEnum.DOWNLOAD_BILL){//下载对账文件
					json = new String(msg.getBody(), Charsets.UTF_8);
					log.info("接收到的MSG:" + json);
					log.info("接收到的MSGID:" + msg.getMsgId());
					TradeBean tradeBean = JSON.parseObject(json,TradeBean.class);
					if (tradeBean == null) {
						log.warn("MSGID:{}JSON转换后为NULL,无法生成订单数据,原始消息数据为{}",msg.getMsgId(), json);
						break;
					}
					try {
						resultBean = concentrateTradeService.downLoadBill(tradeBean);
					} catch (Throwable e) {
						// TODO: handle exception
						resultBean = new ResultBean("09", e.getMessage());
					}
				}else if(hzBatchEnum == HZBatchEnum.SIGNIN){
					json = new String(msg.getBody(), Charsets.UTF_8);
					log.info("接收到的MSG:" + json);
					log.info("接收到的MSGID:" + msg.getMsgId());
					TradeBean tradeBean = JSON.parseObject(json,TradeBean.class);
					if (tradeBean == null) {
						log.warn("MSGID:{}JSON转换后为NULL,无法生成订单数据,原始消息数据为{}",msg.getMsgId(), json);
						break;
					}
					try {
						resultBean = concentrateTradeService.signInOrOut(tradeBean);
					} catch (Throwable e) {
						// TODO: handle exception
						resultBean = new ResultBean("09", e.getMessage());
					}
				}else if(hzBatchEnum == HZBatchEnum.SIGNOUT){
					json = new String(msg.getBody(), Charsets.UTF_8);
					log.info("接收到的MSG:" + json);
					log.info("接收到的MSGID:" + msg.getMsgId());
					TradeBean tradeBean = JSON.parseObject(json,TradeBean.class);
					if (tradeBean == null) {
						log.warn("MSGID:{}JSON转换后为NULL,无法生成订单数据,原始消息数据为{}",msg.getMsgId(), json);
						break;
					}
					try {
						resultBean = concentrateTradeService.signInOrOut(tradeBean);
					} catch (Throwable e) {
						// TODO: handle exception
						resultBean = new ResultBean("09", e.getMessage());
					}
				}else if(hzBatchEnum == HZBatchEnum.PROTOCOL_DOWNLOAD){
					json = new String(msg.getBody(), Charsets.UTF_8);
					log.info("接收到的MSG:" + json);
					log.info("接收到的MSGID:" + msg.getMsgId());
					TradeBean tradeBean = JSON.parseObject(json,TradeBean.class);
					if (tradeBean == null) {
						log.warn("MSGID:{}JSON转换后为NULL,无法生成订单数据,原始消息数据为{}",msg.getMsgId(), json);
						break;
					}
					try {
						resultBean = concentrateTradeService.protocolDownLoad(tradeBean);
					} catch (Throwable e) {
						// TODO: handle exception
						resultBean = new ResultBean("09", e.getMessage());
					}
				}else if(hzBatchEnum == HZBatchEnum.PROTOCOLSIGN){
					json = new String(msg.getBody(), Charsets.UTF_8);
					log.info("接收到的MSG:" + json);
					log.info("接收到的MSGID:" + msg.getMsgId());
					//TradeBean tradeBean = JSON.parseObject(json,TradeBean.class);
					List<ProtocolSignBean> parseArray = JSON.parseArray(json, ProtocolSignBean.class);
					if (parseArray == null) {
						log.warn("MSGID:{}JSON转换后为NULL,无法生成订单数据,原始消息数据为{}",msg.getMsgId(), json);
						break;
					}
					try {
						resultBean = concentrateTradeService.protocolSign(parseArray);
					} catch (Throwable e) {
						// TODO: handle exception
						resultBean = new ResultBean("09", e.getMessage());
					}
				}
			}
			concentrateCacheResultService.saveInsteadPayResult(KEY, JSON.toJSONString(resultBean));
			log.info(Thread.currentThread().getName()+ " Receive New Messages: " + msgs);
		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

}
