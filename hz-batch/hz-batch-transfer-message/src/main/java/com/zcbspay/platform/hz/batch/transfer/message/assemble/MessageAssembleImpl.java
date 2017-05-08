package com.zcbspay.platform.hz.batch.transfer.message.assemble;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zcbspay.platform.hz.batch.common.constant.Constant;
import com.zcbspay.platform.hz.batch.common.utils.DateUtil;
import com.zcbspay.platform.hz.batch.message.bean.request.AUT031Bean;
import com.zcbspay.platform.hz.batch.message.bean.request.AUT032Bean;
import com.zcbspay.platform.hz.batch.message.bean.request.CMT031Bean;
import com.zcbspay.platform.hz.batch.message.bean.request.CMT036Bean;
import com.zcbspay.platform.hz.batch.message.bean.request.DLD032Bean;
import com.zcbspay.platform.hz.batch.message.bean.request.DLD037Bean;
import com.zcbspay.platform.hz.batch.message.bean.request.GMT031Bean;
import com.zcbspay.platform.hz.batch.transfer.message.api.assemble.MessageAssemble;
import com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageBean;
import com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageHead;
import com.zcbspay.platform.hz.batch.transfer.message.api.enums.MessageTypeEnum;
import com.zcbspay.platform.hz.batch.transfer.message.exception.HZBatchTransferMessageException;
import com.zcbspay.platform.support.cipher.hardware.api.MACHardwareService;
import com.zcbspay.platform.support.cipher.software.api.MACSoftwareService;
/**
 * 
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2017年3月21日 下午4:28:18
 * @since
 */
@Service("messageAssemble")
public class MessageAssembleImpl implements MessageAssemble{

	@Reference(version="1.0",timeout=30000)
	private MACSoftwareService macSoftwareService;
	@Reference(version="1.0",timeout=30000)
	private MACHardwareService macHardwareService;
	@Override
	public MessageHead createMessageHead(MessageBean bean) throws HZBatchTransferMessageException{
		
		MessageTypeEnum messageType = bean.getMessageTypeEnum();
		//报文头赋值
		MessageHead messageHead = new MessageHead();
		switch (messageType){
			case GMT031:
				messageHead.setLocalDate(DateUtil.getCurrentDate());
				messageHead.setLocalTime(DateUtil.getCurrentTime());
				messageHead.setMsgType(messageType.name());
				messageHead.setOperator(Constant.getInstance().getOperatorCode());
				messageHead.setReceiverCode(Constant.getInstance().getReceiverCode());
				messageHead.setRspCode("99");//固定值
				messageHead.setSenderCode(bean.getSenderCode());
				messageHead.setServiceType("01");//固定值
				break;
			case CMT036:
				messageHead.setLocalDate(DateUtil.getCurrentDate());
				messageHead.setLocalTime(DateUtil.getCurrentTime());
				messageHead.setMsgType(messageType.name());
				messageHead.setOperator(Constant.getInstance().getOperatorCode());
				messageHead.setReceiverCode(Constant.getInstance().getReceiverCode());
				messageHead.setRspCode("99");//固定值
				messageHead.setSenderCode(bean.getSenderCode());
				messageHead.setServiceType("04");//固定值
				break;
			case DLD037:
				messageHead.setLocalDate(DateUtil.getCurrentDate());
				messageHead.setLocalTime(DateUtil.getCurrentTime());
				messageHead.setMsgType(messageType.name());
				messageHead.setOperator(Constant.getInstance().getOperatorCode());
				messageHead.setReceiverCode(Constant.getInstance().getReceiverCode());
				messageHead.setRspCode("99");//固定值
				messageHead.setSenderCode(bean.getSenderCode());
				messageHead.setServiceType("07");//固定值
				break;
			case CMT031:
				messageHead.setLocalDate(DateUtil.getCurrentDate());
				messageHead.setLocalTime(DateUtil.getCurrentTime());
				messageHead.setMsgType(messageType.name());
				messageHead.setOperator(Constant.getInstance().getOperatorCode());
				messageHead.setReceiverCode(Constant.getInstance().getReceiverCode());
				messageHead.setRspCode("99");//固定值
				messageHead.setSenderCode(bean.getSenderCode());
				messageHead.setServiceType("04");//固定值
				break;
			case DLD032:
				messageHead.setLocalDate(DateUtil.getCurrentDate());
				messageHead.setLocalTime(DateUtil.getCurrentTime());
				messageHead.setMsgType(messageType.name());
				messageHead.setOperator(Constant.getInstance().getOperatorCode());
				messageHead.setReceiverCode(Constant.getInstance().getReceiverCode());
				messageHead.setRspCode("99");//固定值
				messageHead.setSenderCode(bean.getSenderCode());
				messageHead.setServiceType("07");//固定值
				break;
			case AUT031:
				messageHead.setLocalDate(DateUtil.getCurrentDate());
				messageHead.setLocalTime(DateUtil.getCurrentTime());
				messageHead.setMsgType(messageType.name());
				messageHead.setOperator(Constant.getInstance().getOperatorCode());
				messageHead.setReceiverCode(Constant.getInstance().getReceiverCode());
				messageHead.setRspCode("99");//固定值
				messageHead.setSenderCode(Constant.getInstance().getSenderCode());
				messageHead.setServiceType("04");//固定值
				break;
			case AUT032:
				messageHead.setLocalDate(DateUtil.getCurrentDate());
				messageHead.setLocalTime(DateUtil.getCurrentTime());
				messageHead.setMsgType(messageType.name());
				messageHead.setOperator(Constant.getInstance().getOperatorCode());
				messageHead.setReceiverCode(Constant.getInstance().getReceiverCode());
				messageHead.setRspCode("99");//固定值
				messageHead.setSenderCode(Constant.getInstance().getSenderCode());
				messageHead.setServiceType("07");//固定值
				break;
			default:
				break;
		}
		return messageHead;//报文头字符串
	}

