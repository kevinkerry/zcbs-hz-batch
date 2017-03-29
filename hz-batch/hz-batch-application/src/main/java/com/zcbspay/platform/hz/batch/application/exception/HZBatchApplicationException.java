package com.zcbspay.platform.hz.batch.application.exception;

import com.zcbspay.platform.hz.batch.common.exception.AbstractDescException;

public class HZBatchApplicationException extends AbstractDescException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2532723750334986539L;
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
	
	public HZBatchApplicationException(String code,Object ... para ) {
        this.params = para;
        this.code = code;
    }
	
	public HZBatchApplicationException(String code) {
        this.code = code;
    }
	/**
	 * 
	 */
	public HZBatchApplicationException() {
		super();
		// TODO Auto-generated constructor stub
	}
}
