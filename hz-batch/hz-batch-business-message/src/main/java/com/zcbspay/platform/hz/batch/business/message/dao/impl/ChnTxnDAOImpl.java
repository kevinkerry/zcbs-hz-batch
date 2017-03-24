package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnTxnDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnTxnDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;
@Repository
public class ChnTxnDAOImpl extends HibernateBaseDAOImpl<ChnTxnDO> implements ChnTxnDAO {

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void saveChnTxn(ChnTxnDO chnTxn) {
		if(getTxnByTxId(chnTxn.getTxid())==0){
			saveEntity(chnTxn);
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public int getTxnByTxId(String txId){
		Criteria criteria = getSession().createCriteria(ChnTxnDO.class);
		criteria.add(Restrictions.eq("txid", txId));
		return criteria.list().size();
	}

}
