package com.zcbspay.platform.hz.batch.transfer.message.unpack;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zcbspay.platform.hz.batch.business.message.api.BusinessMessageReceiver;
import com.zcbspay.platform.hz.batch.business.message.exception.HZBatchBusinessMessageException;
import com.zcbspay.platform.hz.batch.common.utils.BeanCopyUtil;
import com.zcbspay.platform.hz.batch.message.bean.CollectBillBean;
import com.zcbspay.platform.hz.batch.message.bean.MessageHead;
import com.zcbspay.platform.hz.batch.message.bean.PaymentBillBean;
import com.zcbspay.platform.hz.batch.message.bean.ProtocolDetaBean;
import com.zcbspay.platform.hz.batch.message.bean.response.AUT032RSPBean;
import com.zcbspay.platform.hz.batch.message.bean.response.DLD032RSPBean;
import com.zcbspay.platform.hz.batch.message.bean.response.DLD037RSPBean;
import com.zcbspay.platform.hz.batch.message.bean.response.GMT031RSPBean;
import com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageBean;
import com.zcbspay.platform.hz.batch.transfer.message.api.enums.MessageTypeEnum;
import com.zcbspay.platform.hz.batch.transfer.message.api.unpack.MessageUnpack;

@Service("messageUnpack")
public class MessageUnpackImpl implements MessageUnpack {

