package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnReconDownLogDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnReconDownLogDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;

@Repository
public class ChnReconDownLogDAOImpl extends HibernateBaseDAOImpl<ChnReconDownLogDO> implements
		ChnReconDownLogDAO {

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public ChnReconDownLogDO saveReconDownLog(ChnReconDownLogDO chnReconDownLog) {
		// TODO Auto-generated method stub
		return merge(chnReconDownLog);
	}

	

}
