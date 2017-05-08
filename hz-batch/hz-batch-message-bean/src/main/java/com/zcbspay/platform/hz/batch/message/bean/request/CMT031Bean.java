package com.zcbspay.platform.hz.batch.message.bean.request;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class CMT031Bean implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -767414991446800074L;
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
	/**
	 * 附言
	 */
	private String postscript="";
	/**
	 * 附加域子类
	 */
	private String additionSubclass;
	/**
	 * 附加域长度
	 */
	private String additionLength;
	/**
	 * 消息鉴别码
	 */
	private String msgAuthCode;
	/**
	 * 附加域内容
	 */
	private String additionContent="";
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(debtorUnitCode);
		buffer.append(commitDate);
		buffer.append(txId);
		buffer.append(debtorBranchNo);
		buffer.append(String.format("%1$-30s",debtorAccountNo));
		buffer.append(String.format("%1$-60s",debtorName));
		buffer.append(creditorBranchCode);
		buffer.append(String.format("%1$-30s",creditorAccountNo));
		buffer.append(currencyCode);
		buffer.append(StringUtils.leftPad(String.valueOf(amount), 12, "0"));
		buffer.append(String.format("%1$-20s",meteringCode));
		buffer.append(String.format("%1$-12s",empowerCode));
		buffer.append(accountType);
		buffer.append(String.format("%1$-16s",voucherCode));
		buffer.append(String.format("%1$-16s",postscript));
		buffer.append(additionSubclass);
		buffer.append(additionLength);
		buffer.append(msgAuthCode);
		buffer.append(String.format("%1$-46s",additionContent));
		return buffer.toString();
	}
	
	public String signature(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(debtorUnitCode);
		buffer.append(commitDate);
		buffer.append(txId);
		buffer.append(debtorBranchNo);
		buffer.append(String.format("%1$-30s",debtorAccountNo));
		buffer.append(String.format("%1$-60s",debtorName));
		buffer.append(creditorBranchCode);
		buffer.append(String.format("%1$-30s",creditorAccountNo));
		buffer.append(currencyCode);
		buffer.append(StringUtils.leftPad(String.valueOf(amount), 12, "0"));
		buffer.append(String.format("%1$-20s",meteringCode));
		buffer.append(String.format("%1$-12s",empowerCode));
		buffer.append(accountType);
		buffer.append(String.format("%1$-16s",voucherCode));
		buffer.append(String.format("%1$-16s",postscript));
		buffer.append(additionSubclass);
		buffer.append(additionLength);
		return buffer.toString();
	}
	
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
	public String getAdditionSubclass() {
		return additionSubclass;
	}
	public void setAdditionSubclass(String additionSubclass) {
		this.additionSubclass = additionSubclass;
	}
	public String getAdditionLength() {
		return additionLength;
	}
	public void setAdditionLength(String additionLength) {
		this.additionLength = additionLength;
	}
	public String getMsgAuthCode() {
		return msgAuthCode;
	}
	public void setMsgAuthCode(String msgAuthCode) {
		this.msgAuthCode = msgAuthCode;
	}
	public String getAdditionContent() {
		return additionContent;
	}
	public void setAdditionContent(String additionContent) {
		this.additionContent = additionContent;
	}
	
	
	
	
}
