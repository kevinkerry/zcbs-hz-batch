package com.zcbspay.platform.hz.batch.application.enums;


public enum HZBatchEnum {
	/**
	 * 实时代付
	 */
	BATCH_PAYMENT("TAG_001"),
	/**
	 * 实时代收
	 */
	BATCH_COLLECT("TAG_002"),
	/**
	 * 签到
	 */
	SIGNIN("TAG_004"),
	/**
	 * 签退
	 */
	SIGNOUT("TAG_005"),
	/**
	 * 下载对账文件
	 */
	DOWNLOAD_BILL("TAG_006"),
	
	/**
	 * 代收协议签约
	 */
	PROTOCOLSIGN("TAG_008"),
	/**
	 * 代收协议下载
	 */
	PROTOCOL_DOWNLOAD("TAG_009"),
	
	;
	private String code;

	/**
	 * @param code
	 */
	private HZBatchEnum(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	
	public static HZBatchEnum fromValue(String code){
		for(HZBatchEnum tagsEnum : values()){
			if(tagsEnum.getCode().equals(code)){
				return tagsEnum;
			}
		}
		return null;
	}
}
