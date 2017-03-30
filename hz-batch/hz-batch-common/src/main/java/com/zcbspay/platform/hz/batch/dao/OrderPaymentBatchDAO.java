package com.zcbspay.platform.hz.batch.dao;

import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;
import com.zcbspay.platform.hz.batch.pojo.OrderPaymentBatchDO;

public interface OrderPaymentBatchDAO extends BaseDAO<OrderPaymentBatchDO> {

	
	/**
	 * 通过tn获取代付批次数据
	 * @param tn
	 * @return
	 */
	public OrderPaymentBatchDO getPaymentBatchOrderByTn(String tn);
	
	/**
	 * 更新批次订单状态为支付完成
	 * @param tn
	 */
	public void updateOrderToSuccess(String tn);

	/**
	 * 更新订单状态为交易中
	 * @param tn
	 */
	public void updateOrderToPay(String tn);
}
