package com.zcbspay.platform.hz.batch.business.message.dao;

import java.util.List;

import com.zcbspay.platform.hz.batch.business.message.pojo.ChnCollectDetaDO;
import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;
import com.zcbspay.platform.hz.batch.message.bean.CollectBillBean;

public interface ChnCollectDetaDAO extends BaseDAO<ChnCollectDetaDO>{

	/**
	 * 保存代收明细
	 * @param chnCollectDeta
	 */
	public void saveCollectDeta(ChnCollectDetaDO chnCollectDeta);
	
	/**
	 * 批量保存代收明细
	 * @param detaList
	 */
	public void saveBatchCollectDeta(List<ChnCollectDetaDO> detaList);
	
	/**
	 * 根据对账文件更新交易结果
	 * @param collectBillBean
	 */
	public void updateCollectDetaResult(CollectBillBean collectBillBean);
}
