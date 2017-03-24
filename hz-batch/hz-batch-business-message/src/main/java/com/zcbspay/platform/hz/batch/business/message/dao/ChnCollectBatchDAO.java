package com.zcbspay.platform.hz.batch.business.message.dao;

import com.zcbspay.platform.hz.batch.business.message.pojo.ChnCollectBatchDO;
import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;

public interface ChnCollectBatchDAO extends BaseDAO<ChnCollectBatchDO>{

	/**
	 * 保存代收批次数据
	 * @param chnCollectBatch
	 */
	public ChnCollectBatchDO saveCollectBatch(ChnCollectBatchDO chnCollectBatch);
}
