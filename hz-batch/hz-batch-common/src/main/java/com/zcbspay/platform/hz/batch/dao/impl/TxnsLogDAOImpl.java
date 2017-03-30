/* 
 * TxnsLogDAOImpl.java  
 * 
 * version TODO
 *
 * 2016年10月13日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.hz.batch.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.bean.PayPartyBean;
import com.zcbspay.platform.hz.batch.common.constant.Constant;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.hz.batch.common.utils.DateUtil;
import com.zcbspay.platform.hz.batch.common.utils.UUIDUtil;
import com.zcbspay.platform.hz.batch.dao.RspmsgDAO;
import com.zcbspay.platform.hz.batch.dao.TxnsLogDAO;
import com.zcbspay.platform.hz.batch.enums.BusinessEnum;
import com.zcbspay.platform.hz.batch.enums.ChnlTypeEnum;
import com.zcbspay.platform.hz.batch.enums.TradeStatFlagEnum;
import com.zcbspay.platform.hz.batch.enums.TradeTxnFlagEnum;
import com.zcbspay.platform.hz.batch.pojo.RspmsgDO;
import com.zcbspay.platform.hz.batch.pojo.TxnsLogDO;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年10月13日 下午2:07:03
 * @since
 */
@Repository
public class TxnsLogDAOImpl extends HibernateBaseDAOImpl<TxnsLogDO> implements
		TxnsLogDAO {

	private static final Logger log = LoggerFactory
			.getLogger(TxnsLogDAOImpl.class);

	@Autowired
	private RspmsgDAO rspmsgDAO;
	/**
	 *
	 * @param txnseqno
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public TxnsLogDO getTxnsLogByTxnseqno(String txnseqno) {
		Criteria criteria = getSession().createCriteria(TxnsLogDO.class);
		criteria.add(Restrictions.eq("txnseqno", txnseqno));
		TxnsLogDO txnsLog = (TxnsLogDO) criteria.uniqueResult();
		if(txnsLog!=null){
			getSession().evict(txnsLog);
		}
		return txnsLog;
	}

	/**
	 *
	 * @param txnsLog
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateTxnsLog(TxnsLogDO txnsLog) {
		update(txnsLog);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updatePayInfo(PayPartyBean payPartyBean) {
		String hql = "update TxnsLogDO set payordno=?,payinst=?,payfirmerno=?,payordcomtime=? where txnseqno=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, payPartyBean.getPayordno());
		query.setParameter(1, Constant.getInstance().getChannelCode());
		query.setParameter(2, Constant.getInstance().getSenderCode());
		query.setParameter(3, DateUtil.getCurrentDateTime());
		
		query.setParameter(4, payPartyBean.getTxnseqno());
		int rows = query.executeUpdate();
		log.info("updatePayInfo() effect rows:" + rows);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateTradeStatFlag(String txnseqno,
			TradeStatFlagEnum tradeStatFlagEnum) {
		String hql = "update TxnsLogDO set tradestatflag = ? where txnseqno = ?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, tradeStatFlagEnum.getStatus());
		query.setParameter(1, txnseqno);
		int rows = query.executeUpdate();
		log.info("updateTradeStatFlag() effect rows:" + rows);
	}
	
	

	/**
	 *
	 * @param cardNo
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> getCardInfo(String cardNo){
		StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("SELECT type,bankcode,bankname ");
        sqlBuffer.append("FROM (SELECT t.TYPE,t.BANKCODE,b.bankname ");
        sqlBuffer.append("FROM t_card_bin t, t_bank_insti b ");
        sqlBuffer.append("WHERE t.bankcode = b.bankcode ");
        sqlBuffer.append("AND ? LIKE t.cardbin || '%' ");
        sqlBuffer.append("AND t.cardlen = ? ");
        sqlBuffer.append("ORDER BY t.cardbin DESC) ");
        sqlBuffer.append("WHERE ROWNUM = 1 ");
        
        SQLQuery sqlQuery = (SQLQuery) getSession().createSQLQuery(sqlBuffer.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        sqlQuery.setParameter(0, cardNo);
        sqlQuery.setParameter(1, cardNo.trim().length());
        List<Map<String, Object>> routList =  (List<Map<String, Object>>)sqlQuery.list();
       
        if(routList.size()>0){
            return routList.get(0);
        }
		return null;
	}

	/**
	 *
	 * @param txnseqno
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateAppInfo(String txnseqno) {
		// TODO Auto-generated method stub
		String hql = "update TxnsLogDO set appinst=?,appordcommitime=?,appordno=? where txnseqno = ?";
		 Query query = getSession().createQuery(hql);
		 query.setParameter(0, "000000000000");
		 query.setParameter(1, DateUtil.getCurrentDateTime());
		 query.setParameter(2, "");
		 query.setParameter(3, txnseqno);
		 int rows = query.executeUpdate();
		 log.info("updateAppInfo() effect rows:" + rows);
	}
	
	/**
	 *
	 * @param txnsLog
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void saveTxnsLog(TxnsLogDO txnsLog) {
		saveEntity(txnsLog);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updatePayInfoResult(PayPartyBean payPartyBean) {
		RspmsgDO rspmsg = rspmsgDAO.getRspmsgByChnlCode(ChnlTypeEnum.HZ, payPartyBean.getPayretcode());
		if(rspmsg==null){
			rspmsg = new RspmsgDO();
			rspmsg.setRspinfo("未知异常");
		}
		String hql = "update TxnsLogDO set payordfintime = ?, payretcode = ?, payretinfo = ?, accordfintime = ?,retdatetime=?,tradetxnflag=?,tradestatflag = ?,relate=?,tradeseltxn=?,retcode = ?,retinfo = ?  where payordno=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, DateUtil.getCurrentDateTime());
		query.setParameter(1, payPartyBean.getPayretcode());
		query.setParameter(2, rspmsg.getRspinfo());
		query.setParameter(3, DateUtil.getCurrentDateTime());
		query.setParameter(4, DateUtil.getCurrentDateTime());
		if(BusinessEnum.CONCENTRATE_COLLECT_BATCH==payPartyBean.getBusinessEnum()){
			query.setParameter(5, TradeTxnFlagEnum.HZ_BATCH_COLLECT.getCode());
		}else if(BusinessEnum.CONCENTRATE_PAYMENT_BATCH==payPartyBean.getBusinessEnum()){
			query.setParameter(5, TradeTxnFlagEnum.HZ_BATCH_PAYMENT.getCode());
		}
		query.setParameter(6, TradeStatFlagEnum.FINISH_SUCCESS.getStatus());
		query.setParameter(7, "10000000");
		query.setParameter(8, UUIDUtil.uuid());
		query.setParameter(9, rspmsg.getWebrspcode());
		query.setParameter(10, rspmsg.getRspinfo());
		query.setParameter(11, payPartyBean.getPayordno());
		int rows = query.executeUpdate();
		log.info("updatePayInfo() effect rows:" + rows);
		
	}
	
	

	@Override
	@Transactional(readOnly=true)
	public TxnsLogDO getTxnsLogByPayOrder(String payordno) {
		Criteria criteria = getSession().createCriteria(TxnsLogDO.class);
		criteria.add(Restrictions.eq("payordno", payordno));
		TxnsLogDO uniqueResult = (TxnsLogDO) criteria.uniqueResult();
		return uniqueResult;
	}
	
	
}
