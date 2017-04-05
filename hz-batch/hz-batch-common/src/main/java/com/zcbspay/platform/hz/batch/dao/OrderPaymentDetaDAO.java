package com.zcbspay.platform.hz.batch.dao;

import java.util.List;

import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;
import com.zcbspay.platform.hz.batch.pojo.OrderPaymentDetaDO;

public interface OrderPaymentDetaDAO extends BaseDAO<OrderPaymentDetaDO> {

	/**
	 * 通过批次标示获取批次明细数据集合
	 * @param batchId
	 * @return
	 */
	public List<OrderPaymentDetaDO> getDetaListByBatchtid(Long batchId);
	
	/**
	 * 更新订单状态为失败
	 * @param txnseqno 交易序列号
	 */
    public void updateOrderToFail(String txnseqno,String rspCode,String rspMsg);
    /**
     * 更新订单状态为成功
     * @param txnseqno 交易序列号
     */
    public void updateOrderToSuccess(String txnseqno,String rspCode,String rspMsg) ;

    /**
     * 更新订单状态为交易中国
     * @param batchId
     */
    public void updateOrderToPay(long batchId);
    /**
     * 更新代收订单结果
     * @param payordno
     */
    public void updateOrderResult(String payordno);
}
