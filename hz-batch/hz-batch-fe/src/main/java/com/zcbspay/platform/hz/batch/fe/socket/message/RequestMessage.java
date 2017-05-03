package com.zcbspay.platform.hz.batch.fe.socket.message;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

@XStreamAlias("Req")
public class RequestMessage {
	 public static final String XMLHEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	 @XStreamAlias("MsgType")
	 private String msgType;
	 
	 @XStreamAlias("FileName")
	 private String fileName;
	 
	 @XStreamAlias("FilePaht")
	 private String filePaht;
	 
	 @XStreamAlias("IP")
	 private String ip;
	 
	 @XStreamAlias("Port")
	 private String port;
	 
	 @XStreamAlias("TimeOut")
	 private String timeOut;

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePaht() {
		return filePaht;
	}

	public void setFilePaht(String filePaht) {
		this.filePaht = filePaht;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}
	 
	public synchronized String toXML(){
        XStream xstream = new XStream(new DomDriver(null,new XmlFriendlyNameCoder("_-", "_")));  
        xstream.autodetectAnnotations(true);
        String xml = XMLHEAD+xstream.toXML(this);
        Pattern p = Pattern.compile("\\s{2,}|\t|\r|\n");
        Matcher m = p.matcher(xml);
        xml=m.replaceAll("");
        int xml_length = xml.getBytes().length;
        DecimalFormat df = new DecimalFormat("0000");
        String msgLength = df.format(xml_length);
        return  msgLength+xml;
    }
	 
}
