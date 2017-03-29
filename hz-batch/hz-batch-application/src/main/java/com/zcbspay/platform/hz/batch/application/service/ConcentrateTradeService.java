package com.zcbspay.platform.hz.batch.application.service;

import java.util.List;

import com.zcbspay.platform.hz.batch.application.exception.HZBatchApplicationException;
import com.zcbspay.platform.hz.batch.application.service.bean.TradeBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.ProtocolSignBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.ResultBean;

public interface ConcentrateTradeService {

	/**
	 * 批量代收
	 * @param tradeBean
	 * @return
	 * @throws HZBatchApplicationException 
	 */
	public ResultBean batchCollection(TradeBean tradeBean) throws HZBatchApplicationException;
	
	/**
	 * 批量代付
	 * @param tradeBean
	 * @return
	 * @throws HZBatchApplicationException 
	 */
	public ResultBean batchPayment(TradeBean tradeBean) throws HZBatchApplicationException;
	/**
	 * 签到签退
	 * @param tradeBean
	 * @return
	 */
	public ResultBean signInOrOut(TradeBean tradeBean);
	/**
	 * 下载对账文件
	 * @param tradeBean
	 * @return
	 */
	public ResultBean downLoadBill(TradeBean tradeBean);
	/**
	 * 代收协议签约
	 * @param tradeBean
	 * @return
	 */
	public ResultBean protocolSign(List<ProtocolSignBean> protocolList);
	/**
	 * 代收协议下载
	 * @param tradeBean
	 * @return
	 */
	public ResultBean protocolDownLoad(TradeBean tradeBean);
}
