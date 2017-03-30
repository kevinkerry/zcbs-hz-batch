package com.zcbspay.platform.hz.batch.fe.api;

import com.zcbspay.platform.hz.batch.fe.exception.HZBatchFEException;

public interface MessageSender {

	/**
	 * 发送报文信息
	 * @param message
	 * @return
	 * @throws HZBatchFEException 
	 */
	public String sendMessage(String message,String msgType) throws HZBatchFEException;
}
