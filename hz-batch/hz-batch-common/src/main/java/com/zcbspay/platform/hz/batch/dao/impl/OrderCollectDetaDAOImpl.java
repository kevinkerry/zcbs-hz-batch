package com.zcbspay.platform.hz.batch.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.hz.batch.dao.OrderCollectBatchDAO;
import com.zcbspay.platform.hz.batch.dao.OrderCollectDetaDAO;
import com.zcbspay.platform.hz.batch.dao.TxnsLogDAO;
import com.zcbspay.platform.hz.batch.pojo.OrderCollectDetaDO;
import com.zcbspay.platform.hz.batch.pojo.TxnsLogDO;

@Repository
public class OrderCollectDetaDAOImpl extends HibernateBaseDAOImpl<OrderCollectDetaDO> implements OrderCollectDetaDAO {
	private static final Logger log = LoggerFactory.getLogger(OrderCollectDetaDAOImpl.class);
	@Autowired
	private TxnsLogDAO txnsLogDAO;
	@Autowired
	private OrderCollectBatchDAO orderCollectBatchDAO;
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public List<OrderCollectDetaDO> getDetaListByBatchtid(Long batchId){
		Criteria criteria = getSession().createCriteria(OrderCollectDetaDO.class);
		criteria.add(Restrictions.eq("batchtid", batchId));
		return criteria.list();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateOrderToFail(String txnseqno,String rspCode,String rspMsg) {
		String hql = "update OrderCollectDetaDO set status = ? , respcode = ? , respmsg = ? where relatetradetxn = ? ";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setString(0, "03");
		query.setString(1, rspCode);
		query.setString(2, rspMsg);
		query.setString(3, txnseqno);
		int rows = query.executeUpdate();
		log.info("updateOrderToFail() effect rows:"+rows);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateOrderToSuccess(String txnseqno,String rspCode,String rspMsg) {
		String hql = "update OrderCollectDetaDO set status = ? , respcode = ? , respmsg = ? where relatetradetxn = ? ";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setString(0, "00");
		query.setString(1, rspCode);
		query.setString(2, rspMsg);
		query.setString(3, txnseqno);
		int rows = query.executeUpdate();
		log.info("updateOrderToSuccess() effect rows:"+rows);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateOrderResult(String payordno){
		TxnsLogDO txnsLog = txnsLogDAO.getTxnsLogByPayOrder(payordno);
		if(txnsLog==null){
			log.warn("payordno "+payordno+"is not exist");
			return;
		}
		//判断交易是否成功
		if("0000".equals(txnsLog.getRetcode())){//交易成功
			updateOrderToSuccess(txnsLog.getTxnseqno(), txnsLog.getRetcode(), txnsLog.getRetinfo());
		}else{//交易失败
			updateOrderToFail(txnsLog.getTxnseqno(), txnsLog.getRetcode(), txnsLog.getRetinfo());
		}
		String sql = "select count(1) as total from T_ORDER_COLLECT_DETA t where t.batchtid = (select t1.batchtid from T_ORDER_COLLECT_DETA t1 where t1.relatetradetxn=?) and t.status=?";
		SQLQuery query = (SQLQuery) getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter(0, payordno);
		query.setParameter(1, "02");
		List<Map<String, Object>> list = query.list();
		if(list.size()>0){
			Map<String, Object> value = list.get(0);
			String totalCount = value.get("TOTAL").toString();//正在支付的笔数
			if(totalCount.equals("0")){//没有支付中的交易
				OrderCollectDetaDO orderCollectDeta = getOrderCollectDetaBytxnseqno(txnsLog.getTxnseqno());
				orderCollectBatchDAO.updateOrderToSuccess(orderCollectDeta.getBatchtid());
			}
		}
	}
	
	@Transactional(readOnly=true)
	public OrderCollectDetaDO getOrderCollectDetaBytxnseqno(String txnseqno){
		Criteria criteria = getSession().createCriteria(OrderCollectDetaDO.class);
		criteria.add(Restrictions.eq("relatetradetxn", txnseqno));
		OrderCollectDetaDO uniqueResult = (OrderCollectDetaDO) criteria.uniqueResult();
		return uniqueResult;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateOrderToPay(long batchId) {
		// TODO Auto-generated method stub
		String hql = "update OrderCollectDetaDO set status = ? where batchtid = ? ";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, "02");
		query.setParameter(1, batchId);
		int rows = query.executeUpdate();
		log.info("updateOrderToPay() effect rows:"+rows);
	}

}
