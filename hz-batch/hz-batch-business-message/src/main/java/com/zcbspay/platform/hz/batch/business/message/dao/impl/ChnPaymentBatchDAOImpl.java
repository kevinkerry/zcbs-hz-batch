package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnPaymentBatchDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnPaymentBatchDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;

@Repository
public class ChnPaymentBatchDAOImpl extends HibernateBaseDAOImpl<ChnPaymentBatchDO> implements
		ChnPaymentBatchDAO {

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public ChnPaymentBatchDO savePaymentBatch(ChnPaymentBatchDO chnPaymentBatch) {
		return merge(chnPaymentBatch);
	}

	

}
