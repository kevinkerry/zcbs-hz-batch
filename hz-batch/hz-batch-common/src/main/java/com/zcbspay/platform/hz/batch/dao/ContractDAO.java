package com.zcbspay.platform.hz.batch.dao;

import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;
import com.zcbspay.platform.hz.batch.pojo.ContractDO;


public interface ContractDAO  extends BaseDAO<ContractDO>{

	/**
	 * 获取合同信息
	 * @param merchNo 委托机构号
	 * @param conractNo 合同号
	 * @return
	 */
	public ContractDO getContract(String merchNo,String conractNo);
}
