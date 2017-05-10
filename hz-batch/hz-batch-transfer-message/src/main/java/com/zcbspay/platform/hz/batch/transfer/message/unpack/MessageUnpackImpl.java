package com.zcbspay.platform.hz.batch.transfer.message.unpack;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
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
		com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean copyBean = null;
		try {
			switch (messageTypeEnum) {
				case AUT031:
					
					break;
				case AUT032://协议下载
					//messageBean = createAUT032(body,messageHead);
					copyBean = BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean);
					copyBean.setMessageTypeEnum(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageTypeEnum.AUT032);
					businessMessageReceiver.downloadProtocol(BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean));
					break;
				case CMT031://签到 报文体一条，长度43
					//throw new HZBatchBusinessMessageException("");
					break;
				case CMT036:

					break;
				case DLD032://代收对账文件
					messageBean = createDLD032(body,messageHead);
					copyBean = BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean);
					copyBean.setMessageTypeEnum(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageTypeEnum.DLD032);
					businessMessageReceiver.downLoadBill(BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean));
					break;
				case DLD037://代付对账文件
					messageBean =createDLD037(body,messageHead);
					copyBean = BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean);
					copyBean.setMessageTypeEnum(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageTypeEnum.DLD037);
					businessMessageReceiver.downLoadBill(BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean));
					break;
				case GMT031:
					messageBean = createCMT031(body,messageHead);
					copyBean = BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean);
					copyBean.setMessageTypeEnum(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageTypeEnum.GMT031);
					businessMessageReceiver.signInAndSignOut(BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean));
					break;
				default:
					break;
			}
		} catch (HZBatchBusinessMessageException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageBean;
	}
	@Deprecated
	private MessageBean createAUT032(String message, MessageHead messageHead) throws UnsupportedEncodingException {
		AUT032RSPBean aut032rspBean = new AUT032RSPBean();
		aut032rspBean.setMessageHead(messageHead);
		List<ProtocolDetaBean> detaList = Lists.newArrayList();
		//单位报文长度 257
		int protocolCount = message.getBytes("GBK").length/147;
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
	private MessageBean createDLD032(String message, MessageHead messageHead) throws IOException {
		DLD032RSPBean dld032rspBean = new DLD032RSPBean();
		dld032rspBean.setMessageHead(messageHead);
		dld032rspBean.setSenderCode(message.substring(0, 10).trim());
		dld032rspBean.setDownLoadDate(message.substring(10, 18).trim());
		dld032rspBean.setDownLoadType(message.substring(18, 20).trim());
		dld032rspBean.setLocalDate(message.substring(20, 28).trim());
		dld032rspBean.setLocalTime(message.substring(28, 34).trim());
		dld032rspBean.setOperator(message.substring(34, 42).trim());
		String billContent = message.substring(42).trim();
		List<CollectBillBean> billDeatList = Lists.newArrayList(); 
		byte[] billContentByes = billContent.getBytes("GBK");
		int i = 0;
		while (1==1) {
			byte[] singlebillBytes = ArrayUtils.subarray(billContentByes, i*272, (i+1)*272);
			if(singlebillBytes.length==0){
				break;
			}
			ByteArrayInputStream inputStream = new ByteArrayInputStream(singlebillBytes);
			CollectBillBean bean = new CollectBillBean();
			byte[] debtorUnitCode = new byte[10];
			inputStream.read(debtorUnitCode);
			bean.setDebtorUnitCode(new String(debtorUnitCode,"GBK").trim());
			byte[] commitDate= new byte[8];
			inputStream.read(commitDate);
			bean.setCommitDate(new String(commitDate,"GBK").trim());
			byte[] txId= new byte[8];
			inputStream.read(txId);
			bean.setTxId(new String(txId,"GBK").trim());
			byte[] debtorBranchNo= new byte[10];
			inputStream.read(debtorBranchNo);
			bean.setDebtorBranchNo(new String(debtorBranchNo,"GBK").trim());
			byte[] debtorAccountNo= new byte[30];
			inputStream.read(debtorAccountNo);
			bean.setDebtorAccountNo(new String(debtorAccountNo,"GBK").trim());
			byte[] debtorName= new byte[60];
			inputStream.read(debtorName);
			bean.setDebtorName(new String(debtorName,"GBK").trim());
			byte[] creditorBranchCode= new byte[10];
			inputStream.read(creditorBranchCode);
			bean.setCreditorBranchCode(new String(creditorBranchCode,"GBK").trim());
			byte[] creditorAccountNo= new byte[30];
			inputStream.read(creditorAccountNo);
			bean.setCreditorAccountNo(new String(creditorAccountNo,"GBK").trim());
			byte[] currencyCode= new byte[3];
			inputStream.read(currencyCode);
			bean.setCurrencyCode(new String(currencyCode,"GBK").trim());
			byte[] amount= new byte[12];
			inputStream.read(amount);
			bean.setAmount(new String(amount,"GBK").trim());
			byte[] meteringCode= new byte[20];
			inputStream.read(meteringCode);
			bean.setMeteringCode(new String(meteringCode,"GBK").trim());
			byte[] empowerCode= new byte[12];
			inputStream.read(empowerCode);
			bean.setEmpowerCode(new String(empowerCode,"GBK").trim());
			byte[] accountType= new byte[1];
			inputStream.read(accountType);
			bean.setAccountType(new String(accountType,"GBK").trim());
			byte[] voucherCode= new byte[16];
			inputStream.read(voucherCode);
			bean.setVoucherCode(new String(voucherCode,"GBK").trim());
			byte[] postscript= new byte[16];
			inputStream.read(postscript);
			bean.setPostscript(new String(postscript,"GBK").trim());
			byte[] settDate= new byte[8];
			inputStream.read(settDate);
			bean.setSettDate(new String(settDate,"GBK").trim());
			byte[] rspCode= new byte[2];
			inputStream.read(rspCode);
			bean.setRspCode(new String(rspCode,"GBK").trim());
			byte[] settFlag= new byte[1];
			inputStream.read(settFlag);
			bean.setSettFlag(new String(settFlag,"GBK").trim());
			billDeatList.add(bean);
			i++;
		}
		dld032rspBean.setBillDetaList(billDeatList);
		MessageBean messageBean = new MessageBean();
		messageBean.setMessageBean(dld032rspBean);
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
	
	private MessageBean createDLD037(String message,MessageHead messageHead) throws IOException{
		DLD037RSPBean dld037rspBean = new DLD037RSPBean();
		dld037rspBean.setMessageHead(messageHead);
		dld037rspBean.setSenderCode(message.substring(0, 10).trim());
		dld037rspBean.setDownLoadDate(message.substring(10, 18).trim());
		dld037rspBean.setDownLoadType(message.substring(18, 20).trim());
		dld037rspBean.setLocalDate(message.substring(20, 28).trim());
		dld037rspBean.setLocalTime(message.substring(28, 34).trim());
		dld037rspBean.setOperator(message.substring(34, 42).trim());
		String billContent = message.substring(42).trim();
		List<PaymentBillBean> billDeatList = Lists.newArrayList(); 
		byte[] billContentByes = billContent.getBytes("GBK");
		int i = 0;
		while (1==1) {
			byte[] singlebillBytes = ArrayUtils.subarray(billContentByes, i*257, (i+1)*257);
			if(singlebillBytes.length==0){
				break;
			}
			ByteArrayInputStream inputStream = new ByteArrayInputStream(singlebillBytes);
			PaymentBillBean bean = new PaymentBillBean();
			byte[] debtorUnitCode = new byte[10];
			inputStream.read(debtorUnitCode);
			bean.setDebtorUnitCode(new String(debtorUnitCode,"GBK").trim());
			byte[] commitDate= new byte[8];
			inputStream.read(commitDate);
			bean.setCommitDate(new String(commitDate,"GBK").trim());
			byte[] txId= new byte[8];
			inputStream.read(txId);
			bean.setTxId(new String(txId,"GBK").trim());
			byte[] creditorBranchCode= new byte[12];
			inputStream.read(creditorBranchCode);
			bean.setCreditorBranchCode(new String(creditorBranchCode,"GBK").trim());
			byte[] creditorAccountNo= new byte[30];
			inputStream.read(creditorAccountNo);
			bean.setCreditorAccountNo(new String(creditorAccountNo,"GBK").trim());
			byte[] creditorName= new byte[60];
			inputStream.read(creditorName);
			bean.setCreditorName(new String(creditorName,"GBK").trim());
			byte[] debtorBranchNo= new byte[12];
			inputStream.read(debtorBranchNo);
			bean.setDebtorBranchNo(new String(debtorBranchNo,"GBK").trim());
			byte[] debtorAccountNo= new byte[30];
			inputStream.read(debtorAccountNo);
			bean.setDebtorAccountNo(new String(debtorAccountNo,"GBK").trim());
			byte[] currencyCode= new byte[3];
			inputStream.read(currencyCode);
			bean.setCurrencyCode(new String(currencyCode,"GBK").trim());
			byte[] amount= new byte[12];
			inputStream.read(amount);
			bean.setAmount(new String(amount,"GBK").trim());
			byte[] accountType= new byte[1];
			inputStream.read(accountType);
			bean.setAccountType(new String(accountType,"GBK").trim());
			byte[] postscript= new byte[60];
			inputStream.read(postscript);
			bean.setPostscript(new String(postscript,"GBK").trim());
			byte[] settDate= new byte[8];
			inputStream.read(settDate);
			bean.setSettDate(new String(settDate,"GBK").trim());
			byte[] rspCode= new byte[2];
			inputStream.read(rspCode);
			bean.setRspCode(new String(rspCode,"GBK").trim());
			byte[] settFlag= new byte[1];
			inputStream.read(settFlag);
			bean.setSettFlag(new String(settFlag,"GBK").trim());
			billDeatList.add(bean);
			i++;
		}
		dld037rspBean.setBillDetaList(billDeatList);
		MessageBean messageBean = new MessageBean();
		messageBean .setMessageBean(dld037rspBean);
		messageBean.setMessageTypeEnum(MessageTypeEnum.DLD037);
		return messageBean;
	}
	
	
	public static void main(String[] args) {
		MessageUnpackImpl impl = new MessageUnpackImpl();
		String msg = "DLD03208331010010233100620912017051015005200000000013310062091201705100120170510153043000000013310062091201705100310015133101103016228480018543668976           guoaji                                                      3310130601213582310310001               RMB0000000001000000000             000         3                                201705101713310062091201705100310015233101103016228480018543668976           guoaji                                                      3310130601213582310310001               RMB0000000001000000000             000         3                                201705101713310062091201705100310015333101103016228480018543668976           guoaji                                                      3310130601213582310310001               RMB0000000001000000000             000         3                                201705101713310062091201705100310015433101103016228480018543668976           guoaji                                                      3310130601213582310310001               RMB0000000001000000000             000         3                                201705101713310062091201705100310015533101103016228480018543668976           guoaji                                                      3310130601213582310310001               RMB0000000001000000000             000         3                                201705101713310062091201705100310015633101103016228480018543668976           guoaji                                                      3310130601213582310310001               RMB0000000001000000000             000         3                                201705104913310062091201705100310016133101103016228480018543668976           guoaji                                                      3310130601213582310310001               RMB0000000001000000000             000         3                                20170510491";
		MessageBean messageBean = impl.unpack(msg);
		//System.out.println(messageBean.getMessageTypeEnum());
		System.out.println(JSON.toJSON(BeanCopyUtil.copyBean(com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean.class, messageBean)));
		DLD032RSPBean bean = (DLD032RSPBean) messageBean.getMessageBean();
		//System.out.println(bean.getBillDetaList().size());
		/*for(CollectBillBean billBean : bean.getBillDetaList()){
			System.out.println(billBean.getTxId());
			System.out.println(billBean.getRspCode());
		}*/
		
	/*	String json =	"{\"messageBean\":{\"billDetaList\":[{\"accountType\":\"3\",\"amount\":\"000000000100\",\"commitDate\":\"20170510\",\"creditorAccountNo\":\"213582310310001\",\"creditorBranchCode\":\"3310130601\",\"currencyCode\":\"RMB\",\"debtorAccountNo\":\"6228480018543668976\",\"debtorBranchNo\":\"3310110301\",\"debtorName\":\"guoaji\",\"debtorUnitCode\":\"3310062091\",\"empowerCode\":\"000\",\"meteringCode\":\"0000000\",\"postscript\":\"\",\"rspCode\":\"17\",\"settDate\":\"20170510\",\"settFlag\":\"1\",\"txId\":\"03100151\",\"voucherCode\":\"\"},{\"accountType\":\"3\",\"amount\":\"000000000100\",\"commitDate\":\"20170510\",\"creditorAccountNo\":\"213582310310001\",\"creditorBranchCode\":\"3310130601\",\"currencyCode\":\"RMB\",\"debtorAccountNo\":\"6228480018543668976\",\"debtorBranchNo\":\"3310110301\",\"debtorName\":\"guoaji\",\"debtorUnitCode\":\"3310062091\",\"empowerCode\":\"000\",\"meteringCode\":\"0000000\",\"postscript\":\"\",\"rspCode\":\"17\",\"settDate\":\"20170510\",\"settFlag\":\"1\",\"txId\":\"03100152\",\"voucherCode\":\"\"},{\"accountType\":\"3\",\"amount\":\"000000000100\",\"commitDate\":\"20170510\",\"creditorAccountNo\":\"213582310310001\",\"creditorBranchCode\":\"3310130601\",\"currencyCode\":\"RMB\",\"debtorAccountNo\":\"6228480018543668976\",\"debtorBranchNo\":\"3310110301\",\"debtorName\":\"guoaji\",\"debtorUnitCode\":\"3310062091\",\"empowerCode\":\"000\",\"meteringCode\":\"0000000\",\"postscript\":\"\",\"rspCode\":\"17\",\"settDate\":\"20170510\",\"settFlag\":\"1\",\"txId\":\"03100153\",\"voucherCode\":\"\"},{\"accountType\":\"3\",\"amount\":\"000000000100\",\"commitDate\":\"20170510\",\"creditorAccountNo\":\"213582310310001\",\"creditorBranchCode\":\"3310130601\",\"currencyCode\":\"RMB\",\"debtorAccountNo\":\"6228480018543668976\",\"debtorBranchNo\":\"3310110301\",\"debtorName\":\"guoaji\",\"debtorUnitCode\":\"3310062091\",\"empowerCode\":\"000\",\"meteringCode\":\"0000000\",\"postscript\":\"\",\"rspCode\":\"17\",\"settDate\":\"20170510\",\"settFlag\":\"1\",\"txId\":\"03100154\",\"voucherCode\":\"\"},{\"accountType\":\"3\",\"amount\":\"000000000100\",\"commitDate\":\"20170510\",\"creditorAccountNo\":\"213582310310001\",\"creditorBranchCode\":\"3310130601\",\"currencyCode\":\"RMB\",\"debtorAccountNo\":\"6228480018543668976\",\"debtorBranchNo\":\"3310110301\",\"debtorName\":\"guoaji\",\"debtorUnitCode\":\"3310062091\",\"empowerCode\":\"000\",\"meteringCode\":\"0000000\",\"postscript\":\"\",\"rspCode\":\"17\",\"settDate\":\"20170510\",\"settFlag\":\"1\",\"txId\":\"03100155\",\"voucherCode\":\"\"},{\"accountType\":\"3\",\"amount\":\"000000000100\",\"commitDate\":\"20170510\",\"creditorAccountNo\":\"213582310310001\",\"creditorBranchCode\":\"3310130601\",\"currencyCode\":\"RMB\",\"debtorAccountNo\":\"6228480018543668976\",\"debtorBranchNo\":\"3310110301\",\"debtorName\":\"guoaji\",\"debtorUnitCode\":\"3310062091\",\"empowerCode\":\"000\",\"meteringCode\":\"0000000\",\"postscript\":\"\",\"rspCode\":\"49\",\"settDate\":\"20170510\",\"settFlag\":\"1\",\"txId\":\"03100156\",\"voucherCode\":\"\"},{\"accountType\":\"3\",\"amount\":\"000000000100\",\"commitDate\":\"20170510\",\"creditorAccountNo\":\"213582310310001\",\"creditorBranchCode\":\"3310130601\",\"currencyCode\":\"RMB\",\"debtorAccountNo\":\"6228480018543668976\",\"debtorBranchNo\":\"3310110301\",\"debtorName\":\"guoaji\",\"debtorUnitCode\":\"3310062091\",\"empowerCode\":\"000\",\"meteringCode\":\"0000000\",\"postscript\":\"\",\"rspCode\":\"49\",\"settDate\":\"20170510\",\"settFlag\":\"1\",\"txId\":\"03100161\",\"voucherCode\":\"\"}],\"downLoadDate\":\"20170510\",\"downLoadType\":\"01\",\"localDate\":\"20170510\",\"localTime\":\"153043\",\"messageHead\":{\"localDate\":\"20170510\",\"localTime\":\"150052\",\"msgType\":\"DLD032\",\"operator\":\"00000001\",\"receiverCode\":\"3310062091\",\"rspCode\":\"00\",\"senderCode\":\"3310100102\",\"serviceType\":\"08\"},\"operator\":\"00000001\",\"senderCode\":\"3310062091\"}}";
		MessageBean messageBean = JSON.parseObject(json, MessageBean.class);
		System.out.println(messageBean.getMessageTypeEnum());*/
		
	}
}
