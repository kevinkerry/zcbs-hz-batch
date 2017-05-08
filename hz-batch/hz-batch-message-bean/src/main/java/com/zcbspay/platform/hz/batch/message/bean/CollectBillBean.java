package com.zcbspay.platform.hz.batch.message.bean;

import java.io.Serializable;

public class CollectBillBean implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5944610384346847200L;
	/**
	 * 收费单位代码
	 */
	private String debtorUnitCode;
	/**
	 * 提交日期
	 */
	private String commitDate;
	/**
	 * 流水号
	 */
	private String txId;
	/**
	 * 出账银行行号
	 */
	private String debtorBranchNo;
	/**
	 * 付款人账号
	 */
	private String debtorAccountNo;
	/**
	 * 付款人名称
	 */
	private String debtorName;
	/**
	 * 入账银行行号
	 */
	private String creditorBranchCode;
	/**
	 * 入账账号
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
	/**
	 * 附言
	 */
	private String postscript;
	/**
	 * 清算日
	 */
	private String settDate;
	/**
	 * 应答码
	 */
	private String rspCode;
	/**
	 * 清算标志
	 */
	private String settFlag;
	
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
	public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
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
	public String getPostscript() {
		return postscript;
	}
	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}
	public String getSettDate() {
		return settDate;
	}
	public void setSettDate(String settDate) {
		this.settDate = settDate;
	}
	public String getSettFlag() {
		return settFlag;
	}
	public void setSettFlag(String settFlag) {
		this.settFlag = settFlag;
	}
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	
	
}
