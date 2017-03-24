package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnProtocolSignBatchDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnProtocolSignBatchDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;

@Repository
public class ChnProtocolSignBatchDAOImpl extends HibernateBaseDAOImpl<ChnProtocolSignBatchDO>
		implements ChnProtocolSignBatchDAO {

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public ChnProtocolSignBatchDO saveProtocolSignBatch(ChnProtocolSignBatchDO chnProtocolSignBatch) {
		return merge(chnProtocolSignBatch);
	}

	

}
