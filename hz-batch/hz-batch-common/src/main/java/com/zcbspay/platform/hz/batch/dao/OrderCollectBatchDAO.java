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
     * @param tid 受理批次主键
     */
    public void updateOrderToSuccess(long tid) ;
    
    /**
     * 更新批次状态为正在交易中
     * @param tn
     */
    public void updateOrderToPay(String tn);
}
