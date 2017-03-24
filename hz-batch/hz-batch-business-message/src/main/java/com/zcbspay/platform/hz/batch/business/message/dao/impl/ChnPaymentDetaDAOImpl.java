package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnPaymentDetaDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnPaymentDetaDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;
@Repository
public class ChnPaymentDetaDAOImpl extends HibernateBaseDAOImpl<ChnPaymentDetaDO> implements
		ChnPaymentDetaDAO {

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void saveBatchPaymentDeta(List<ChnPaymentDetaDO> detaList) {
		for(ChnPaymentDetaDO paymentDeta:detaList){
			saveEntity(paymentDeta);
		}
	}

}
