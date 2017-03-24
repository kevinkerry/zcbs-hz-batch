package com.zcbspay.platform.hz.batch.business.message.dao;

import com.zcbspay.platform.hz.batch.business.message.pojo.ChnTxnDO;
import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;

/**
 * 
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2017年3月23日 下午2:48:15
 * @since
 */
public interface ChnTxnDAO extends BaseDAO<ChnTxnDO> {

	/**
	 * 保存渠道对账数据
	 * @param chnTxn
	 */
	public void saveChnTxn(ChnTxnDO chnTxn);
}
