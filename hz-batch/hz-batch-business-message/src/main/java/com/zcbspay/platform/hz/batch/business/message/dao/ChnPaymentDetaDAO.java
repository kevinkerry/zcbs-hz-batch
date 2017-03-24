package com.zcbspay.platform.hz.batch.business.message.dao;

import java.util.List;

import com.zcbspay.platform.hz.batch.business.message.pojo.ChnPaymentDetaDO;
import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;

public interface ChnPaymentDetaDAO extends BaseDAO<ChnPaymentDetaDO> {

	/**
	 * 批量保存代付明细数据
	 * @param detaList
	 */
	public void saveBatchPaymentDeta(List<ChnPaymentDetaDO> detaList);
}
