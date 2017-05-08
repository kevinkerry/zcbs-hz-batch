package com.zcbspay.platform.hz.batch.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_CONTRACT")
public class ContractDO implements java.io.Serializable {

	private static final long serialVersionUID = 7022140232957883211L;
	/** 标识 **/
	private Long tId;
	/** 合同编号 **/
	private String contractNum;
	/** 商户号 **/
	private String merchNo;
	/** 付款人名称 **/
	private String debName;
	/** 付款人账号 **/
	private String debAccNo;
	/** 付款行行号 **/
	private String debBranchCode;
	/** 收款人名称 **/
	private String credName;
	/** 收款人账号 **/
	private String credAccNo;
	/** 收款行行号 **/
	private String credBranchCode;
	/** 合同收付类型 CT00：代收协议  CT01：代付协议 CT99：代收付协议 **/
	private String contractType;
	/** 单笔金额上限(分)-付款 **/
	private String debAmoLimit;
	/** 付款累计金额限制类型00 不限 01 按年限次 02 按月限次03 总计限次 **/
	private String debTranLimitType;
	/** 累计金额上限(分) -付款 **/
	private String debAccyAmoLimit;
	/** 付款次数限制类型  00  不限 01  按年限次 02  按月限次03  总计限次 **/
	private String debTransLimitType;
	/** 付款次数限制 **/
	private Long debTransLimit;
	/** 单笔金额上限(分) -收款 **/
	private String credAmoLimit;
	/** 收款累计金额限制类型00 不限 01 按年限次 02 按月限次03 总计限次 **/
	private String credTranLimitType;
	/** 累计金额上限(分) -收款 **/
	private String credAccuAmoLimit;
	/** 收款次数限制类型00 不限 01 按年限次 02 按月限次03 总计限次 **/
	private String credTransLimitType;
	/** 付款次数限制 **/
	private Long credTransLimit;
	/** 签约日期 **/
	private String signDate;
	/** 失效日期 **/
	private String expiryDate;
	/** 00 有效 10 待初审 19初审未过 20 待复审 29复审未过 89过期失效 99撤销 **/
	private String status;
	/** 记录写入人 **/
	private Long inUser;
	/** 记录写入时间 **/
	private Date inTime;
	/** 初审人 **/
	private Long stexaUser;
	/** 初审时间 **/
	private Date stexaTime;
	/** 初审意见 **/
	private String stexaOpt;
	/** 复核人 **/
	private Long cvlexaUser;
	/** 复核时间**/
	private Date cvlexaTime;
	/** 复核意见 **/
	private String cvlexaOpt;
	/** 注销人 **/
	private Long withdrawUser;
	/** 注销时间 **/
	private String withdrawTime;
	/** 注销原因 **/
	private String withdrawOpt;
	/** 备注 **/
	private String notes;
	/** 备注 **/
	private String remarks;
	/** 附件地址 **/
	private String fileAddress;
	/** 合同注销生效日期 **/
	private String revocationDate;
	/** 批量导入时的文件名称 **/
	private String fileName;
	/** 业务种类代码 **/
	private String proprieTary;
	/** 业务类型代码  **/
	private String categoryPurpose;
	/** 批次号，接口导入时有批次号  **/
	private String batchNo;
	/**
	 * 收费代码
	 */
	private String chargeno;
	/**收费协议号**/
	private String chargeContract;
	/**付费协议号**/
	private String payContract;

	@Id
	@Column(name = "TID")
	public Long gettId() {
		return tId;
	}

