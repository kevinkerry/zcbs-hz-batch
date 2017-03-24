package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnCollectDetaDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnCollectDetaDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;

@Repository
public class ChnCollectDetaDAOImpl extends HibernateBaseDAOImpl<ChnCollectDetaDO> implements ChnCollectDetaDAO {

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void saveCollectDeta(ChnCollectDetaDO chnCollectDeta) {
		saveEntity(chnCollectDeta);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void saveBatchCollectDeta(List<ChnCollectDetaDO> detaList) {
		for(ChnCollectDetaDO collectDeta : detaList){
			saveEntity(collectDeta);
		}
	}

	

}
