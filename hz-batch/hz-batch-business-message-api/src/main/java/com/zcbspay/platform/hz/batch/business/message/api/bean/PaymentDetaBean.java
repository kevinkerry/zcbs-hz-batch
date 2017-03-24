package com.zcbspay.platform.hz.batch.business.message.api.bean;

import java.io.Serializable;


public class PaymentDetaBean implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -933526667159420544L;

	/**
	 * 付费单位代码
	 */
	private String debtorUnitCode;
	/**
	 * 提交日期
	 */
	private String commitDate;
	/**
	 * 入账银行行号
	 */
	private String creditorBranchCode;
	/**
	 * 收款人账号
	 */
	private String creditorAccountNo;
	/**
	 * 收款人名称
	 */
	private String creditorName;
	
	/**
	 * 付费约定协议号
	 */
	private String debtorContract;
	/**
	 * 出账账号
	 */
	private String debtorAccountNo;
	/**
	 * 货币符
	 */
	private String currencyCode;
	/**
	 * 金额
	 */
	private String amount;
	/**
	 * 支付工具类型
	 */
	private String accountType;
	/**
	 * 附言
	 */
	private String postscript;
	
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
	public String getCreditorName() {
		return creditorName;
	}
	public void setCreditorName(String creditorName) {
		this.creditorName = creditorName;
	}
	public String getDebtorContract() {
		return debtorContract;
	}
	public void setDebtorContract(String debtorContract) {
		this.debtorContract = debtorContract;
	}
	public String getDebtorAccountNo() {
		return debtorAccountNo;
	}
	public void setDebtorAccountNo(String debtorAccountNo) {
		this.debtorAccountNo = debtorAccountNo;
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
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getPostscript() {
		return postscript;
	}
	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}
	public String getTxnseqno() {
		return txnseqno;
	}
	public void setTxnseqno(String txnseqno) {
		this.txnseqno = txnseqno;
	}
	
	
}
