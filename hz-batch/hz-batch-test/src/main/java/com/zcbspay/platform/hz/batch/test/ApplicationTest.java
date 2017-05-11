package com.zcbspay.platform.hz.batch.test;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.google.common.collect.Lists;
import com.zcbspay.platform.hz.batch.application.bean.ResultBean;
import com.zcbspay.platform.hz.batch.application.enums.HZBatchEnum;
import com.zcbspay.platform.hz.batch.application.interfaces.Producer;
import com.zcbspay.platform.hz.batch.business.message.api.bean.ProtocolSignBean;

public class ApplicationTest extends BaseTest {
	
	@Autowired
	@Qualifier("hzBatchSpringProducer")
	private Producer hzBatchSpringProducer;
	@Test
	public void test(){
		ResourceBundle RESOURCE = ResourceBundle.getBundle("hz_batch_test");
		String collect_tn = RESOURCE.getString("collect_tn");
		String payment_tn = RESOURCE.getString("payment_tn");
		String download_bill_date = RESOURCE.getString("download_bill_date");
		String sign_in_out = RESOURCE.getString("sign_in_out");
		String merchno = RESOURCE.getString("debtorunitcode");
		String billType = RESOURCE.getString("billtype");
		if(StringUtils.isNotEmpty(collect_tn)){
			//批量代收
			test_batch_collect(collect_tn);
		}
		if(StringUtils.isNotEmpty(payment_tn)){
			//批量代付
			test_batch_payment(payment_tn);
		}
		if(StringUtils.isNotEmpty(download_bill_date)){
			//下载对账文件
			if("01".equals(billType)){
				test_download_bill(download_bill_date,"01",merchno);
			}
			if("02".equals(billType)){
				test_download_bill(download_bill_date,"02",merchno);
			}
			
			
		}
		if(StringUtils.isNotEmpty(sign_in_out)){
			test_sign_in_out(sign_in_out);
		}
		
		//签到签退
		//test_sign_in_out("01");
		//协议下载
		//test_protocol_downLoad("1000000001","20170207","1");
		//协议签约
		//test_protocol_sign();
	}
	
	private void test_protocol_sign() {
		try {
			ProtocolSignBean protocolSignBean = new ProtocolSignBean();
			List<ProtocolSignBean> protocolList = Lists.newArrayList();
			protocolSignBean.setOperateType("1");
			protocolSignBean.setDebtorUnitCode("1000000001");
			protocolSignBean.setDelegationCode("000000000001");
			protocolSignBean.setSignDate("20170325");
			protocolSignBean.setMeteringCode("00001");
			protocolSignBean.setMeteringName("TEST");
			protocolSignBean.setDebtorBranchNo("203121000010");
			protocolSignBean.setDebtorAccountNo("6228480018543668979");
			protocolSignBean.setDebtorName("测试账户1");
			protocolSignBean.setDebtorAddress("北京");
			protocolList.add(protocolSignBean);
			HZBatchEnum hzBatch = HZBatchEnum.PROTOCOLSIGN;
			SendResult sendResult = hzBatchSpringProducer.sendJsonMessage(JSON.toJSONString(protocolList), hzBatch);
			System.out.println(JSON.toJSONString(sendResult));
		} catch (MQClientException | RemotingException | InterruptedException
				| MQBrokerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void test_protocol_downLoad(String debtorunitcode, String signdate,
			String downloadtype) {
		try {
			HZBatchEnum hzBatch = HZBatchEnum.PROTOCOL_DOWNLOAD;
			TradeBean tradeBean = new TradeBean();
			tradeBean.setDebtorUnitCode(debtorunitcode);
			tradeBean.setSignDate(signdate);
			tradeBean.setDownLoadType(downloadtype);
			SendResult sendResult = hzBatchSpringProducer.sendJsonMessage(JSON.toJSONString(tradeBean), hzBatch);
			
			ResultBean queryReturnResult = hzBatchSpringProducer.queryReturnResult(sendResult);
			System.out.println(JSON.toJSONString(queryReturnResult));
			
		} catch (MQClientException | RemotingException | InterruptedException
				| MQBrokerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void test_sign_in_out(String signType) {
		// TODO Auto-generated method stub
		try {
			HZBatchEnum hzBatch = HZBatchEnum.SIGNIN;
			TradeBean tradeBean = new TradeBean();
			tradeBean.setSignOperateType(signType);
			SendResult sendResult = hzBatchSpringProducer.sendJsonMessage(JSON.toJSONString(tradeBean), hzBatch);
			System.out.println(JSON.toJSONString(sendResult));
			ResultBean queryReturnResult = hzBatchSpringProducer.queryReturnResult(sendResult);
			System.out.println(JSON.toJSONString(queryReturnResult));
		} catch (MQClientException | RemotingException | InterruptedException
				| MQBrokerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void test_download_bill(String billdate, String billtype,String merchno) {
		// TODO Auto-generated method stub
		try {
			HZBatchEnum hzBatch = HZBatchEnum.DOWNLOAD_BILL;
			TradeBean tradeBean = new TradeBean();
			tradeBean.setBillDate(billdate);
			tradeBean.setBillOperateType(billtype);
			tradeBean.setDebtorUnitCode(merchno);
			SendResult sendResult = hzBatchSpringProducer.sendJsonMessage(JSON.toJSONString(tradeBean), hzBatch);
			System.out.println(JSON.toJSONString(sendResult));
		} catch (MQClientException | RemotingException | InterruptedException
				| MQBrokerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test_batch_collect(String tn){
		try {
			HZBatchEnum hzBatch = HZBatchEnum.BATCH_COLLECT;
			TradeBean tradeBean = new TradeBean();
			tradeBean.setTn(tn);
			SendResult sendResult = hzBatchSpringProducer.sendJsonMessage(JSON.toJSONString(tradeBean), hzBatch);
			System.out.println(JSON.toJSONString(sendResult));
			ResultBean queryReturnResult = hzBatchSpringProducer.queryReturnResult(sendResult);
			System.out.println(JSON.toJSONString(queryReturnResult));
		} catch (MQClientException | RemotingException | InterruptedException
				| MQBrokerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test_batch_payment(String tn){
		try {
			HZBatchEnum hzBatch = HZBatchEnum.BATCH_PAYMENT;
			TradeBean tradeBean = new TradeBean();
			tradeBean.setTn(tn);
			SendResult sendResult = hzBatchSpringProducer.sendJsonMessage(JSON.toJSONString(tradeBean), hzBatch);
			System.out.println(JSON.toJSONString(sendResult));
			ResultBean queryReturnResult = hzBatchSpringProducer.queryReturnResult(sendResult);
			System.out.println(JSON.toJSONString(queryReturnResult));
		} catch (MQClientException | RemotingException | InterruptedException
				| MQBrokerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println("3310062091201705080310005133101103016228480018543668976           郭佳                                                          3310130601213582310310001               RMB000000000100                                0                null            Y0000".length());
	}
}
