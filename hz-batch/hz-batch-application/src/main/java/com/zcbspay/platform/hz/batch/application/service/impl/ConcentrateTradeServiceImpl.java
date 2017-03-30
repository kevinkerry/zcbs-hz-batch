package com.zcbspay.platform.hz.batch.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.zcbspay.platform.hz.batch.application.exception.HZBatchApplicationException;
import com.zcbspay.platform.hz.batch.application.service.ConcentrateTradeService;
import com.zcbspay.platform.hz.batch.application.service.bean.TradeBean;
import com.zcbspay.platform.hz.batch.business.message.api.BusinesssMessageSender;
import com.zcbspay.platform.hz.batch.business.message.api.bean.BatchCollectionChargesBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.BatchPaymentBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.CollectionChargesDetaBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.PaymentDetaBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.ProtocolSignBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.ResultBean;
import com.zcbspay.platform.hz.batch.business.message.exception.HZBatchBusinessMessageException;
import com.zcbspay.platform.hz.batch.common.utils.DateUtil;
import com.zcbspay.platform.hz.batch.dao.ChnAgreementDAO;
import com.zcbspay.platform.hz.batch.dao.OrderCollectBatchDAO;
import com.zcbspay.platform.hz.batch.dao.OrderCollectDetaDAO;
import com.zcbspay.platform.hz.batch.dao.OrderPaymentBatchDAO;
import com.zcbspay.platform.hz.batch.dao.OrderPaymentDetaDAO;
import com.zcbspay.platform.hz.batch.pojo.ChnAgreementDO;
import com.zcbspay.platform.hz.batch.pojo.OrderCollectBatchDO;
import com.zcbspay.platform.hz.batch.pojo.OrderCollectDetaDO;
import com.zcbspay.platform.hz.batch.pojo.OrderPaymentBatchDO;
import com.zcbspay.platform.hz.batch.pojo.OrderPaymentDetaDO;
import com.zcbspay.platform.member.merchant.bean.MerchantBean;
import com.zcbspay.platform.member.merchant.service.MerchService;

@Service
public class ConcentrateTradeServiceImpl implements ConcentrateTradeService {

