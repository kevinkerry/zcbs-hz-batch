package com.zcbspay.platform.hz.batch.business.message.sequence;

/**
 * 交易序列号服务
 *
 * @author guojia
 * @version
 * @date 2017年3月22日 上午9:53:07
 * @since
 */
public interface TradeSequenceService {

	/**
	 * 获取代收交易明细流水号
	 * @return
	 */
	public String getCMTCollectSeqNo();
	/**
	 * 获取代付交易明细流水号
	 * @return
	 */
	public String getCMTPaymentSeqNo();
	/**
	 * 获取批量代收批次号
	 * @return
	 */
	public String getCollectionBatchNo();
	/**
	 * 获取批量代付批次号
	 * @return
	 */
	public String getPaymentBatchNo();
	
	/**
	 * 获取批量协议批次号
	 * @return
	 */
	public String getProtocolBatchNo();
}
