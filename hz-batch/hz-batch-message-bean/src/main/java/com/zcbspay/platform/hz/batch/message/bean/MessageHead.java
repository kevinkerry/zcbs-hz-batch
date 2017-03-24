package com.zcbspay.platform.hz.batch.message.bean;

public class MessageHead {

	/**
	 * 报文类型
	 */
	private String msgType;
	/**
	 * 服务类型
	 */
	private String serviceType;
	/**
	 * 发起方代码
	 */
	private String senderCode;
	/**
	 * 接收方代码
	 */
	private String receiverCode;
	/**
	 * 本地日期
	 */
	private String localDate;
	/**
	 * 本地时间
	 */
	private String localTime;
	/**
	 * 回应码
	 */
	private String rspCode;
	/**
	 * 操作员代码
	 */
	private String operator;
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getSenderCode() {
		return senderCode;
	}
	public void setSenderCode(String senderCode) {
		this.senderCode = senderCode;
	}
	public String getReceiverCode() {
		return receiverCode;
	}
	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}
	public String getLocalDate() {
		return localDate;
	}
	public void setLocalDate(String localDate) {
		this.localDate = localDate;
	}
	public String getLocalTime() {
		return localTime;
	}
	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(msgType);
		buffer.append(serviceType);
		buffer.append(senderCode);
		buffer.append(receiverCode);
		buffer.append(localDate);
		buffer.append(localTime);
		buffer.append(rspCode);
		buffer.append(operator);
		return buffer.toString();
	}
	public MessageHead() {
		super();
	}
	public MessageHead(String headMsg) {
		super();
		
		this.msgType = headMsg.substring(0,6);
		this.serviceType = headMsg.substring(6,8);
		this.senderCode = headMsg.substring(8,18);
		this.receiverCode = headMsg.substring(18,28);
		this.localDate = headMsg.substring(28,36);
		this.localTime = headMsg.substring(36,42);
		this.rspCode = headMsg.substring(42,44);
		this.operator = headMsg.substring(44);
	}
	
	
}
