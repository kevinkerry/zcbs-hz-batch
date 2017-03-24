package com.zcbspay.platform.hz.batch.message.bean;

public class ProtocolDetaBean {
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
	 * 计量对象号码
	 */
	private String meteringCode;
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
	 * 应答码
	 */
	private String rspCode;
	
	
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
	public String getMeteringCode() {
		return meteringCode;
	}
	public void setMeteringCode(String meteringCode) {
		this.meteringCode = meteringCode;
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
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
}
