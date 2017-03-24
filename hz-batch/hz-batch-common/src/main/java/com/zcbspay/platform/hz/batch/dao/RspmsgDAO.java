/* 
 * RspmsgDAO.java  
 * 
 * version TODO
 *
 * 2015年10月22日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.hz.batch.dao;

import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;
import com.zcbspay.platform.hz.batch.enums.ChnlTypeEnum;
import com.zcbspay.platform.hz.batch.pojo.RspmsgDO;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2015年10月22日 下午1:43:04
 * @since 
 */
public interface RspmsgDAO extends BaseDAO<RspmsgDO>{

    public RspmsgDO get(String rspid);
    public RspmsgDO getRspmsgByChnlCode(ChnlTypeEnum chnlType,String retCode) ;
}
