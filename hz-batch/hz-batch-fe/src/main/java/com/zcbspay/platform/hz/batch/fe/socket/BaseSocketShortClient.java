package com.zcbspay.platform.hz.batch.fe.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseSocketShortClient  {
	private InetSocketAddress hostAddress;
	private int timeout;
	
	private static final Log logger = LogFactory.getLog(BaseSocketShortClient.class);

	public BaseSocketShortClient(String host,int port,int timeout) {
		this.hostAddress = new InetSocketAddress(host, port);
		this.timeout = timeout;
	}
	
	
	public void sendMessage(byte[] data){
		Socket s = new Socket();
		try{
			try{
				s.connect(hostAddress,timeout);
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("对端链接失败");
			}
			try {
				OutputStream os = s.getOutputStream();
				os.write(data);
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("消息发送失败");
				//TODO 抛异常：消息发送失败
			}
			try{
				s.setSoTimeout(timeout);
				InputStream is = s.getInputStream();
				byte[] msgLength = new byte[4];
				is.read(msgLength);
				int length = Integer.valueOf(new String(msgLength,"UTF-8"));
				logger.info(length);
				byte[] buffer = new byte[length];
				is.read(buffer);
				
				
			} catch (IOException e) {
				e.printStackTrace();
				//消息发送失败
			}
		}finally{
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
