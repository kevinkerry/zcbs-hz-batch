package com.zcbspay.platform.hz.batch.business.message.dao;

import java.util.List;

import com.zcbspay.platform.hz.batch.business.message.pojo.ChnProtocolSignDetaDO;
import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;

/**
 * 
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2017年3月23日 上午11:13:14
 * @since
 */
public interface ChnProtocolSignDetaDAO extends BaseDAO<ChnProtocolSignDetaDO> {

	/**
	 * 保存协议签约明细数据
	 * @param chnProtocolSignDeta
	 */
	public void saveProtocolSignDeta(ChnProtocolSignDetaDO chnProtocolSignDeta); 
	
	/**
	 * 批量保存协议签约明细数据
	 * @param detaList
	 */
	public void batchSaveProtocolSignDeta(List<ChnProtocolSignDetaDO> detaList);
}
