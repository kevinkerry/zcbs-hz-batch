package com.zcbspay.platform.hz.batch.test;

import org.junit.Test;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.zcbspay.platform.hz.batch.transfer.message.api.assemble.MessageAssemble;
import com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageBean;
import com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageHead;
import com.zcbspay.platform.hz.batch.transfer.message.api.enums.MessageTypeEnum;

public class TransMessageTest extends BaseTest{

	@Reference(version="1.0")
	private MessageAssemble messageAssemble; 
	@Test
	public void test(){
		test_message_assemble();
	}
	
	public void test_message_assemble(){
		//String json = "{\"batchNo\":\"170315061000000048\",\"detaList\":[{\"accountType\":\"0\",\"amount\":\"10\",\"commitDate\":\"20170327\",\"creditorAccountNo\":\"6228480018543668970\",\"creditorBranchCode\":\"203121000010\",\"creditorName\":\"测试账户2\",\"currencyCode\":\"156\",\"debtorAccountNo\":\"6228480018543668979\",\"debtorContract\":\"0987654\",\"debtorUnitCode\":\"2000000610\",\"postscript\":\"test0\",\"txnseqno\":\"1703159900000055\"},{\"accountType\":\"0\",\"amount\":\"10\",\"commitDate\":\"20170327\",\"creditorAccountNo\":\"6228480018543668970\",\"creditorBranchCode\":\"203121000010\",\"creditorName\":\"测试账户2\",\"currencyCode\":\"156\",\"debtorAccountNo\":\"6228480018543668979\",\"debtorContract\":\"0987654\",\"debtorUnitCode\":\"2000000610\",\"postscript\":\"test1\",\"txnseqno\":\"1703159900000056\"},{\"accountType\":\"0\",\"amount\":\"10\",\"commitDate\":\"20170327\",\"creditorAccountNo\":\"6228480018543668970\",\"creditorBranchCode\":\"203121000010\",\"creditorName\":\"测试账户2\",\"currencyCode\":\"156\",\"debtorAccountNo\":\"6228480018543668979\",\"debtorContract\":\"0987654\",\"debtorUnitCode\":\"2000000610\",\"postscript\":\"test2\",\"txnseqno\":\"1703159900000057\"},{\"accountType\":\"0\",\"amount\":\"10\",\"commitDate\":\"20170327\",\"creditorAccountNo\":\"6228480018543668970\",\"creditorBranchCode\":\"203121000010\",\"creditorName\":\"测试账户2\",\"currencyCode\":\"156\",\"debtorAccountNo\":\"6228480018543668979\",\"debtorContract\":\"0987654\",\"debtorUnitCode\":\"2000000610\",\"postscript\":\"test3\",\"txnseqno\":\"1703159900000058\"},{\"accountType\":\"0\",\"amount\":\"10\",\"commitDate\":\"20170327\",\"creditorAccountNo\":\"6228480018543668970\",\"creditorBranchCode\":\"203121000010\",\"creditorName\":\"测试账户2\",\"currencyCode\":\"156\",\"debtorAccountNo\":\"6228480018543668979\",\"debtorContract\":\"0987654\",\"debtorUnitCode\":\"2000000610\",\"postscript\":\"test4\",\"txnseqno\":\"1703159900000059\"}],\"merchNo\":\"200000000000610\",\"totalAmt\":\"50\",\"totalCount\":\"5\"}";	
		//JSON.parseObject(json,)
		
		MessageBean messageBean = new MessageBean();
		messageBean.setMessageTypeEnum(MessageTypeEnum.CMT036);
		MessageHead messageHead = messageAssemble.createMessageHead(messageBean);
		System.out.println(JSON.toJSONString(messageHead));
	}
}
