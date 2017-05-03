/* 
 * CMBCInsteadPayReciveProcessor.java  
 * 
 * version TODO
 *
 * 2015年11月26日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.hz.batch.fe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.dubbo.config.annotation.Reference;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.zcbspay.platform.hz.batch.fe.enums.MessageTypeEnum;
import com.zcbspay.platform.hz.batch.fe.socket.ReceiveProcessor;
import com.zcbspay.platform.hz.batch.fe.socket.message.ResponseMessage;
import com.zcbspay.platform.hz.batch.transfer.message.api.unpack.MessageUnpack;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2015年11月26日 上午10:26:05
 * @since 
 */
public class ReciveProcessorImpl implements ReceiveProcessor{
    private static final String ENCODING = "UTF-8";
    private static final Log log = LogFactory.getLog(ReciveProcessorImpl.class);
    @Reference(version="1.0")
    private MessageUnpack messageUnpack;
    /**
     *
     * @param data
     */
    @Override
    public void onReceive(Object data) {
        // TODO Auto-generated method stub
        byte[] rawData = (byte[]) data;
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(rawData));
        byte[] message = new byte[rawData.length];
        try {
            XStream xstream = new XStream(new DomDriver(null,new XmlFriendlyNameCoder("_-", "_")));
            inputStream.read(message);
            String xml = new String(message,ENCODING);
            xstream.processAnnotations(ResponseMessage.class);
            xstream.autodetectAnnotations(true);
            ResponseMessage responseMessage = (ResponseMessage) xstream.fromXML(xml);
            readFile(responseMessage);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void readFile(ResponseMessage responseMessage) throws IOException{
    	File file = new File(responseMessage.getFilePaht()+responseMessage.getFileName());
    	if(file.exists()){
    		FileReader fr = new FileReader(file);
    		BufferedReader br = new BufferedReader(fr);
    		String content = br.readLine();
    		messageUnpack.unpack(content);
    		br.close();
    		fr.close();
    	}
    	
    }
    
}