	public void settId(Long tId) {
		this.tId = tId;
	}
	@Column(name = "CONTRACTNUM")
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	@Column(name = "MERCHNO")
	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}
	@Column(name = "DEBTORNAME")
	public String getDebName() {
		return debName;
	}

	public void setDebName(String debName) {
		this.debName = debName;
	}
	@Column(name = "DEBTORACCOUNTNO")
	public String getDebAccNo() {
		return debAccNo;
	}

	public void setDebAccNo(String debAccNo) {
		this.debAccNo = debAccNo;
	}
	@Column(name = "DEBTORBRANCHCODE")
	public String getDebBranchCode() {
		return debBranchCode;
	}

	public void setDebBranchCode(String debBranchCode) {
		this.debBranchCode = debBranchCode;
	}
	@Column(name = "CREDITORNAME")
	public String getCredName() {
		return credName;
	}

	public void setCredName(String credName) {
		this.credName = credName;
	}
	@Column(name = "CREDITORACCOUNTNO")
	public String getCredAccNo() {
		return credAccNo;
	}

	public void setCredAccNo(String credAccNo) {
		this.credAccNo = credAccNo;
	}
	@Column(name = "CREDITORBRANCHCODE")
	public String getCredBranchCode() {
		return credBranchCode;
	}

	public void setCredBranchCode(String credBranchCode) {
		this.credBranchCode = credBranchCode;
	}
	@Column(name = "CONTRACTTYPE")
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	@Column(name = "DEBTORAMOUNTLIMIT")
	public String getDebAmoLimit() {
		return debAmoLimit;
	}

	public void setDebAmoLimit(String debAmoLimit) {
		this.debAmoLimit = debAmoLimit;
	}
	@Column(name = "DEBTORTRANSAMTLIMITTYPE")
	public String getDebTranLimitType() {
		return debTranLimitType;
	}

	public void setDebTranLimitType(String debTranLimitType) {
		this.debTranLimitType = debTranLimitType;
	}

	@Column(name = "DEBTORACCUAMOUNTLIMIT")
	public String getDebAccyAmoLimit() {
		return debAccyAmoLimit;
	}

	public void setDebAccyAmoLimit(String debAccyAmoLimit) {
		this.debAccyAmoLimit = debAccyAmoLimit;
	}
	@Column(name = "DEBTORTRANSNUMLIMITTYPE")
	public String getDebTransLimitType() {
		return debTransLimitType;
	}

	public void setDebTransLimitType(String debTransLimitType) {
		this.debTransLimitType = debTransLimitType;
	}
	@Column(name = "DEBTORTRANSLIMIT")
	public Long getDebTransLimit() {
		return debTransLimit;
	}

	public void setDebTransLimit(Long debTransLimit) {
		this.debTransLimit = debTransLimit;
	}
	@Column(name = "CREDITORAMOUNTLIMIT")
	public String getCredAmoLimit() {
		return credAmoLimit;
	}

	public void setCredAmoLimit(String credAmoLimit) {
		this.credAmoLimit = credAmoLimit;
	}
	@Column(name = "CREDITORTRANSAMTLIMITTYPE")
	public String getCredTranLimitType() {
		return credTranLimitType;
	}

	public void setCredTranLimitType(String credTranLimitType) {
		this.credTranLimitType = credTranLimitType;
	}

	@Column(name = "CREDITORACCUAMOUNTLIMIT")
	public String getCredAccuAmoLimit() {
		return credAccuAmoLimit;
	}

	public void setCredAccuAmoLimit(String credAccuAmoLimit) {
		this.credAccuAmoLimit = credAccuAmoLimit;
	}
	@Column(name = "CREDITORTRANSNUMLIMITTYPE")
	public String getCredTransLimitType() {
		return credTransLimitType;
	}

	public void setCredTransLimitType(String credTransLimitType) {
		this.credTransLimitType = credTransLimitType;
	}
	@Column(name = "CREDITORTRANSLIMIT")
	public Long getCredTransLimit() {
		return credTransLimit;
	}

	public void setCredTransLimit(Long credTransLimit) {
		this.credTransLimit = credTransLimit;
	}
	@Column(name = "SIGNDATE")
	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}	
	@Column(name = "EXPIRYDATE")
	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "INUSER")
	public Long getInUser() {
		return inUser;
	}

	public void setInUser(Long inUser) {
		this.inUser = inUser;
	}
	@Column(name = "INTIME")
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	@Column(name = "STEXAUSER")
	public Long getStexaUser() {
		return stexaUser;
	}

	public void setStexaUser(Long stexaUser) {
		this.stexaUser = stexaUser;
	}
	@Column(name = "STEXATIME")
	public Date getStexaTime() {
		return stexaTime;
	}

	public void setStexaTime(Date stexaTime) {
		this.stexaTime = stexaTime;
	}
	@Column(name = "STEXAOPT")
	public String getStexaOpt() {
		return stexaOpt;
	}

	public void setStexaOpt(String stexaOpt) {
		this.stexaOpt = stexaOpt;
	}
	@Column(name = "CVLEXAUSER")
	public Long getCvlexaUser() {
		return cvlexaUser;
	}

	public void setCvlexaUser(Long cvlexaUser) {
		this.cvlexaUser = cvlexaUser;
	}
	@Column(name = "CVLEXATIME")
	public Date getCvlexaTime() {
		return cvlexaTime;
	}

	public void setCvlexaTime(Date cvlexaTime) {
		this.cvlexaTime = cvlexaTime;
	}
	@Column(name = "CVLEXAOPT")
	public String getCvlexaOpt() {
		return cvlexaOpt;
	}

	public void setCvlexaOpt(String cvlexaOpt) {
		this.cvlexaOpt = cvlexaOpt;
	}
	@Column(name = "WITHDRAWUSER")
	public Long getWithdrawUser() {
		return withdrawUser;
	}

	public void setWithdrawUser(Long withdrawUser) {
		this.withdrawUser = withdrawUser;
	}
	@Column(name = "WITHDRAWTIME")
	public String getWithdrawTime() {
		return withdrawTime;
	}

	public void setWithdrawTime(String withdrawTime) {
		this.withdrawTime = withdrawTime;
	}
	@Column(name = "WITHDRAWOPT")
	public String getWithdrawOpt() {
		return withdrawOpt;
	}

	public void setWithdrawOpt(String withdrawOpt) {
		this.withdrawOpt = withdrawOpt;
	}
	@Column(name = "NOTES")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "FILEADDRESS")
	public String getFileAddress() {
		return fileAddress;
	}

	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}
	@Column(name = "REVOCATIONDATE")
	public String getRevocationDate() {
		return revocationDate;
	}

	public void setRevocationDate(String revocationDate) {
		this.revocationDate = revocationDate;
	}
	@Column(name = "FILENAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name = "PROPRIETARY")
	public String getProprieTary() {
		return proprieTary;
	}
	public void setProprieTary(String proprieTary) {
		this.proprieTary = proprieTary;
	}
	@Column(name = "CATEGORYPURPOSE")
	public String getCategoryPurpose() {
		return categoryPurpose;
	}
	public void setCategoryPurpose(String categoryPurpose) {
		this.categoryPurpose = categoryPurpose;
	}
	@Column(name = "BATCHNO")
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	@Column(name = "CHARGENO")
	public String getChargeno() {
		return chargeno;
	}

	public void setChargeno(String chargeno) {
		this.chargeno = chargeno;
	}
	@Column(name = "CHARGECONTRACT")
	public String getChargeContract() {
		return chargeContract;
	}

	public void setChargeContract(String chargeContract) {
		this.chargeContract = chargeContract;
	}
	@Column(name = "PAYCONTRACT")
	public String getPayContract() {
		return payContract;
	}

	public void setPayContract(String payContract) {
		this.payContract = payContract;
	}
	
	
}
