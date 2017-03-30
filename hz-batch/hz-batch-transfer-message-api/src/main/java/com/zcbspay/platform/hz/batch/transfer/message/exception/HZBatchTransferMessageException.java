package com.zcbspay.platform.hz.batch.transfer.message.exception;

public class HZBatchTransferMessageException extends AbstractDescException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4846959299563109056L;
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
	
	public HZBatchTransferMessageException(String code,Object ... para ) {
        this.params = para;
        this.code = code;
    }
	
	public HZBatchTransferMessageException(String code) {
        this.code = code;
    }
	
	/**
	 * 
	 */
	public HZBatchTransferMessageException() {
		super();
		// TODO Auto-generated constructor stub
	}
}
