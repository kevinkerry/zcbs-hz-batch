package com.zcbspay.platform.hz.batch.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.hz.batch.dao.ChnAgreementDAO;
import com.zcbspay.platform.hz.batch.pojo.ChnAgreementDO;
@Repository
public class ChnAgreementDAOImpl extends HibernateBaseDAOImpl<ChnAgreementDO> implements
		ChnAgreementDAO {

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public ChnAgreementDO getAgreement(String chargingunit,
			String debtoraccountno) {
		Criteria criteria = getSession().createCriteria(ChnAgreementDO.class);
		criteria.add(Restrictions.eq("chargingunit", chargingunit));
		criteria.add(Restrictions.eq("debtoraccountno", debtoraccountno));
		criteria.add(Restrictions.eq("status", "00"));
		ChnAgreementDO uniqueResult = (ChnAgreementDO) criteria.uniqueResult();
		return uniqueResult;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void saveAgreement(ChnAgreementDO chnAgreement) {
		// TODO Auto-generated method stub
		saveEntity(chnAgreement);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void updateAgreement(ChnAgreementDO chnAgreement) {
		// TODO Auto-generated method stub
		update(chnAgreement);
	}

}
