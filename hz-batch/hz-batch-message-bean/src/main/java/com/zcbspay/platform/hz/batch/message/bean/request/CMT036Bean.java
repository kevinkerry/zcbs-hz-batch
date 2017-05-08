package com.zcbspay.platform.hz.batch.message.bean.request;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class CMT036Bean implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -866797195507305431L;
	/**
	 * 付费单位代码
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
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("%1$-10s", debtorUnitCode));
		buffer.append(commitDate);
		buffer.append(String.format("%1$-8s", txId));
		buffer.append(String.format("%1$-12s", creditorBranchCode));
		buffer.append(String.format("%1$-30s", creditorAccountNo));
		buffer.append(String.format("%1$-60s", creditorName));
		buffer.append(String.format("%1$-12s", debtorContract));
		buffer.append(String.format("%1$-30s", debtorAccountNo));
		buffer.append(String.format("%1$-3s", currencyCode));
		buffer.append(StringUtils.leftPad(String.valueOf(amount), 12, "0"));
		buffer.append(accountType);
		buffer.append(String.format("%1$-60s", postscript));
		buffer.append(additionSubclass);
		buffer.append(additionLength);
		buffer.append(msgAuthCode);
		return buffer.toString();
	}
	public String signature() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("%1$-10s", debtorUnitCode));
		buffer.append(commitDate);
		buffer.append(String.format("%1$-8s", txId));
		buffer.append(String.format("%1$-12s", creditorBranchCode));
		buffer.append(String.format("%1$-30s", creditorAccountNo));
		buffer.append(String.format("%1$-60s", creditorName));
		buffer.append(String.format("%1$-12s", debtorContract));
		buffer.append(String.format("%1$-30s", debtorAccountNo));
		buffer.append(String.format("%1$-3s", currencyCode));
		buffer.append(StringUtils.leftPad(String.valueOf(amount), 12, "0"));
		buffer.append(accountType);
		buffer.append(String.format("%1$-60s", postscript));
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
	
	
	
	
	public static void main(String[] args) {
		System.out.println(StringUtils.leftPad(String.valueOf("123"), 12, "0"));
	}
}