	@Autowired
	private OrderCollectBatchDAO orderCollectBatchDAO;
	@Autowired
	private OrderCollectDetaDAO orderCollectDetaDAO;
	@Autowired
	private ChnAgreementDAO chnAgreementDAO;
	@Autowired
	private OrderPaymentBatchDAO orderPaymentBatchDAO;
	@Autowired
	private OrderPaymentDetaDAO orderPaymentDetaDAO;
	@Reference(version="1.0")
	private BusinesssMessageSender businesssMessageSender;
	@Reference(version="1.0") 
	private MerchService merchService;
	@Override
	public ResultBean batchCollection(TradeBean tradeBean) throws HZBatchApplicationException {
		/**
		 * 原则上发送过一次的代收批次，禁止再次发送，如果是因为特殊原因导致的实际交易数据更新成功，但未发送出去，可以手动修改批次状态，
		 * 但如果是强制重新发起代收，批次明细中已经确认交易成功的不再发送，所有交易都会按照新的代收付交易处理，上一次的渠道交易流水保留
		 */
		BatchCollectionChargesBean batchBean = new BatchCollectionChargesBean();
		List<CollectionChargesDetaBean> detaList = Lists.newArrayList();
		OrderCollectBatchDO orderCollectBatch = orderCollectBatchDAO.getCollectBatchOrderByTn(tradeBean.getTn());
		if("00".equals(orderCollectBatch.getStatus())){//状态为待支付才能进行交易
			throw new HZBatchApplicationException("HZB001");
		}else if(!"01".equals(orderCollectBatch.getStatus())){//其他状态的批次订单
			throw new HZBatchApplicationException("HZB002");
		}
		List<OrderCollectDetaDO> detaOrderList = orderCollectDetaDAO.getDetaListByBatchtid(orderCollectBatch.getTid());
		MerchantBean merchantBean = merchService.getMerchBymemberId(orderCollectBatch.getMerid());
		if(StringUtils.isEmpty(merchantBean.getChargingunit())){
			throw new HZBatchApplicationException("HZB005");
		}
		for(OrderCollectDetaDO collectDeta : detaOrderList){
			if("00".equals(collectDeta.getStatus())){//状态为交易成功的交易忽略
				continue;
			}
			CollectionChargesDetaBean detaBean = new CollectionChargesDetaBean();
			detaBean.setDebtorUnitCode(merchantBean.getChargingunit());//从商户表中获取，暂时没有此字段,查询处理
			detaBean.setCommitDate(DateUtil.getCurrentDate());
			detaBean.setDebtorBranchNo(collectDeta.getDebtorbank());//出帐银行号
			detaBean.setDebtorAccountNo(collectDeta.getDebtoraccount());
			detaBean.setDebtorName(collectDeta.getDebtorname());
			detaBean.setCreditorBranchCode(collectDeta.getCreditorbank());
			detaBean.setCreditorAccountNo(collectDeta.getCreditoraccount());
			detaBean.setCurrencyCode("RMB");
			detaBean.setAmount(collectDeta.getAmt());
			ChnAgreementDO agreement = chnAgreementDAO.getAgreement(merchantBean.getChargingunit(), collectDeta.getCreditoraccount());
			detaBean.setMeteringCode(agreement.getMeterobjnumber());//计量对象号码 从代收委托协议表中获取
			detaBean.setEmpowerCode(agreement.getEntrustnum());//授权号 从代收委托协议表中获取
			detaBean.setAccountType("0");//支付工具类型:0-未知1-活期储蓄存折2-对公账户3-借记卡4-信用卡（贷记卡）5-活期存款账户
			detaBean.setVoucherCode("");//凭证号码 不清楚
			detaBean.setTxnseqno(collectDeta.getRelatetradetxn());
			detaList.add(detaBean);
		}
		batchBean.setBatchNo(tradeBean.getTn());
		batchBean.setMerchNo(orderCollectBatch.getMerid());
		batchBean.setTotalAmt(orderCollectBatch.getTotalamt()+"");
		batchBean.setTotalCount(orderCollectBatch.getTotalqty()+"");
		batchBean.setDetaList(detaList);
		//批次状态和明细状态修改为正在交易中（02）
		orderCollectBatchDAO.updateOrderToPay(tradeBean.getTn());
		orderCollectDetaDAO.updateOrderToPay(orderCollectBatch.getTid());
		ResultBean resultBean = null;
		try {
			resultBean = businesssMessageSender.batchCollectionCharges(batchBean);
		} catch (HZBatchBusinessMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultBean = new ResultBean(e.getCode(), e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean batchPayment(TradeBean tradeBean) throws HZBatchApplicationException {
		/**
		 * 原则上发送过一次的代收批次，禁止再次发送，如果是因为特殊原因导致的实际交易数据更新成功，但未发送出去，可以手动修改批次状态，
		 * 但如果是强制重新发起代收，批次明细中已经确认交易成功的不再发送，所有交易都会按照新的代收付交易处理，上一次的渠道交易流水保留
		 */
		BatchPaymentBean paymentBean = new BatchPaymentBean();
		List<PaymentDetaBean> detaList = Lists.newArrayList();
		OrderPaymentBatchDO paymentBatchOrder = orderPaymentBatchDAO.getPaymentBatchOrderByTn(tradeBean.getTn());
		if("00".equals(paymentBatchOrder.getStatus())){//状态为待支付才能进行交易
			throw new HZBatchApplicationException("HZB003");
		}else if(!"01".equals(paymentBatchOrder.getStatus())){
			throw new HZBatchApplicationException("HZB004");
		}
		List<OrderPaymentDetaDO> orderList = orderPaymentDetaDAO.getDetaListByBatchtid(paymentBatchOrder.getTid());
		MerchantBean merchantBean = merchService.getMerchBymemberId(paymentBatchOrder.getMerid());
		if(StringUtils.isEmpty(merchantBean.getChargingunit())){
			throw new HZBatchApplicationException("HZB005");
		}
		for(OrderPaymentDetaDO orderDeta : orderList){
			if("00".equals(orderDeta.getStatus())){//状态为交易成功的交易忽略，防止交易成功订单重复支付
				continue;
			}
			PaymentDetaBean detaBean = new PaymentDetaBean();
			detaBean.setDebtorUnitCode(merchantBean.getChargingunit());
			detaBean.setCommitDate(DateUtil.getCurrentDate());
			detaBean.setCreditorBranchCode(orderDeta.getCreditorbank());
			detaBean.setCreditorAccountNo(orderDeta.getCreditoraccount());
			detaBean.setCreditorName(orderDeta.getCreditorname());
			detaBean.setDebtorContract(orderDeta.getDebtorconsign());
			detaBean.setDebtorAccountNo(orderDeta.getDebtoraccount());
			detaBean.setCurrencyCode(orderDeta.getCurrencycode());
			detaBean.setAmount(orderDeta.getAmt());
			detaBean.setAccountType("0");
			detaBean.setPostscript(orderDeta.getSummary());
			detaBean.setTxnseqno(orderDeta.getRelatetradetxn());
			detaList.add(detaBean);
		}
		paymentBean.setBatchNo(tradeBean.getTn());
		paymentBean.setMerchNo(paymentBatchOrder.getMerid());
		paymentBean.setTotalAmt(paymentBatchOrder.getTotalamt()+"");
		paymentBean.setTotalCount(paymentBatchOrder.getTotalqty()+"");
		paymentBean.setDetaList(detaList);
		//批次状态和明细状态修改为正在交易中（02）
		orderPaymentBatchDAO.updateOrderToPay(tradeBean.getTn());
		orderPaymentDetaDAO.updateOrderToPay(paymentBatchOrder.getTid());
		ResultBean resultBean = null;
		try {
			resultBean = businesssMessageSender.batchPayment(paymentBean);
		} catch (HZBatchBusinessMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultBean = new ResultBean(e.getCode(), e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean signInOrOut(TradeBean tradeBean) {
		ResultBean resultBean = null;
		try {
			resultBean = businesssMessageSender.signInAndSignOut(tradeBean.getSignOperateType());
		} catch (HZBatchBusinessMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultBean = new ResultBean(e.getCode(), e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean downLoadBill(TradeBean tradeBean) {
		ResultBean resultBean = null;
		try {
			resultBean = businesssMessageSender.downLoadBill(tradeBean.getBillDate(), tradeBean.getBillOperateType());
		} catch (HZBatchBusinessMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultBean = new ResultBean(e.getCode(), e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean protocolSign(List<ProtocolSignBean> protocolList) {
		ResultBean resultBean = null;
		try {
			resultBean = businesssMessageSender.protocolSign(protocolList);
		} catch (HZBatchBusinessMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultBean = new ResultBean(e.getCode(), e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean protocolDownLoad(TradeBean tradeBean) {
		ResultBean resultBean = null;
		try {
			resultBean = businesssMessageSender.downloadProtocol(tradeBean.getDebtorUnitCode(), tradeBean.getSignDate(), tradeBean.getDownLoadType());
		} catch (HZBatchBusinessMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultBean = new ResultBean(e.getCode(), e.getMessage());
		}
		return resultBean;
	}

}
