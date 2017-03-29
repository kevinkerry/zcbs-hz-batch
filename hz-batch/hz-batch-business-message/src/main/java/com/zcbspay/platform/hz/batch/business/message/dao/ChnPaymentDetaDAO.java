package com.zcbspay.platform.hz.batch.business.message.dao;

import java.util.List;

import com.zcbspay.platform.hz.batch.business.message.pojo.ChnPaymentDetaDO;
import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;
import com.zcbspay.platform.hz.batch.message.bean.CollectBillBean;
import com.zcbspay.platform.hz.batch.message.bean.PaymentBillBean;

public interface ChnPaymentDetaDAO extends BaseDAO<ChnPaymentDetaDO> {

	/**
	 * 批量保存代付明细数据
	 * @param detaList
	 */
	public void saveBatchPaymentDeta(List<ChnPaymentDetaDO> detaList);
	
	/**
	 * 更新代付交易结果
	 * @param collectBillBean
	 */
	public void updatePaymentDetaResult(PaymentBillBean paymentBillBean);
}
