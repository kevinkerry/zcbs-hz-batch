package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnPaymentDetaDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnPaymentDetaDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.hz.batch.common.utils.DateUtil;
import com.zcbspay.platform.hz.batch.message.bean.CollectBillBean;
import com.zcbspay.platform.hz.batch.message.bean.PaymentBillBean;
@Repository
public class ChnPaymentDetaDAOImpl extends HibernateBaseDAOImpl<ChnPaymentDetaDO> implements
		ChnPaymentDetaDAO {
	private static final Logger logger = LoggerFactory.getLogger(ChnPaymentDetaDAOImpl.class);
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void saveBatchPaymentDeta(List<ChnPaymentDetaDO> detaList) {
		for(ChnPaymentDetaDO paymentDeta:detaList){
			saveEntity(paymentDeta);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void updatePaymentDetaResult(PaymentBillBean paymentBillBean) {
		// TODO Auto-generated method stub
		String hql = "update ChnPaymentDetaDO set rspstatus = ?,rspdate = ?,nettingdate = ? where txid = ?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, paymentBillBean.getRspCode());
		query.setParameter(1, DateUtil.getCurrentDateTime());
		query.setParameter(2, paymentBillBean.getSettDate());
		query.setParameter(3, paymentBillBean.getTxId());
		int rows = query.executeUpdate();
		logger.info("hql:{},effect rows:{}",hql,rows);
	}

}
