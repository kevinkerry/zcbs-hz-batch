package com.zcbspay.platform.hz.batch.fe.api;

public interface MessageSender {

	/**
	 * 发送报文信息
	 * @param message
	 * @return
	 */
	public String sendMessage(String message,String msgType);
}
