package com.zcbspay.platform.hz.batch.fe;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zcbspay.platform.hz.batch.fe.api.MessageSender;
import com.zcbspay.platform.hz.batch.fe.enums.MessageTypeEnum;
import com.zcbspay.platform.hz.batch.transfer.message.api.unpack.MessageUnpack;

@Service
@com.alibaba.dubbo.config.annotation.Service(version="1.0",retries=0)
public class MessageSenderImpl implements MessageSender {

	@Reference(version="1.0")
	private MessageUnpack messageUnpack;
	@Override
	public String sendMessage(String message, String msgType) {
		MessageTypeEnum messageType = MessageTypeEnum.valueOf(msgType);
		switch (messageType) {
			case AUT031://代收业务委托协议签约报文AUT031
	
				break;
			case AUT032://代收业务委托协议下载报文AUT032
	
				break;
			case CMT031://借记报文CMT031 批量代收
	
				break;
			case CMT036://贷记报文CMT036 批量代付
	
				break;
			case DLD032://收费明细下载报文DLD032
	
				break;
			case DLD037://付费明细下载报文DLD037
	
				break;
			case GMT031://签到/签退报文GMT031
	
				break;
			default:
				break;
		}
		return null;
	}

}
