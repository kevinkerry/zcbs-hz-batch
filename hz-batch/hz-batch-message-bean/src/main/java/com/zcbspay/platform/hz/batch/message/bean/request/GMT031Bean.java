package com.zcbspay.platform.hz.batch.message.bean.request;

import java.io.Serializable;

public class GMT031Bean implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6715220294798944748L;
	/**
	 * 签到方代码
	 */
	private String signInCode;
	/**
	 * 签到日期
	 */
	private String signInDate;
	/**
	 * 签到时间
	 */
	private String signInTime;
	/**
	 * 应答码
	 */
	private String rspCode;
	/**
	 * 签到员代码
	 */
	private String operator;
	/**
	 * 签到类型
	 */
	private String signInType;
	/**
	 * 消息鉴别码
	 */
	private String msgAuthCode;
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(signInCode);
		buffer.append(signInDate);
		buffer.append(signInTime);
		buffer.append(rspCode);
		buffer.append(operator);
		buffer.append(signInType);
		buffer.append(msgAuthCode);
		return buffer.toString();
	}
	
	public String signature(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(signInCode);
		buffer.append(signInDate);
		buffer.append(signInTime);
		buffer.append(rspCode);
		buffer.append(operator);
		buffer.append(signInType);
		return buffer.toString();
	}
	
	
	
	
	
	public String getSignInCode() {
		return signInCode;
	}
	public void setSignInCode(String signInCode) {
		this.signInCode = signInCode;
	}
	public String getSignInDate() {
		return signInDate;
	}
	public void setSignInDate(String signInDate) {
		this.signInDate = signInDate;
	}
	public String getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
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
	public String getSignInType() {
		return signInType;
	}
	public void setSignInType(String signInType) {
		this.signInType = signInType;
	}
	public String getMsgAuthCode() {
		return msgAuthCode;
	}
	public void setMsgAuthCode(String msgAuthCode) {
		this.msgAuthCode = msgAuthCode;
	}
	
	
}
