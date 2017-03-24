package com.zcbspay.platform.hz.batch.message.bean.request;

import java.io.Serializable;

public class AUT032Bean implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3584524665138211809L;
	
	/**
	 * 收费单位代码
	 */
	private String debtorUnitCode;
	/**
	 * 委托协议日期
	 */
	private String protocolDate;
	/**
	 * 下载类型
	 */
	private String downLoadType;
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(debtorUnitCode);
		buffer.append(protocolDate);
		buffer.append(downLoadType);
		return buffer.toString();
	}
	
	public String getDebtorUnitCode() {
		return debtorUnitCode;
	}
	public void setDebtorUnitCode(String debtorUnitCode) {
		this.debtorUnitCode = debtorUnitCode;
	}
	public String getProtocolDate() {
		return protocolDate;
	}
	public void setProtocolDate(String protocolDate) {
		this.protocolDate = protocolDate;
	}
	public String getDownLoadType() {
		return downLoadType;
	}
	public void setDownLoadType(String downLoadType) {
		this.downLoadType = downLoadType;
	}
	
	
	
	
}
