package com.zcbspay.platform.hz.batch.message.bean.request;

import java.io.Serializable;

public class AUT031Bean implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5560816097647676930L;
	/**
	 * 操作类型
	 */
	private String operateType;
	/**
	 * 收费单位代码
	 */
	private String debtorUnitCode;
	/**
	 * 委托号
	 */
	private String delegationCode;
	/**
	 * 签约日期
	 */
	private String signDate;
	/**
	 * 计量对象号码
	 */
	private String meteringCode;
	/**
	 * 计量对象名称
	 */
	private String meteringName;
	/**
	 * 付款银行号
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
	 * 付款人住址
	 */
	private String debtorAddress;
	
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(operateType);
		buffer.append(debtorUnitCode);
		buffer.append(String.format("%1$-12s", delegationCode));
		buffer.append(signDate);
		buffer.append(String.format("%1$-20s", meteringCode));
		buffer.append(String.format("%1$-60s", meteringName));
		buffer.append(debtorBranchNo);
		buffer.append(String.format("%1$-32s", debtorAccountNo));
		buffer.append(String.format("%1$-60s", debtorName));
		buffer.append(String.format("%1$-60s",debtorAddress));
		return buffer.toString();
	}
	
	public String signature(){
		
		return null;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getDebtorUnitCode() {
		return debtorUnitCode;
	}
	public void setDebtorUnitCode(String debtorUnitCode) {
		this.debtorUnitCode = debtorUnitCode;
	}
	public String getDelegationCode() {
		return delegationCode;
	}
	public void setDelegationCode(String delegationCode) {
		this.delegationCode = delegationCode;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getMeteringCode() {
		return meteringCode;
	}
	public void setMeteringCode(String meteringCode) {
		this.meteringCode = meteringCode;
	}
	public String getMeteringName() {
		return meteringName;
	}
	public void setMeteringName(String meteringName) {
		this.meteringName = meteringName;
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
	public String getDebtorAddress() {
		return debtorAddress;
	}
	public void setDebtorAddress(String debtorAddress) {
		this.debtorAddress = debtorAddress;
	}
	
	
}
