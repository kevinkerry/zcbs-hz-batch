package com.zcbspay.platform.hz.batch.business.message.api;

import com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean;


/**
 * 业务报文接收器
 *
 * @author guojia
 * @version
 * @date 2017年3月9日 下午4:07:57
 * @since
 */
public interface BusinessMessageReceiver {

	/**
	 * 处理签到签退应答报文处理
	 * @param message
	 */
   public void signInAndSignOut(MessageBean messageBean);

   /**
    * 下载协议应答报文处理
    * @param messageBean
    */
   public void downloadProtocol(MessageBean messageBean);

   /**
    * 下载对账应答报文处理
    * @param messageBean
    */
   public void downLoadBill(MessageBean messageBean);

    

}
