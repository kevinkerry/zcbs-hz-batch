package com.zcbspay.platform.hz.batch.message.bean.request;

import java.io.Serializable;

public class DLD032Bean implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6416485951260691897L;
	/**
	 * 发起方代码
	 */
	private String senderCode;
	/**
	 * 下载日期
	 */
	private String downloadDate;
	/**
	 * 下载类型
	 */
	private String downloadType;
	/**
	 * 本地日期
	 */
	private String localDate;
	/**
	 * 本地时间
	 */
	private String localTime;
	/**
	 * 操作员代码
	 */
	private String operator;
	/**
	 * 消息鉴别码
	 */
	private String msgAuthCode;
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("%1$-10s", senderCode));
		buffer.append(String.format("%1$-8s", downloadDate));
		buffer.append(String.format("%1$-2s", downloadType));
		buffer.append(String.format("%1$-8s", localDate));
		buffer.append(String.format("%1$-6s", localTime));
		buffer.append(String.format("%1$-10s", senderCode));
		buffer.append(String.format("%1$-10s", senderCode));
		buffer.append(String.format("%1$-8s", operator));
		buffer.append(String.format("%1$-8s", msgAuthCode));
		return buffer.toString();
	}
	public String signature() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("%1$-10s", senderCode));
		buffer.append(String.format("%1$-8s", downloadDate));
		buffer.append(String.format("%1$-2s", downloadType));
		buffer.append(String.format("%1$-8s", localDate));
		buffer.append(String.format("%1$-6s", localTime));
		buffer.append(String.format("%1$-10s", senderCode));
		buffer.append(String.format("%1$-10s", senderCode));
		buffer.append(String.format("%1$-8s", operator));
		return buffer.toString();
	}
	
	public String getSenderCode() {
		return senderCode;
	}

	public void setSenderCode(String senderCode) {
		this.senderCode = senderCode;
	}

	public String getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(String downloadDate) {
		this.downloadDate = downloadDate;
	}

	public String getDownloadType() {
		return downloadType;
	}

	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMsgAuthCode() {
		return msgAuthCode;
	}

	public void setMsgAuthCode(String msgAuthCode) {
		this.msgAuthCode = msgAuthCode;
	}
}
