package com.zcbspay.platform.hz.batch.business.message.dao;

import com.zcbspay.platform.hz.batch.business.message.pojo.ChnReconDownLogDO;
import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;
/**
 * 
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2017年3月24日 下午1:53:55
 * @since
 */
public interface ChnReconDownLogDAO extends BaseDAO<ChnReconDownLogDO>{

	/**
	 * 保存对账文件下载日志
	 * @param chnReconDownLog
	 * @return
	 */
	public ChnReconDownLogDO saveReconDownLog(ChnReconDownLogDO chnReconDownLog);
}
