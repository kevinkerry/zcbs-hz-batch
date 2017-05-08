package com.zcbspay.platform.hz.batch.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.hz.batch.dao.ContractDAO;
import com.zcbspay.platform.hz.batch.pojo.ContractDO;

@Repository
public class ContractDAOImpl extends HibernateBaseDAOImpl<ContractDO> implements ContractDAO {

	
	@Override
	@Transactional(readOnly=true)
	public ContractDO getContract(String merchNo, String conractNo) {
		Criteria criteria = getSession().createCriteria(ContractDO.class);
		criteria.add(Restrictions.eq("merchNo", merchNo));
		criteria.add(Restrictions.eq("contractNum", conractNo));
		criteria.add(Restrictions.eq("status", "00"));
		ContractDO contract = null;
		try {
			contract = (ContractDO) criteria.uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contract;
	}

}
