package com.zcbspay.platform.hz.batch.test;

import java.util.List;

import org.junit.Test;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zcbspay.platform.hz.batch.business.message.api.BusinessMessageReceiver;
import com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.MessageTypeEnum;
import com.zcbspay.platform.hz.batch.business.message.exception.HZBatchBusinessMessageException;
import com.zcbspay.platform.hz.batch.message.bean.CollectBillBean;
import com.zcbspay.platform.hz.batch.message.bean.ProtocolDetaBean;
import com.zcbspay.platform.hz.batch.message.bean.response.AUT032RSPBean;
import com.zcbspay.platform.hz.batch.message.bean.response.DLD032RSPBean;
import com.zcbspay.platform.hz.batch.message.bean.response.GMT031RSPBean;
import com.zcbspay.platform.hz.batch.transfer.message.api.assemble.MessageAssemble;
import com.zcbspay.platform.hz.batch.transfer.message.exception.HZBatchTransferMessageException;
import com.zcbspay.platform.hz.batch.utils.DateUtil;

public class BusinessMessageTest extends BaseTest {

	@Reference(version = "1.0")
	private BusinessMessageReceiver businessMessageReceiver;
	@Reference(version = "1.0")
	private MessageAssemble messageAssemble;

	@Test
	public void test() throws HZBatchTransferMessageException {
		// 签到签退
		//test_sign();
		// 下载协议
		//test_download_protocol();
		//下载对账文件
		test_downLoad_bill(MessageTypeEnum.DLD032);
	}

	private void test_downLoad_bill(MessageTypeEnum messageType) throws HZBatchTransferMessageException {
		MessageBean messageBean = new MessageBean();
		messageBean.setMessageTypeEnum(messageType);
		if(messageType==MessageTypeEnum.DLD032){
			DLD032RSPBean dld032rspBean = new DLD032RSPBean();
			com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageBean messageBean2 = new com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageBean();
			messageBean2.setMessageTypeEnum(com.zcbspay.platform.hz.batch.transfer.message.api.enums.MessageTypeEnum.DLD032);
			com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageHead messageHead = messageAssemble.createMessageHead(messageBean2);
			dld032rspBean.setMessageHead(BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.message.bean.MessageHead.class,messageHead));
			dld032rspBean.setSenderCode(messageHead.getSenderCode());
			dld032rspBean.setDownLoadDate("20170327");
			dld032rspBean.setDownLoadType("01");
			dld032rspBean.setLocalDate(DateUtil.getCurrentDate());
			dld032rspBean.setLocalTime(DateUtil.getCurrentTime());
			dld032rspBean.setOperator("00001");
			List<CollectBillBean> billDetaList = Lists.newArrayList();
			for(int i=0;i<5;i++){
				CollectBillBean collectBillBean = new CollectBillBean();
				collectBillBean.setDebtorUnitCode("2000000610");
				collectBillBean.setCommitDate("20170327");
				collectBillBean.setTxId("0310007"+(6+i));
				collectBillBean.setDebtorBranchNo("203121000010");
				collectBillBean.setDebtorAccountNo("6228480018543668979");
				collectBillBean.setDebtorName("测试账户1");
				collectBillBean.setCreditorBranchCode("203121000010");
				collectBillBean.setCreditorAccountNo("6228480018543668970");
				collectBillBean.setCurrencyCode("RMB");
				collectBillBean.setAmount("10");
				collectBillBean.setMeteringCode("01065789664");
				collectBillBean.setEmpowerCode("1234567890");
				collectBillBean.setAccountType("0");
				collectBillBean.setVoucherCode("");
				collectBillBean.setPostscript("");
				collectBillBean.setSettDate("20170329");
				collectBillBean.setRspCode("00");
				collectBillBean.setSettFlag("0");
				billDetaList.add(collectBillBean);
			}
			dld032rspBean.setBillDetaList(billDetaList);
			messageBean.setMessageBean(dld032rspBean);
		}else if(messageType==MessageTypeEnum.DLD037){
			
		}
		try {
			businessMessageReceiver.downLoadBill(messageBean);
		} catch (HZBatchBusinessMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void test_download_protocol() throws HZBatchTransferMessageException {
		MessageBean messageBean = new MessageBean();
		messageBean.setMessageTypeEnum(MessageTypeEnum.AUT032);
		AUT032RSPBean aut032rspBean = new AUT032RSPBean();
		com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageBean messageBean2 = new com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageBean();
		messageBean2
				.setMessageTypeEnum(com.zcbspay.platform.hz.batch.transfer.message.api.enums.MessageTypeEnum.AUT032);
		com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageHead messageHead = messageAssemble
				.createMessageHead(messageBean2);
		aut032rspBean.setMessageHead(BeanCopyUtil.copyBean(
				com.zcbspay.platform.hz.batch.message.bean.MessageHead.class,
				messageHead));
		List<ProtocolDetaBean> detaList = Lists.newArrayList();
		for (int i = 0; i < 3; i++) {
			ProtocolDetaBean detaBean = new ProtocolDetaBean();
			detaBean.setOperateType("2");
			detaBean.setDebtorUnitCode("1000000001");
			detaBean.setDelegationCode("91000" + i);
			detaBean.setMeteringCode("81000" + i);
			detaBean.setDebtorBranchNo("203121000010");
			detaBean.setDebtorAccountNo("622848001854366817" + i);
			detaBean.setDebtorName("测试账户0" + i);
			detaBean.setRspCode("00");
			detaList.add(detaBean);
		}
		aut032rspBean.setDetaList(detaList);
		messageBean.setMessageBean(aut032rspBean);
		try {
			businessMessageReceiver.downloadProtocol(messageBean);
		} catch (HZBatchBusinessMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test_sign() throws HZBatchTransferMessageException {
		MessageBean messageBean = new MessageBean();
		messageBean.setMessageTypeEnum(MessageTypeEnum.GMT031);
		GMT031RSPBean gmt031rspBean = new GMT031RSPBean();
		com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageBean messageBean2 = new com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageBean();
		messageBean2
				.setMessageTypeEnum(com.zcbspay.platform.hz.batch.transfer.message.api.enums.MessageTypeEnum.GMT031);
		com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageHead messageHead = messageAssemble
				.createMessageHead(messageBean2);
		gmt031rspBean.setMessageHead(BeanCopyUtil.copyBean(
				com.zcbspay.platform.hz.batch.message.bean.MessageHead.class,
				messageHead));
		gmt031rspBean.setMsgAuthCode("12345678");
		gmt031rspBean.setOperator("00000001");
		gmt031rspBean.setRspCode("99");
		gmt031rspBean.setSignInCode(messageHead.getSenderCode());
		gmt031rspBean.setSignInDate("20170329");
		gmt031rspBean.setSignInTime("000000");
		gmt031rspBean.setSignInType("1");
		System.out.println(JSON.toJSONString(gmt031rspBean));
		messageBean.setMessageBean(gmt031rspBean);
		try {
			businessMessageReceiver.signInAndSignOut(messageBean);
		} catch (HZBatchBusinessMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
