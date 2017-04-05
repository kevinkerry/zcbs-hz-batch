package com.zcbspay.platform.hz.batch.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.hz.batch.common.utils.DateUtil;
import com.zcbspay.platform.hz.batch.dao.OrderPaymentBatchDAO;
import com.zcbspay.platform.hz.batch.pojo.OrderPaymentBatchDO;
@Repository
public class OrderPaymentBatchDAOImpl extends HibernateBaseDAOImpl<OrderPaymentBatchDO> implements
		OrderPaymentBatchDAO {
	private static final Logger log = LoggerFactory.getLogger(OrderPaymentBatchDAOImpl.class);
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public OrderPaymentBatchDO getPaymentBatchOrderByTn(String tn) {
		Criteria criteria = getSession().createCriteria(OrderPaymentBatchDO.class);
		criteria.add(Restrictions.eq("tn", tn));
		OrderPaymentBatchDO uniqueResult = (OrderPaymentBatchDO) criteria.uniqueResult();
		return uniqueResult;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void updateOrderToSuccess(long tid) {
		// TODO Auto-generated method stub
		String hql = "update OrderPaymentBatchDO set status = ? , orderfinshtime = ? where tid = ? ";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, "00");
		query.setParameter(1, DateUtil.getCurrentDateTime());
		query.setParameter(2, tid);
		int rows = query.executeUpdate();
		log.info("updateOrderToSuccess() effect rows:"+rows);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void updateOrderToPay(String tn) {
		// TODO Auto-generated method stub
		String hql = "update OrderPaymentBatchDO set status = ? where tn = ? ";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, "02");
		query.setParameter(1, tn);
		int rows = query.executeUpdate();
		log.info("updateOrderToPay() effect rows:"+rows);
	}
}
