package com.zcbspay.platform.hz.batch.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.hz.batch.dao.HzAgencyInfoDAO;
import com.zcbspay.platform.hz.batch.pojo.HzAgencyInfoDO;
@Repository
public class HzAgencyInfoDAOImpl extends HibernateBaseDAOImpl<HzAgencyInfoDO> implements HzAgencyInfoDAO {

	

	@Override
	@Transactional(readOnly=true)
	public HzAgencyInfoDO getAgencyInfo(String merchNo, String busicode) {
		Criteria criteria = getSession().createCriteria(HzAgencyInfoDO.class);
		criteria.add(Restrictions.eq("merchno", merchNo));
		criteria.add(Restrictions.eq("busicode", busicode));
		criteria.add(Restrictions.eq("status", "00"));
		return (HzAgencyInfoDO) criteria.uniqueResult();
	}

}
