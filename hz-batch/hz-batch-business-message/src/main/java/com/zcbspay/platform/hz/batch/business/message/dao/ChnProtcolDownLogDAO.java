package com.zcbspay.platform.hz.batch.business.message.dao;

import com.zcbspay.platform.hz.batch.business.message.pojo.ChnProtcolDownLogDO;
import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;

public interface ChnProtcolDownLogDAO extends BaseDAO<ChnProtcolDownLogDO>{

	/**
	 * 保存协议下载日志
	 * @param chnProtcolDownLog
	 */
	public void saveProtcolDownLog(ChnProtcolDownLogDO chnProtcolDownLog);
}
