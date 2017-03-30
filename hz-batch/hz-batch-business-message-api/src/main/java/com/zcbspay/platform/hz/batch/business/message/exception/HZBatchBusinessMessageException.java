package com.zcbspay.platform.hz.batch.business.message.exception;

public class HZBatchBusinessMessageException extends AbstractDescException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6802411452237660812L;
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
	
	public HZBatchBusinessMessageException(String code,Object ... para ) {
        this.params = para;
        this.code = code;
    }
	
	public HZBatchBusinessMessageException(String code) {
        this.code = code;
    }
	/**
	 * 
	 */
	public HZBatchBusinessMessageException() {
		super();
		// TODO Auto-generated constructor stub
	}

}
