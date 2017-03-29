package com.zcbspay.platform.hz.batch.business.message.api.bean;

import java.io.Serializable;



public class MessageBean implements Serializable{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6661982428462631707L;

	private MessageTypeEnum messageTypeEnum;

    private Object messageBean;

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

}
