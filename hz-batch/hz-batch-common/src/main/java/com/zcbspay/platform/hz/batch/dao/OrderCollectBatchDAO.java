package com.zcbspay.platform.hz.batch.dao;

import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;
import com.zcbspay.platform.hz.batch.pojo.OrderCollectBatchDO;

public interface OrderCollectBatchDAO extends BaseDAO<OrderCollectBatchDO> {

	/**
	 * 通过tn获取代收批次数据
	 * @param tn
	 * @return
	 */
	public OrderCollectBatchDO getCollectBatchOrderByTn(String tn);
	
	/**
     * 更新订单状态为成功
     * @param tn 受理批次号
     */
    public void updateOrderToSuccess(String tn) ;
}
