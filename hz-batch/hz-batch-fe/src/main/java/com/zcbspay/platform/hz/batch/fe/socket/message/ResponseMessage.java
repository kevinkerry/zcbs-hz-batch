package com.zcbspay.platform.hz.batch.fe.socket.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Ans")
public class ResponseMessage {
	
	@XStreamAlias("FileName")
	private String fileName;

	@XStreamAlias("FilePaht")
	private String filePaht;

	@XStreamAlias("RetCode")
	private String retCode;

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

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

}
