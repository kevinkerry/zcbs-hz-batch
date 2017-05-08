package com.zcbspay.platform.hz.batch.transfer.message.api.bean;

import java.io.Serializable;

import com.zcbspay.platform.hz.batch.transfer.message.api.enums.MessageTypeEnum;


public class MessageBean implements Serializable{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1814629421235030743L;

	private MessageTypeEnum messageTypeEnum;

    private Object messageBean;
    
    private String senderCode;

    public MessageBean() {
        super();
    }

    public MessageBean(MessageTypeEnum messageTypeEnum, Object messageBean) {
        super();
        this.messageTypeEnum = messageTypeEnum;
        this.messageBean = messageBean;
    }

    public MessageTypeEnum getMessageTypeEnum() {
        return messageTypeEnum;
    }

    public Object getMessageBean() {
        return messageBean;
    }

    public void setMessageTypeEnum(MessageTypeEnum messageTypeEnum) {
        this.messageTypeEnum = messageTypeEnum;
    }

    public void setMessageBean(Object messageBean) {
        this.messageBean = messageBean;
    }

    public MessageBean(Object messageBean, MessageTypeEnum messageTypeEnum) {
        this.messageBean = messageBean;
        this.messageTypeEnum = messageTypeEnum;
    }

	public String getSenderCode() {
		return senderCode;
	}

	public void setSenderCode(String senderCode) {
		this.senderCode = senderCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
