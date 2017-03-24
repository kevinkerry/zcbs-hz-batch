package com.zcbspay.platform.hz.batch.transfer.message.api.unpack;

import com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageBean;





/**
 * 报文解析
 * 
 * @author guojia
 * @version
 * @date 2017年2月23日 下午5:54:29
 * @since
 */
public interface MessageUnpack {


    /**
     * 解析回执报文
     * @param message
     * @return
     */
    public MessageBean unpack(String message);

    
}
