package com.zcbspay.platform.hz.batch.dao;

import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;
import com.zcbspay.platform.hz.batch.pojo.ChnAgreementDO;

public interface ChnAgreementDAO extends BaseDAO<ChnAgreementDO>{

	/**
	 * 通过收费单位代码和付款账号获取协议信息
	 * @param chargingunit
	 * @param debtoraccountno
	 * @return
	 */
	public ChnAgreementDO getAgreement(String chargingunit,String debtoraccountno);
	
	/**
	 * 保存协议信息
	 * @param chnAgreement
	 */
	public void saveAgreement(ChnAgreementDO chnAgreement);
	
	/**
	 * 更新协议信息
	 * @param chnAgreement
	 */
	public void updateAgreement(ChnAgreementDO chnAgreement);
}
