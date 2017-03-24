package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnSignInOutLogDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnSignInOutLogDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;

@Repository
public class ChnSignInOutLogDAOImpl extends HibernateBaseDAOImpl<ChnSignInOutLogDO> implements
		ChnSignInOutLogDAO {

	private static final Logger logger = LoggerFactory.getLogger(ChnSignInOutLogDAOImpl.class);
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public ChnSignInOutLogDO saveSignInOutLog(ChnSignInOutLogDO chnSignInOutLog) {
		// TODO Auto-generated method stub
		return merge(chnSignInOutLog);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void updateSignInOutLog(long tid, String rspCode) {
		// TODO Auto-generated method stub
		String hql = "update ChnSignInOutLogDO set rspcode = ? where tid = ?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, rspCode);
		query.setParameter(1, tid);
		int rows = query.executeUpdate();
		logger.info("hql:{},effect rows:{}",hql,rows);
	}

	
	

}
