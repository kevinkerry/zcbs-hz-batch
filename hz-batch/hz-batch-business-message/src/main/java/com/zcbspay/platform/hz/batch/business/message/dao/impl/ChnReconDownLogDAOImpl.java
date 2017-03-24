package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import org.springframework.stereotype.Repository;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnReconDownLogDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnReconDownLogDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;

@Repository
public class ChnReconDownLogDAOImpl extends HibernateBaseDAOImpl<ChnReconDownLogDO> implements
		ChnReconDownLogDAO {

	@Override
	public ChnReconDownLogDO saveReconDownLog(ChnReconDownLogDO chnReconDownLog) {
		// TODO Auto-generated method stub
		return merge(chnReconDownLog);
	}

	

}
