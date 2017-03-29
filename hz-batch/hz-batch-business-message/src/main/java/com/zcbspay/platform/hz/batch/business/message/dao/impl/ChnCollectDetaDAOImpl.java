package com.zcbspay.platform.hz.batch.business.message.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.hz.batch.business.message.dao.ChnCollectDetaDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnCollectDetaDO;
import com.zcbspay.platform.hz.batch.common.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.hz.batch.common.utils.DateUtil;
import com.zcbspay.platform.hz.batch.message.bean.CollectBillBean;

@Repository
public class ChnCollectDetaDAOImpl extends HibernateBaseDAOImpl<ChnCollectDetaDO> implements ChnCollectDetaDAO {

	private static final Logger logger = LoggerFactory.getLogger(ChnCollectDetaDAOImpl.class);
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

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void updateCollectDetaResult(CollectBillBean collectBillBean) {
		// TODO Auto-generated method stub
		String hql = "update ChnCollectDetaDO set rspstatus = ?,rspdate = ?,nettingdate = ? where txid = ?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, collectBillBean.getRspCode());
		query.setParameter(1, DateUtil.getCurrentDateTime());
		query.setParameter(2, collectBillBean.getSettDate());
		query.setParameter(3, collectBillBean.getTxId());
		int rows = query.executeUpdate();
		logger.info("hql:{},effect rows:{}",hql,rows);
	}

	

}