	@SuppressWarnings("unchecked")
	@Override
	public String signature(MessageBean bean)  throws HZBatchTransferMessageException {
		MessageTypeEnum messageType = bean.getMessageTypeEnum();
		StringBuffer msgBuffer = new StringBuffer();
		String mac = null;
		switch (messageType){
			case GMT031:
				GMT031Bean gmt031Bean =  (GMT031Bean) bean.getMessageBean();
				mac = macHardwareService.genANSI_x9_9_MAC(1, "E408C01308B5DFD1", gmt031Bean.signature());
				if(mac==null){
					gmt031Bean.setMsgAuthCode(macSoftwareService.genANSI_x9_9_MAC("3030303030303030", gmt031Bean.signature()));
				}else{
					gmt031Bean.setMsgAuthCode(mac);
				}
				return gmt031Bean.toString();
			case CMT036:
				List<CMT036Bean> cmt036BeanList =  (List<CMT036Bean>) bean.getMessageBean();
				for(CMT036Bean cmt036Bean : cmt036BeanList){
					//cmt036Bean.signature();//待签名的字符串
					mac = macHardwareService.genANSI_x9_9_MAC(1, "E408C01308B5DFD1", cmt036Bean.signature());
					if(mac==null){
						cmt036Bean.setMsgAuthCode(macSoftwareService.genANSI_x9_9_MAC("3030303030303030", cmt036Bean.signature()));
					}else{
						cmt036Bean.setMsgAuthCode(mac);
					}
					msgBuffer.append(cmt036Bean.toString());
				}
				return msgBuffer.toString();
			case DLD037:
				DLD037Bean dld037Bean =  (DLD037Bean) bean.getMessageBean();
				mac = macHardwareService.genANSI_x9_9_MAC(1, "E408C01308B5DFD1", dld037Bean.signature());
				if(mac==null){
					dld037Bean.setMsgAuthCode(macSoftwareService.genANSI_x9_9_MAC("3030303030303030", dld037Bean.signature()));
				}else{
					dld037Bean.setMsgAuthCode(mac);
				}
				return dld037Bean.toString();
			case CMT031://代收
				List<CMT031Bean> cmt031BeanList =  (List<CMT031Bean>) bean.getMessageBean();
				for(CMT031Bean cmt031Bean : cmt031BeanList){
					//cmt031Bean.signature();//待签名的字符串
					mac = macHardwareService.genANSI_x9_9_MAC(1, "E408C01308B5DFD1", cmt031Bean.signature());
					if(mac==null){
						cmt031Bean.setMsgAuthCode(macSoftwareService.genANSI_x9_9_MAC("3030303030303030", cmt031Bean.signature()));
					}else{
						cmt031Bean.setMsgAuthCode(mac);
					}
					msgBuffer.append(cmt031Bean.toString());
				}
				return msgBuffer.toString();
			case DLD032:
				DLD032Bean dld032Bean =  (DLD032Bean) bean.getMessageBean();
				mac = macHardwareService.genANSI_x9_9_MAC(1, "E408C01308B5DFD1", dld032Bean.signature());
				if(mac==null){
					dld032Bean.setMsgAuthCode(macSoftwareService.genANSI_x9_9_MAC("3030303030303030", dld032Bean.signature()));
				}else{
					dld032Bean.setMsgAuthCode(mac);
				}
				return dld032Bean.toString();
			case AUT031:
				List<AUT031Bean> aut031BeanList =  (List<AUT031Bean>) bean.getMessageBean();
				for(AUT031Bean aut031Bean : aut031BeanList){
					//aut031Bean.signature();//待签名的字符串
					//aut031Bean.setMsgAuthCode(macSoftwareService.genANSI_x9_9_MAC("", aut031Bean.signature()));
					msgBuffer.append(aut031Bean.toString());
				}
				return msgBuffer.toString();
			case AUT032:
				AUT032Bean aut032Bean =  (AUT032Bean) bean.getMessageBean();
				return aut032Bean.toString();
			default:
			
				break;
		}
		return null;
	}

	@Override
	public String assemble(MessageBean messageBean)  throws HZBatchTransferMessageException{
		return  createMessageHead(messageBean).toString()+signature(messageBean);
	}
	
	
}
