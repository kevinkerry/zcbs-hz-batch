package com.zcbspay.platform.hz.batch.business.message.api.bean;

import java.io.Serializable;
import java.util.List;

public class BatchCollectionChargesBean implements Serializable{

	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5957045832599165326L;
	
	/**
	 * 代收批次
	 */
	private String batchNo;
	
	/**
	 * 交易发起商户
	 */
	private String merchNo;
	/**
	 * 总笔数
	 */
	private String totalCount;
	/**
	 * 总金额
	 */
	private String totalAmt;
	/**
	 * 代收明细
	 */
	private List<CollectionChargesDetaBean> detaList;
	
	
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getMerchNo() {
		return merchNo;
	}
	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public List<CollectionChargesDetaBean> getDetaList() {
		return detaList;
	}
	public void setDetaList(List<CollectionChargesDetaBean> detaList) {
		this.detaList = detaList;
	}
	
	
	
}
