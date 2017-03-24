package com.zcbspay.platform.hz.batch.business.message.dao;

import com.zcbspay.platform.hz.batch.business.message.pojo.ChnSignInOutLogDO;
import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;

public interface ChnSignInOutLogDAO extends BaseDAO<ChnSignInOutLogDO>{

	/**
	 * 保存签到签退日志
	 * @param chnSignInOutLog
	 */
	public ChnSignInOutLogDO saveSignInOutLog(ChnSignInOutLogDO chnSignInOutLog);
	
	/**
	 * 更新签到签退日志（应答码）
	 * @param chnSignInOutLog
	 */
	public void updateSignInOutLog(long tid,String rspCode);
}
