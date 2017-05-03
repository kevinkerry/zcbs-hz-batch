package com.zcbspay.platform.jni;

public class YHT_JNI {

	public native int sendYHTBWFile(String ip,String port,String sndFName);
	
	public native int recvYHTBWFile(String ip,String port,String sndFName,int timeOut, String rcvedFName,String prepUsePath);
	
	static{
		 System.loadLibrary("libyhtjni");  
	}
}
