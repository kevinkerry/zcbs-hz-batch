package com.zcbspay.platform.hz.batch.message.bean.response;

import java.io.Serializable;
import java.util.List;

import com.zcbspay.platform.hz.batch.message.bean.MessageHead;
import com.zcbspay.platform.hz.batch.message.bean.ProtocolDetaBean;

public class AUT032RSPBean implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3584524665138211809L;
	/**
	 * 报文头
	 */
	private MessageHead messageHead;
	
	private List<ProtocolDetaBean> detaList;
	
	
	public MessageHead getMessageHead() {
		return messageHead;
	}
	public void setMessageHead(MessageHead messageHead) {
		this.messageHead = messageHead;
	}
	public List<ProtocolDetaBean> getDetaList() {
		return detaList;
	}
	public void setDetaList(List<ProtocolDetaBean> detaList) {
		this.detaList = detaList;
	}
	
	
}
