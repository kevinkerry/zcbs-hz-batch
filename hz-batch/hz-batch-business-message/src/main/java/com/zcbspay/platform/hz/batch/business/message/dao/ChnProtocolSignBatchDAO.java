package com.zcbspay.platform.hz.batch.business.message.dao;

import com.zcbspay.platform.hz.batch.business.message.pojo.ChnProtocolSignBatchDO;
import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;

public interface ChnProtocolSignBatchDAO extends BaseDAO<ChnProtocolSignBatchDO> {

	/**
	 * 保存批量签约数据
	 * @param chnProtocolSignBatch
	 * @return
	 */
	public ChnProtocolSignBatchDO saveProtocolSignBatch(ChnProtocolSignBatchDO chnProtocolSignBatch);
}
