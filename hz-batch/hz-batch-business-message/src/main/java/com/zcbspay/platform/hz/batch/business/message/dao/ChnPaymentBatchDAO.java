package com.zcbspay.platform.hz.batch.business.message.dao;

import com.zcbspay.platform.hz.batch.business.message.pojo.ChnPaymentBatchDO;
import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;

public interface ChnPaymentBatchDAO extends BaseDAO<ChnPaymentBatchDO>{

	/**
	 * 保存批量代付批次数据
	 * @param chnPaymentBatch
	 * @return
	 */
	public ChnPaymentBatchDO savePaymentBatch(ChnPaymentBatchDO chnPaymentBatch);
}
