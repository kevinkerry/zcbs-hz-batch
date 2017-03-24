package com.zcbspay.platform.hz.batch.business.message.api.bean;

import java.io.Serializable;

public class CollectionChargesDetaBean implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2135430667032839790L;
	/**
	 * 收费单位代码
	 */
	private String debtorUnitCode;
	/**
	 * 提交日期
	 */
	private String commitDate;
	
	/**
	 * 出帐银行号
	 */
	private String debtorBranchNo;
	/**
	 * 付款人帐号
	 */
	private String debtorAccountNo;
	/**
	 * 付款人名称
	 */
	private String debtorName;
	/**
	 * 入帐银行号
	 */
	private String creditorBranchCode;
	/**
	 * 入帐帐号
	 */
	private String creditorAccountNo;
	/**
	 * 货币符
	 */
	private String currencyCode;
	/**
	 * 金额
	 */
	private String amount;
	/**
	 * 计量对象号码
	 */
	private String meteringCode;
	/**
	 * 授权号
	 */
	private String empowerCode;
	/**
	 * 支付工具类型
	 */
	private String accountType;
	/**
	 * 凭证号码
	 */
	private String voucherCode;
	
	private String summary;
	
	private String txnseqno;
	
	public String getDebtorUnitCode() {
		return debtorUnitCode;
	}
	public void setDebtorUnitCode(String debtorUnitCode) {
		this.debtorUnitCode = debtorUnitCode;
	}
	public String getCommitDate() {
		return commitDate;
	}
	public void setCommitDate(String commitDate) {
		this.commitDate = commitDate;
	}
	
	public String getDebtorBranchNo() {
		return debtorBranchNo;
	}
	public void setDebtorBranchNo(String debtorBranchNo) {
		this.debtorBranchNo = debtorBranchNo;
	}
	public String getDebtorAccountNo() {
		return debtorAccountNo;
	}
	public void setDebtorAccountNo(String debtorAccountNo) {
		this.debtorAccountNo = debtorAccountNo;
	}
	public String getDebtorName() {
		return debtorName;
	}
	public void setDebtorName(String debtorName) {
		this.debtorName = debtorName;
	}
	public String getCreditorBranchCode() {
		return creditorBranchCode;
	}
	public void setCreditorBranchCode(String creditorBranchCode) {
		this.creditorBranchCode = creditorBranchCode;
	}
	public String getCreditorAccountNo() {
		return creditorAccountNo;
	}
	public void setCreditorAccountNo(String creditorAccountNo) {
		this.creditorAccountNo = creditorAccountNo;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMeteringCode() {
		return meteringCode;
	}
	public void setMeteringCode(String meteringCode) {
		this.meteringCode = meteringCode;
	}
	public String getEmpowerCode() {
		return empowerCode;
	}
	public void setEmpowerCode(String empowerCode) {
		this.empowerCode = empowerCode;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getVoucherCode() {
		return voucherCode;
	}
	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTxnseqno() {
		return txnseqno;
	}
	public void setTxnseqno(String txnseqno) {
		this.txnseqno = txnseqno;
	}
	
	
}
