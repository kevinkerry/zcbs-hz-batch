package com.zcbspay.platform.hz.batch.business.message.api;

import java.util.List;

import com.zcbspay.platform.hz.batch.business.message.api.bean.BatchCollectionChargesBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.BatchPaymentBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.ProtocolSignBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.ResultBean;
import com.zcbspay.platform.hz.batch.business.message.exception.HZBatchBusinessMessageException;

/**
 * 
 * 业务报文发送器
 *
 * @author guojia
 * @version
 * @date 2017年3月9日 下午4:01:24
 * @since
 */
public interface BusinesssMessageSender {

	/**
	 * 批量代收
	 * @param collectionChargesBean
	 * @return
	 */
	public ResultBean batchCollectionCharges(BatchCollectionChargesBean collectionChargesBean) throws HZBatchBusinessMessageException ;
	
	/**
	 * 批量代付
	 * @param paymentBean
	 * @return
	 */
	public ResultBean batchPayment(BatchPaymentBean paymentBean) throws HZBatchBusinessMessageException;
	
	/**
	 * 签到或者签退
	 * @param operateType 01:签到 02:签退
	 * @return
	 */
	public ResultBean signInAndSignOut(String operateType) throws HZBatchBusinessMessageException;
	
	/**
	 * 
	 * @param billDate 
	 * @param operateType 01:代收 02:代付
	 * @return
	 */
	public ResultBean downLoadBill(String merchNo,String billDate,String operateType) throws HZBatchBusinessMessageException;
	
	/**
	 * 
	 * @param protocolList
	 * @return
	 */
	public ResultBean protocolSign(List<ProtocolSignBean> protocolList) throws HZBatchBusinessMessageException;
	
	/**
	 * 下载签约协议
	 * @param debtorUnitCode
	 * @param signDate
	 * @param downLoadType
	 * @return
	 */
	public ResultBean downloadProtocol(String debtorUnitCode,String signDate,String downLoadType) throws HZBatchBusinessMessageException;
}
