package com.zcbspay.platform.hz.batch.fe.exception;


public class HZBatchFEException extends AbstractDescException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7287083614599423209L;
	private String code;
	/**
	 *
	 * @return
	 */
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}
	
	public HZBatchFEException(String code,Object ... para ) {
        this.params = para;
        this.code = code;
    }
	
	public HZBatchFEException(String code) {
        this.code = code;
    }
	/**
	 * 
	 */
	public HZBatchFEException() {
		super();
		// TODO Auto-generated constructor stub
	}
}
