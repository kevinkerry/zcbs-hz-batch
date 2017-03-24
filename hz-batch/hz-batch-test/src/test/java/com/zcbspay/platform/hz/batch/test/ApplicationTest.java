package com.zcbspay.platform.hz.batch.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.zcbspay.platform.hz.batch.application.enums.HZBatchEnum;
import com.zcbspay.platform.hz.batch.application.interfaces.Producer;

public class ApplicationTest extends BaseTest {

	@Autowired
	@Qualifier("hzBatchSpringProducer")
	private Producer hzBatchSpringProducer;
	@Test
	public void test(){
		test_batch_collect();
	}
	
	public void test_batch_collect(){
		try {
			HZBatchEnum hzBatch = HZBatchEnum.BATCH_COLLECT;
			TradeBean tradeBean = new TradeBean();
			SendResult sendResult = hzBatchSpringProducer.sendJsonMessage(JSON.toJSONString(tradeBean), hzBatch);
			System.out.println(JSON.toJSONString(sendResult));
		} catch (MQClientException | RemotingException | InterruptedException
				| MQBrokerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
