/* 
 * ITxnsLogDAO.java  
 * 
 * version TODO
 *
 * 2015年8月27日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.hz.batch.dao;

import java.util.Map;

import com.zcbspay.platform.hz.batch.bean.PayPartyBean;
import com.zcbspay.platform.hz.batch.common.dao.BaseDAO;
import com.zcbspay.platform.hz.batch.enums.TradeStatFlagEnum;
import com.zcbspay.platform.hz.batch.pojo.TxnsLogDO;


/**
 * 交流流水表DAO
 *
 * @author guojia
 * @version
 * @date 2015年8月27日 下午2:20:50
 * @since 
 */
public interface TxnsLogDAO extends BaseDAO<TxnsLogDO>{
	
	/**
	 * 根据交易序列号获取交易日志数据
	 * @param txnseqno 交易序列号
	 * @return
	 */
	public TxnsLogDO getTxnsLogByTxnseqno(String txnseqno);
	
	/**
	 * 更新交易流水日志数据
	 * @param txnsLog 交易流水日志pojo
	 */
	public void updateTxnsLog(TxnsLogDO txnsLog);
	
	/**
	 * 更新支付交易交易数据（支付前）
	 * @param payPartyBean 支付方数据bean
	 */
	public void updatePayInfo(PayPartyBean payPartyBean);
	
	/**
	 * 更新交易标记状态
	 * @param txnseqno 交易序列号
	 * @param tradeStatFlagEnum 交易标记状态
	 */
	public void updateTradeStatFlag(String txnseqno,
			TradeStatFlagEnum tradeStatFlagEnum);
	
	
	/**
	 * 获取银行卡信息
	 * @param cardNo 银行卡号
	 * @return
	 */
	public Map<String, Object> getCardInfo(String cardNo);
	
	/**
	 * 更新应用方信息
	 * @param txnseqno
	 */
	public void updateAppInfo(String txnseqno);
	
	/**
	 * 保存交易日志
	 * @param txnsLog 交易日志pojo
	 */
	public void saveTxnsLog(TxnsLogDO txnsLog);
	
	/**
	 * 更新支付方交易结果
	 * @param payPartyBean
	 */
	public void updatePayInfoResult(PayPartyBean payPartyBean);
	
	/**
	 * 通过支付订单号获取交易流水数据
	 * @param payordno
	 * @return
	 */
	public TxnsLogDO getTxnsLogByPayOrder(String payordno);
}
