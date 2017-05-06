package com.zcbspay.platform.jni;



public class YHT_JNI {

	public native int sendYHTBWFile(String ip,String port,String sndFName,String prepUsePath);
	
	public native String recvYHTBWFile(String ip,String port,String sndFName,String prepUsePath);
	static{
		 System.loadLibrary("yhtjni");  
	}
	
}
