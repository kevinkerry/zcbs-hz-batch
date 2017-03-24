package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnProtcolDownLogDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnProtcolDownLogDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;
@Repository
public class ChnProtcolDownLogDAOImpl extends HibernateBaseDAOImpl<ChnProtcolDownLogDO> implements
		ChnProtcolDownLogDAO {

	

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void saveProtcolDownLog(ChnProtcolDownLogDO chnProtcolDownLog) {
		// TODO Auto-generated method stub
		saveEntity(chnProtcolDownLog);
	}

}