	private static final Logger logger = LoggerFactory.getLogger(MessageUnpackImpl.class);
	@Reference(version="1.0")
	private BusinessMessageReceiver businessMessageReceiver; 
	@Override
	public MessageBean unpack(String message) {
		MessageBean messageBean = null;
		//报文头长52
		String head = message.substring(0,52);
		String body = message.substring(52);
		MessageHead messageHead = new MessageHead(head);
		logger.info("MessageHead:"+JSON.toJSONString(messageHead));
		MessageTypeEnum messageTypeEnum = MessageTypeEnum.valueOf(messageHead.getMsgType());
		try {
			switch (messageTypeEnum) {
				case AUT031:
					
					break;
				case AUT032://协议下载
					messageBean = createAUT032(body,messageHead);
					businessMessageReceiver.downloadProtocol(BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean));
					break;
				case CMT031://签到 报文体一条，长度43
					
					break;
				case CMT036:

					break;
				case DLD032://代收对账文件
					messageBean = createDLD032(body,messageHead);
					businessMessageReceiver.downLoadBill(BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean));
					break;
				case DLD037://代付对账文件
					messageBean =createDLD037(body,messageHead);
					businessMessageReceiver.downLoadBill(BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean));
					break;
				case GMT031:
					messageBean = createCMT031(body,messageHead);
					businessMessageReceiver.signInAndSignOut(BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean));
					break;
				default:
					break;
			}
		} catch (HZBatchBusinessMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageBean;
	}
	private MessageBean createAUT032(String message, MessageHead messageHead) {
		AUT032RSPBean aut032rspBean = new AUT032RSPBean();
		aut032rspBean.setMessageHead(messageHead);
		List<ProtocolDetaBean> detaList = Lists.newArrayList();
		//单位报文长度 257
		int protocolCount = message.length()/147;
		for(int i = 0;i < protocolCount;i++){
			String protocolDetaContent = message.substring(i*147,(i+1)*147);
			ProtocolDetaBean bean = new ProtocolDetaBean();
			bean.setOperateType(protocolDetaContent.substring(0,1));
			bean.setDebtorUnitCode(protocolDetaContent.substring(1,11));
			bean.setDelegationCode(protocolDetaContent.substring(11,23));
			bean.setMeteringCode(protocolDetaContent.substring(23,43));
			bean.setDebtorBranchNo(protocolDetaContent.substring(43,53));
			bean.setDebtorAccountNo(protocolDetaContent.substring(53,85));
			bean.setDebtorName(protocolDetaContent.substring(85,145));
			bean.setRspCode(protocolDetaContent.substring(145));
			detaList.add(bean);
		}
		aut032rspBean.setDetaList(detaList);;
		MessageBean messageBean = new MessageBean();
		messageBean.setMessageBean(aut032rspBean);
		messageBean.setMessageTypeEnum(MessageTypeEnum.AUT032);
		return messageBean;
	}
	private MessageBean createDLD032(String message, MessageHead messageHead) {
		DLD032RSPBean dld032rspBean = new DLD032RSPBean();
		dld032rspBean.setMessageHead(messageHead);
		dld032rspBean.setSenderCode(message.substring(0, 10));
		dld032rspBean.setDownLoadDate(message.substring(10, 18));
		dld032rspBean.setDownLoadType(message.substring(18, 20));
		dld032rspBean.setLocalDate(message.substring(20, 28));
		dld032rspBean.setLocalTime(message.substring(28, 34));
		dld032rspBean.setOperator(message.substring(34, 42));
		String billContent = message.substring(42);
		//单位报文长度 257
		int billCount = billContent.length()/257;
		List<CollectBillBean> billDeatList = Lists.newArrayList(); 
		for(int i = 0;i < billCount;i++){
			String billDetaContent = billContent.substring(i*257,(i+1)*257);
			CollectBillBean bean = new CollectBillBean();
			bean.setDebtorUnitCode(billDetaContent.substring(0,10));
			bean.setCommitDate(billDetaContent.substring(10,18));
			bean.setTxId(billDetaContent.substring(18,26));
			bean.setDebtorBranchNo(billDetaContent.substring(26,36));
			bean.setDebtorAccountNo(billDetaContent.substring(36,66));
			bean.setDebtorName(billDetaContent.substring(66,126));
			bean.setCreditorBranchCode(billDetaContent.substring(126,136));
			bean.setCreditorAccountNo(billDetaContent.substring(136,166));
			bean.setCurrencyCode(billDetaContent.substring(166,169));
			bean.setAmount(billDetaContent.substring(169,181));
			bean.setMeteringCode(billDetaContent.substring(181,201));
			bean.setEmpowerCode(billDetaContent.substring(201,213));
			bean.setAccountType(billDetaContent.substring(213,214));
			bean.setVoucherCode(billDetaContent.substring(214,230));
			bean.setPostscript(billDetaContent.substring(230,246));
			bean.setSettDate(billDetaContent.substring(246,254));
			bean.setRspCode(billDetaContent.substring(254,256));
			bean.setSettFlag(billDetaContent.substring(256));
			billDeatList.add(bean);
		}
		dld032rspBean.setBillDetaList(billDeatList);
		MessageBean messageBean = new MessageBean();
		messageBean .setMessageBean(dld032rspBean);
		messageBean.setMessageTypeEnum(MessageTypeEnum.DLD032);
		return messageBean;
	}
	private MessageBean createCMT031(String message,MessageHead messageHead){
		GMT031RSPBean gmt031rspBean = new GMT031RSPBean();
		gmt031rspBean.setSignInCode(message.substring(0,10));
		gmt031rspBean.setSignInDate(message.substring(10,18));
		gmt031rspBean.setSignInTime(message.substring(18,24));
		gmt031rspBean.setRspCode(message.substring(24,26));
		gmt031rspBean.setOperator(message.substring(26,34));
		gmt031rspBean.setSignInType(message.substring(34,35));
		gmt031rspBean.setMsgAuthCode(message.substring(35));
		gmt031rspBean.setMessageHead(messageHead);
		MessageBean messageBean = new MessageBean();
		messageBean .setMessageBean(gmt031rspBean);
		messageBean.setMessageTypeEnum(MessageTypeEnum.CMT031);
		return messageBean;
	}
	
	private MessageBean createDLD037(String message,MessageHead messageHead){
		DLD037RSPBean dld037rspBean = new DLD037RSPBean();
		dld037rspBean.setMessageHead(messageHead);
		dld037rspBean.setSenderCode(message.substring(0, 10));
		dld037rspBean.setDownLoadDate(message.substring(10, 18));
		dld037rspBean.setDownLoadType(message.substring(18, 20));
		dld037rspBean.setLocalDate(message.substring(20, 28));
		dld037rspBean.setLocalTime(message.substring(28, 34));
		dld037rspBean.setOperator(message.substring(34, 42));
		String billContent = message.substring(42);
		//单位报文长度 257
		int billCount = billContent.length()/257;
		List<PaymentBillBean> billDeatList = Lists.newArrayList(); 
		for(int i = 0;i < billCount;i++){
			String billDetaContent = billContent.substring(i*257,(i+1)*257);
			PaymentBillBean bean = new PaymentBillBean(); 
			bean.setDebtorUnitCode(billDetaContent.substring(0,10));
			bean.setCommitDate(billDetaContent.substring(10,18));
			bean.setTxId(billDetaContent.substring(18,26));
			bean.setCreditorBranchCode(billDetaContent.substring(26,38));
			bean.setCreditorAccountNo(billDetaContent.substring(38,68));
			bean.setCreditorName(billDetaContent.substring(68,128));
			bean.setDebtorBranchNo(billDetaContent.substring(128,140));
			bean.setDebtorAccountNo(billDetaContent.substring(140,170));
			bean.setCurrencyCode(billDetaContent.substring(170,173));
			bean.setAmount(billDetaContent.substring(173,185));
			bean.setAccountType(billDetaContent.substring(185,186));
			bean.setPostscript(billDetaContent.substring(186,246));
			bean.setSettDate(billDetaContent.substring(246,254));
			bean.setRspCode(billDetaContent.substring(254,256));
			bean.setSettFlag(billDetaContent.substring(256));
			billDeatList.add(bean);
		}
		dld037rspBean.setBillDetaList(billDeatList);
		MessageBean messageBean = new MessageBean();
		messageBean .setMessageBean(dld037rspBean);
		messageBean.setMessageTypeEnum(MessageTypeEnum.DLD037);
		return messageBean;
	}
	
}
