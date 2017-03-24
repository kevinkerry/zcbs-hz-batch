package com.zcbspay.platform.hz.batch.message.bean.response;

import java.io.Serializable;
import java.util.List;

import com.zcbspay.platform.hz.batch.message.bean.MessageHead;
import com.zcbspay.platform.hz.batch.message.bean.PaymentBillBean;

public class DLD037RSPBean implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5815547095246983728L;
	/**
	 * 报文头
	 */
	private MessageHead messageHead;
	/**
	 * 发起方代码
	 */
	private String senderCode;
	/**
	 * 下载日期
	 */
	private String downLoadDate;
	/**
	 * 下载类型
	 */
	private String downLoadType;
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
	 * 对账明细数据
	 */
	private List<PaymentBillBean> billDetaList;
	public MessageHead getMessageHead() {
		return messageHead;
	}
	public void setMessageHead(MessageHead messageHead) {
		this.messageHead = messageHead;
	}
	public String getSenderCode() {
		return senderCode;
	}
	public void setSenderCode(String senderCode) {
		this.senderCode = senderCode;
	}
	public String getDownLoadDate() {
		return downLoadDate;
	}
	public void setDownLoadDate(String downLoadDate) {
		this.downLoadDate = downLoadDate;
	}
	public String getDownLoadType() {
		return downLoadType;
	}
	public void setDownLoadType(String downLoadType) {
		this.downLoadType = downLoadType;
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
	public List<PaymentBillBean> getBillDetaList() {
		return billDetaList;
	}
	public void setBillDetaList(List<PaymentBillBean> billDetaList) {
		this.billDetaList = billDetaList;
	}
	
	
}
