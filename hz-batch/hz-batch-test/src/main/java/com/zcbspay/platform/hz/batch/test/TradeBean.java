package com.zcbspay.platform.hz.batch.test;

import java.io.Serializable;

public class TradeBean implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4650629913077511492L;
	private String tn;
	private String txnseqno;
	private String signOperateType;
	private String billDate;
	private String billOperateType;
	private String debtorUnitCode;
	private String signDate;
	private String downLoadType;
	public String getTn() {
		return tn;
	}
	public void setTn(String tn) {
		this.tn = tn;
	}
	public String getTxnseqno() {
		return txnseqno;
	}
	public void setTxnseqno(String txnseqno) {
		this.txnseqno = txnseqno;
	}
	public String getSignOperateType() {
		return signOperateType;
	}
	public void setSignOperateType(String signOperateType) {
		this.signOperateType = signOperateType;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getBillOperateType() {
		return billOperateType;
	}
	public void setBillOperateType(String billOperateType) {
		this.billOperateType = billOperateType;
	}
	public String getDebtorUnitCode() {
		return debtorUnitCode;
	}
	public void setDebtorUnitCode(String debtorUnitCode) {
		this.debtorUnitCode = debtorUnitCode;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getDownLoadType() {
		return downLoadType;
	}
	public void setDownLoadType(String downLoadType) {
		this.downLoadType = downLoadType;
	}
	
	
}
