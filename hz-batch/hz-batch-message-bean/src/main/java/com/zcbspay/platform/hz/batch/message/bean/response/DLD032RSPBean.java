package com.zcbspay.platform.hz.batch.message.bean.response;

import java.io.Serializable;
import java.util.List;

import com.zcbspay.platform.hz.batch.message.bean.CollectBillBean;
import com.zcbspay.platform.hz.batch.message.bean.MessageHead;

public class DLD032RSPBean implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8638011139674535562L;
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
	private List<CollectBillBean> billDetaList;
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
	public List<CollectBillBean> getBillDetaList() {
		return billDetaList;
	}
	public void setBillDetaList(List<CollectBillBean> billDetaList) {
		this.billDetaList = billDetaList;
	}
	
	
}
