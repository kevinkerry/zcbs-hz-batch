package com.zcbspay.platform.hz.batch.dao;

import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;
import com.zcbspay.platform.hz.batch.pojo.HzAgencyInfoDO;

public interface HzAgencyInfoDAO extends BaseDAO<HzAgencyInfoDO>{

	/**
	 * 通过委托机构号和业务代码获取杭州接入方信息
	 * @param merchNo
	 * @param busicode
	 * @return
	 */
	public HzAgencyInfoDO getAgencyInfo(String merchNo,String busicode);
}
