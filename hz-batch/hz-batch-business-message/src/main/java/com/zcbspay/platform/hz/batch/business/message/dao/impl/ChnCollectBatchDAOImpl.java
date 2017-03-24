package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnCollectBatchDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnCollectBatchDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;

@Repository
public class ChnCollectBatchDAOImpl extends HibernateBaseDAOImpl<ChnCollectBatchDO> implements ChnCollectBatchDAO{

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public ChnCollectBatchDO saveCollectBatch(ChnCollectBatchDO chnCollectBatch) {
		return merge(chnCollectBatch);
	}
	
	
}
