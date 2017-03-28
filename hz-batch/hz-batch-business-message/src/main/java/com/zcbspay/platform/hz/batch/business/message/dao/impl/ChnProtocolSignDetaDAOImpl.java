package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnProtocolSignDetaDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnProtocolSignDetaDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;

@Repository
public class ChnProtocolSignDetaDAOImpl extends HibernateBaseDAOImpl<ChnProtocolSignDetaDO>
		implements ChnProtocolSignDetaDAO {

	

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void saveProtocolSignDeta(ChnProtocolSignDetaDO chnProtocolSignDeta) {
		// TODO Auto-generated method stub
		saveEntity(chnProtocolSignDeta);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void batchSaveProtocolSignDeta(List<ChnProtocolSignDetaDO> detaList) {
		// TODO Auto-generated method stub
		for(ChnProtocolSignDetaDO protocolSignDeta : detaList ){
			saveEntity(protocolSignDeta);
		}
	}

}
