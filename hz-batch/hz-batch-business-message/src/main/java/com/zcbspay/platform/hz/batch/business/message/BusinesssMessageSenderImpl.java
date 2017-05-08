package com.zcbspay.platform.hz.batch.business.message;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.zcbspay.platform.hz.batch.bean.PayPartyBean;
import com.zcbspay.platform.hz.batch.business.message.api.BusinesssMessageSender;
import com.zcbspay.platform.hz.batch.business.message.api.bean.BatchCollectionChargesBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.BatchPaymentBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.CollectionChargesDetaBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.PaymentDetaBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.ProtocolSignBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.ResultBean;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnCollectBatchDAO;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnCollectDetaDAO;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnPaymentBatchDAO;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnPaymentDetaDAO;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnProtcolDownLogDAO;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnProtocolSignBatchDAO;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnProtocolSignDetaDAO;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnReconDownLogDAO;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnSignInOutLogDAO;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnTxnDAO;
import com.zcbspay.platform.hz.batch.business.message.exception.HZBatchBusinessMessageException;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnCollectBatchDO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnCollectDetaDO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnPaymentBatchDO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnPaymentDetaDO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnProtcolDownLogDO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnProtocolSignBatchDO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnProtocolSignDetaDO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnReconDownLogDO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnSignInOutLogDO;
import com.zcbspay.platform.hz.batch.business.message.sequence.TradeSequenceService;
import com.zcbspay.platform.hz.batch.common.constant.Constant;
import com.zcbspay.platform.hz.batch.common.utils.DateUtil;
import com.zcbspay.platform.hz.batch.dao.ChnAgreementDAO;
import com.zcbspay.platform.hz.batch.dao.TxnsLogDAO;
import com.zcbspay.platform.hz.batch.enums.TradeStatFlagEnum;
import com.zcbspay.platform.hz.batch.fe.api.MessageSender;
import com.zcbspay.platform.hz.batch.fe.exception.HZBatchFEException;
import com.zcbspay.platform.hz.batch.message.bean.request.AUT031Bean;
import com.zcbspay.platform.hz.batch.message.bean.request.AUT032Bean;
import com.zcbspay.platform.hz.batch.message.bean.request.CMT031Bean;
import com.zcbspay.platform.hz.batch.message.bean.request.CMT036Bean;
import com.zcbspay.platform.hz.batch.message.bean.request.DLD032Bean;
import com.zcbspay.platform.hz.batch.message.bean.request.DLD037Bean;
import com.zcbspay.platform.hz.batch.message.bean.request.GMT031Bean;
import com.zcbspay.platform.hz.batch.transfer.message.api.assemble.MessageAssemble;
import com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageBean;
import com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageHead;
import com.zcbspay.platform.hz.batch.transfer.message.api.enums.MessageTypeEnum;
import com.zcbspay.platform.hz.batch.transfer.message.exception.HZBatchTransferMessageException;

@Service("businesssMessageSender")
public class BusinesssMessageSenderImpl implements BusinesssMessageSender{
	private static final Logger logger = LoggerFactory.getLogger(BusinesssMessageSenderImpl.class);
	private static final String HZ_SIGN_STATUS = "PARAMETER:HZSIGNSTATUS";
	@Reference(version="1.0")
	private MessageAssemble messageAssemble;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private TradeSequenceService tradeSequenceService;
	@Autowired
	private ChnCollectBatchDAO chnCollectBatchDAO;
	@Autowired
	private ChnCollectDetaDAO chnCollectDetaDAO;
	@Autowired
	private ChnPaymentBatchDAO chnPaymentBatchDAO;
	@Autowired
	private ChnPaymentDetaDAO chnPaymentDetaDAO;
	@Autowired
	private ChnSignInOutLogDAO chnSignInOutLogDAO;
	@Autowired
	private ChnProtocolSignBatchDAO chnProtocolSignBatchDAO;
	@Autowired
	private ChnProtocolSignDetaDAO chnProtocolSignDetaDAO;
	@Autowired
	private ChnProtcolDownLogDAO chnProtcolDownLogDAO;
	@Autowired
	private ChnAgreementDAO chnAgreementDAO;
	@Autowired
	private ChnTxnDAO chnTxnDAO;
	@Autowired
	private TxnsLogDAO txnsLogDAO;
	@Autowired
	private ChnReconDownLogDAO chnReconDownLogDAO;
	@Reference(version="1.0")
	private MessageSender messageSender;
	
	
	@Override
	public ResultBean batchCollectionCharges(BatchCollectionChargesBean collectionChargesBean) throws HZBatchBusinessMessageException {
		MessageBean messageBean = new MessageBean();
		messageBean.setMessageTypeEnum(MessageTypeEnum.CMT031);
		messageBean.setSenderCode(collectionChargesBean.getSenderCode());
		MessageHead messageHead = null;
		try {
			messageHead = messageAssemble.createMessageHead(messageBean);
		} catch (HZBatchTransferMessageException e) {
			e.printStackTrace();
			throw new HZBatchBusinessMessageException(e.getCode());
		}
		ChnCollectBatchDO chnCollectBatch = new ChnCollectBatchDO();
		chnCollectBatch.setBatchno(tradeSequenceService.getCollectionBatchNo());
		chnCollectBatch.setMsgtype(messageHead.getMsgType());
		chnCollectBatch.setServicetype(messageHead.getServiceType());
		chnCollectBatch.setTransmitleg(messageHead.getSenderCode());
		chnCollectBatch.setReceiver(messageHead.getReceiverCode());
		chnCollectBatch.setTransdate(messageHead.getLocalDate());
		chnCollectBatch.setTranstime(messageHead.getLocalTime());
		chnCollectBatch.setTotalqty(Long.valueOf(collectionChargesBean.getTotalCount()));
		chnCollectBatch.setTotalamt(Long.valueOf(collectionChargesBean.getTotalAmt()));
		chnCollectBatch.setOperatorcode(messageHead.getOperator());
		chnCollectBatch.setOrigbatchno(collectionChargesBean.getBatchNo());
		ChnCollectBatchDO batch;
		try {
			batch = chnCollectBatchDAO.saveCollectBatch(chnCollectBatch);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB008");
		}
		//保存批量数据
		List<CollectionChargesDetaBean> detaList = collectionChargesBean.getDetaList();
		List<CMT031Bean> msgList = Lists.newArrayList();
		List<ChnCollectDetaDO> collectDetaList = Lists.newArrayList();
		for(CollectionChargesDetaBean detaBean : detaList){
			CMT031Bean cmt031Bean = new CMT031Bean();
			cmt031Bean.setDebtorUnitCode(detaBean.getDebtorUnitCode());
			cmt031Bean.setCommitDate(detaBean.getCommitDate());
			cmt031Bean.setTxId(tradeSequenceService.getCMTCollectSeqNo());
			cmt031Bean.setDebtorBranchNo(detaBean.getDebtorBranchNo());
			cmt031Bean.setDebtorAccountNo(detaBean.getDebtorAccountNo());
			cmt031Bean.setDebtorName(detaBean.getDebtorName());
			cmt031Bean.setCreditorBranchCode(detaBean.getCreditorBranchCode());
			cmt031Bean.setCreditorAccountNo(detaBean.getCreditorAccountNo());
			cmt031Bean.setCurrencyCode(detaBean.getCurrencyCode());
			cmt031Bean.setAmount(detaBean.getAmount());
			cmt031Bean.setMeteringCode(detaBean.getMeteringCode());
			cmt031Bean.setEmpowerCode(detaBean.getEmpowerCode());
			cmt031Bean.setAccountType(detaBean.getAccountType());
			cmt031Bean.setVoucherCode(detaBean.getVoucherCode());
			cmt031Bean.setPostscript(detaBean.getSummary()==null?"":detaBean.getSummary());
			cmt031Bean.setAdditionSubclass("Y0");
			cmt031Bean.setAdditionLength("000");
			cmt031Bean.setAdditionContent("");
			msgList.add(cmt031Bean);
			ChnCollectDetaDO chnCollectDeta = new ChnCollectDetaDO();
			chnCollectDeta.setBatchtid(batch.getTid());
			chnCollectDeta.setBatchno(batch.getBatchno());
			chnCollectDeta.setChargingunit(detaBean.getDebtorUnitCode());
			chnCollectDeta.setTransdate(detaBean.getCommitDate());
			chnCollectDeta.setTxid(cmt031Bean.getTxId());
			chnCollectDeta.setDebtorname(detaBean.getDebtorName());
			chnCollectDeta.setDebtoraccountno(detaBean.getDebtorAccountNo());
			chnCollectDeta.setDebtorbranchcode(detaBean.getDebtorBranchNo());
			chnCollectDeta.setCreditorbranchcode(detaBean.getCreditorBranchCode());
			chnCollectDeta.setCreditoraccountno(detaBean.getCreditorAccountNo());
			chnCollectDeta.setCurrencysymbol(detaBean.getCurrencyCode());
			chnCollectDeta.setAmount(Long.valueOf(detaBean.getAmount()));
			chnCollectDeta.setMeterobjnumber(detaBean.getMeteringCode());
			chnCollectDeta.setAuthnumber(detaBean.getEmpowerCode());
			chnCollectDeta.setPaymenttooltype(detaBean.getAccountType());
			chnCollectDeta.setVouchernumber(detaBean.getVoucherCode());
			chnCollectDeta.setPostscript(detaBean.getSummary());
			chnCollectDeta.setAdditional(cmt031Bean.getAdditionSubclass());
			chnCollectDeta.setAdditionallen(cmt031Bean.getAdditionLength());
			chnCollectDeta.setAddinfo(cmt031Bean.getAdditionContent());
			chnCollectDeta.setTxnseqno(detaBean.getTxnseqno());
			collectDetaList.add(chnCollectDeta);
			PayPartyBean payPartyBean = new PayPartyBean();
			payPartyBean.setPayordno(cmt031Bean.getTxId());
			payPartyBean.setTxnseqno(detaBean.getTxnseqno());
			txnsLogDAO.updatePayInfo(payPartyBean);
			txnsLogDAO.updateTradeStatFlag(detaBean.getTxnseqno(), TradeStatFlagEnum.PAYING);
		}
		try {
			chnCollectDetaDAO.saveBatchCollectDeta(collectDetaList);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB010");
		}
		messageBean.setMessageBean(msgList);
		String message = null;
		try {
			message = messageAssemble.assemble(messageBean);
		} catch (HZBatchTransferMessageException e) {
			e.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB009");
		}
		ResultBean resultBean = null;
		//无应答报文，只能通过有无异常进行判断
		try {
			String resultMsg = messageSender.sendMessage(message, messageBean.getMessageTypeEnum().name());
			resultBean = new ResultBean(resultMsg);
		} catch (HZBatchFEException e) {
			e.printStackTrace();
			resultBean = new ResultBean(e.getCode(), e.getMessage());
		}
		
		return resultBean;
	}

	@Override
	public ResultBean batchPayment(BatchPaymentBean paymentBean) throws HZBatchBusinessMessageException {
		MessageBean messageBean = new MessageBean();
		messageBean.setSenderCode(paymentBean.getSenderCode());
		messageBean.setMessageTypeEnum(MessageTypeEnum.CMT036);
		MessageHead messageHead = null;
		try {
			messageHead = messageAssemble.createMessageHead(messageBean);
		} catch (HZBatchTransferMessageException e) {
			e.printStackTrace();
			throw new HZBatchBusinessMessageException(e.getCode());
		}
		ChnPaymentBatchDO chnPaymentBatch = new ChnPaymentBatchDO();
		chnPaymentBatch.setBatchno(tradeSequenceService.getCollectionBatchNo());
		chnPaymentBatch.setMsgtype(messageHead.getMsgType());
		chnPaymentBatch.setServicetype(messageHead.getServiceType());
		chnPaymentBatch.setTransmitleg(messageHead.getSenderCode());
		chnPaymentBatch.setReceiver(messageHead.getReceiverCode());
		chnPaymentBatch.setTransdate(messageHead.getLocalDate());
		chnPaymentBatch.setTranstime(messageHead.getLocalTime());
		chnPaymentBatch.setTotalqty(Long.valueOf(paymentBean.getTotalCount()));
		chnPaymentBatch.setTotalamt(Long.valueOf(paymentBean.getTotalAmt()));
		chnPaymentBatch.setOperatorcode(messageHead.getOperator());
		chnPaymentBatch.setOrigbatchno(paymentBean.getBatchNo());
		try {
			chnPaymentBatch = chnPaymentBatchDAO.savePaymentBatch(chnPaymentBatch);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB011");
		}
		List<PaymentDetaBean> detaList = paymentBean.getDetaList();
		List<CMT036Bean> msgList = Lists.newArrayList();
		List<ChnPaymentDetaDO> paymentDetaList = Lists.newArrayList();
		for(PaymentDetaBean detaBean : detaList){
			CMT036Bean cmt036Bean = new CMT036Bean();
			cmt036Bean.setDebtorUnitCode(detaBean.getDebtorUnitCode());
			cmt036Bean.setCommitDate(detaBean.getCommitDate());
			cmt036Bean.setTxId(tradeSequenceService.getCMTPaymentSeqNo());
			cmt036Bean.setCreditorBranchCode(detaBean.getCreditorBranchCode());
			cmt036Bean.setCreditorAccountNo(detaBean.getCreditorAccountNo());
			cmt036Bean.setCreditorName(detaBean.getCreditorName());
			cmt036Bean.setDebtorContract(detaBean.getDebtorContract());
			cmt036Bean.setDebtorAccountNo(detaBean.getDebtorAccountNo());
			cmt036Bean.setCurrencyCode(detaBean.getCurrencyCode());
			cmt036Bean.setAmount(detaBean.getAmount());
			cmt036Bean.setAccountType(detaBean.getAccountType());
			cmt036Bean.setPostscript(detaBean.getPostscript()==null?"":detaBean.getPostscript());
			cmt036Bean.setAdditionSubclass("Y0");
			cmt036Bean.setAdditionLength("000");
			msgList.add(cmt036Bean);
			
			ChnPaymentDetaDO chnCollectDeta = new ChnPaymentDetaDO();
			chnCollectDeta.setBatchtid(chnPaymentBatch.getTid());
			chnCollectDeta.setBatchno(chnPaymentBatch.getBatchno());
			chnCollectDeta.setChargingunit(detaBean.getDebtorUnitCode());
			chnCollectDeta.setTransdate(detaBean.getCommitDate());
			chnCollectDeta.setTxid(cmt036Bean.getTxId());
			//chnCollectDeta.setDebtorname(detaBean.getDebtor);
			chnCollectDeta.setDebtoraccountno(detaBean.getDebtorAccountNo());
			//chnCollectDeta.setDebtorbranchcode(detaBean.getDebtorBranchNo());
			chnCollectDeta.setCreditorbranchcode(detaBean.getCreditorBranchCode());
			chnCollectDeta.setCreditoraccountno(detaBean.getCreditorAccountNo());
			chnCollectDeta.setCurrencysymbol(detaBean.getCurrencyCode());
			chnCollectDeta.setAmount(Long.valueOf(detaBean.getAmount()));
			//chnCollectDeta.setMeterobjnumber(detaBean.getMeteringCode());
			//chnCollectDeta.setAuthnumber(detaBean.getEmpowerCode());
			chnCollectDeta.setPaymenttooltype(detaBean.getAccountType());
			//chnCollectDeta.setVouchernumber(detaBean.getVoucherCode());
			chnCollectDeta.setPostscript(detaBean.getPostscript());
			chnCollectDeta.setAdditional(cmt036Bean.getAdditionSubclass());
			chnCollectDeta.setAdditionallen(cmt036Bean.getAdditionLength());
			chnCollectDeta.setTxnseqno(detaBean.getTxnseqno());
			paymentDetaList.add(chnCollectDeta);
			PayPartyBean payPartyBean = new PayPartyBean();
			payPartyBean.setPayordno(cmt036Bean.getTxId());
			payPartyBean.setTxnseqno(detaBean.getTxnseqno());
			txnsLogDAO.updatePayInfo(payPartyBean);
			txnsLogDAO.updateTradeStatFlag(detaBean.getTxnseqno(), TradeStatFlagEnum.PAYING);
		}
		try {
			chnPaymentDetaDAO.saveBatchPaymentDeta(paymentDetaList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB012");
		}
		messageBean.setMessageBean(msgList);
		String message;
		try {
			message = messageAssemble.assemble(messageBean);
		} catch (HZBatchTransferMessageException e1) {
			e1.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB009");
		}
		
		ResultBean resultBean = null;
		//无应答报文，只能通过有无异常进行判断
		try {
			String resultMsg = messageSender.sendMessage(message, messageBean.getMessageTypeEnum().name());
			resultBean = new ResultBean(resultMsg);
		} catch (HZBatchFEException e) {
			e.printStackTrace();
			resultBean = new ResultBean(e.getCode(), e.getMessage());
		}
		return resultBean;
	}
	@Override
	public ResultBean signInAndSignOut(String operateType) throws HZBatchBusinessMessageException {
		
		GMT031Bean gmt031Bean = new GMT031Bean();
		gmt031Bean.setSignInCode(Constant.getInstance().getSenderCode());
		gmt031Bean.setSignInDate(DateUtil.getCurrentDate());
		gmt031Bean.setSignInTime(DateUtil.getCurrentTime());
		gmt031Bean.setRspCode("99");
		gmt031Bean.setOperator(Constant.getInstance().getOperatorCode());
		if("01".equals(operateType)){//签到
			gmt031Bean.setSignInType("1");	
		}else if ("02".equals(operateType)) {//签退
			gmt031Bean.setSignInType("0");	
		}
		//保存交易流水
		ChnSignInOutLogDO chnSignInOutLog = new ChnSignInOutLogDO();
		//赋值消息bean
		MessageBean messageBean = new MessageBean();
		messageBean.setMessageBean(gmt031Bean);
		messageBean.setMessageTypeEnum(MessageTypeEnum.GMT031);
		MessageHead messageHead = null;
		try {
			messageHead = messageAssemble.createMessageHead(messageBean);
		} catch (HZBatchTransferMessageException e) {
			e.printStackTrace();
			throw new HZBatchBusinessMessageException(e.getCode());
		}
		chnSignInOutLog.setMsgtype(messageHead.getMsgType());
		chnSignInOutLog.setServicetype(messageHead.getServiceType());
		chnSignInOutLog.setTransmitleg(messageHead.getSenderCode());
		chnSignInOutLog.setReceiver(messageHead.getReceiverCode());
		chnSignInOutLog.setTransdate(messageHead.getLocalDate());
		chnSignInOutLog.setTranstime(messageHead.getLocalTime());
		chnSignInOutLog.setOperatorcode(messageHead.getOperator());
		chnSignInOutLog.setSignpartycode(messageHead.getSenderCode());
		chnSignInOutLog.setSigndate(gmt031Bean.getSignInDate());
		chnSignInOutLog.setSigntime(gmt031Bean.getSignInTime());
		chnSignInOutLog.setSigntype(gmt031Bean.getSignInType());
		try {
			chnSignInOutLogDAO.saveSignInOutLog(chnSignInOutLog);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB013");
		}
		String message = null;
		try {
			message = messageAssemble.assemble(messageBean);
			logger.info(message);
		} catch (HZBatchTransferMessageException e) {
			e.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB009");
		}
		ResultBean resultBean = null;
		//无应答报文，只能通过有无异常进行判断
		try {
			String resultMsg = messageSender.sendMessage(message, messageBean.getMessageTypeEnum().name());
			resultBean = new ResultBean(resultMsg);
		} catch (HZBatchFEException e) {
			e.printStackTrace();
			resultBean = new ResultBean(e.getCode(), e.getMessage());
		}
		return resultBean;
	}
	
	

	@Override
	public ResultBean downLoadBill(String billDate, String operateType) throws HZBatchBusinessMessageException {
		ChnReconDownLogDO reconDownLog = new ChnReconDownLogDO();
		MessageBean messageBean = new MessageBean();
		if("01".equals(operateType)){//代收
			DLD032Bean dld032Bean = new DLD032Bean();
			dld032Bean.setSenderCode(Constant.getInstance().getSenderCode());
			dld032Bean.setDownloadDate(billDate);
			dld032Bean.setDownloadType("01");
			dld032Bean.setLocalDate(DateUtil.getCurrentDate());
			dld032Bean.setLocalTime(DateUtil.getCurrentTime());
			dld032Bean.setOperator(Constant.getInstance().getOperatorCode());
			messageBean.setMessageBean(dld032Bean);
			messageBean.setMessageTypeEnum(MessageTypeEnum.DLD032);
			MessageHead messageHead = null;
			try {
				messageHead = messageAssemble.createMessageHead(messageBean);
			} catch (HZBatchTransferMessageException e) {
				e.printStackTrace();
				throw new HZBatchBusinessMessageException(e.getCode());
			}
			reconDownLog.setMsgtype(messageHead.getMsgType());
			reconDownLog.setServicetype(messageHead.getServiceType());
			reconDownLog.setTransmitleg(messageHead.getSenderCode());
			reconDownLog.setReceiver(messageHead.getReceiverCode());
			reconDownLog.setTransdate(messageHead.getLocalDate());
			reconDownLog.setTranstime(messageHead.getLocalTime());
			reconDownLog.setOperatorcode(messageHead.getOperator());
			reconDownLog.setSendercode(Constant.getInstance().getSenderCode());
			reconDownLog.setDownloaddate(billDate);
			reconDownLog.setDownloadtype("01");
			reconDownLog.setLocaldate(dld032Bean.getLocalDate());
			reconDownLog.setLocaltime(dld032Bean.getLocalTime());
		}else if("02".equals(operateType)){//代付
			DLD037Bean dld037Bean = new DLD037Bean();
			dld037Bean.setSenderCode(Constant.getInstance().getSenderCode());
			dld037Bean.setDownloadDate(billDate);
			dld037Bean.setDownloadType("01");
			dld037Bean.setLocalDate(DateUtil.getCurrentDate());
			dld037Bean.setLocalTime(DateUtil.getCurrentTime());
			dld037Bean.setOperator(Constant.getInstance().getOperatorCode());
			messageBean.setMessageBean(dld037Bean);
			messageBean.setMessageTypeEnum(MessageTypeEnum.DLD037);
			MessageHead messageHead = null;
			try {
				messageHead = messageAssemble.createMessageHead(messageBean);
			} catch (HZBatchTransferMessageException e) {
				e.printStackTrace();
				throw new HZBatchBusinessMessageException(e.getCode());
			}
			reconDownLog.setMsgtype(messageHead.getMsgType());
			reconDownLog.setServicetype(messageHead.getServiceType());
			reconDownLog.setTransmitleg(messageHead.getSenderCode());
			reconDownLog.setReceiver(messageHead.getReceiverCode());
			reconDownLog.setTransdate(messageHead.getLocalDate());
			reconDownLog.setTranstime(messageHead.getLocalTime());
			reconDownLog.setOperatorcode(messageHead.getOperator());
			reconDownLog.setSendercode(Constant.getInstance().getSenderCode());
			reconDownLog.setDownloaddate(billDate);
			reconDownLog.setDownloadtype("01");
			reconDownLog.setLocaldate(dld037Bean.getLocalDate());
			reconDownLog.setLocaltime(dld037Bean.getLocalTime());
		}
		chnReconDownLogDAO.saveReconDownLog(reconDownLog);
		String message = null;
		try {
			message = messageAssemble.assemble(messageBean);
		} catch (HZBatchTransferMessageException e1) {
			e1.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB009");
		}
		ResultBean resultBean = null;
		//无应答报文，只能通过有无异常进行判断
		try {
			String resultMsg = messageSender.sendMessage(message, messageBean.getMessageTypeEnum().name());
			resultBean = new ResultBean(resultMsg);
		} catch (HZBatchFEException e) {
			e.printStackTrace();
			resultBean = new ResultBean(e.getCode(), e.getMessage());
		}
		
		
		return resultBean;
	}

	@Override
	public ResultBean protocolSign(List<ProtocolSignBean> protocolList) throws HZBatchBusinessMessageException {
		//赋值消息bean
		MessageBean messageBean = new MessageBean();
		messageBean.setMessageTypeEnum(MessageTypeEnum.AUT031);
		MessageHead messageHead = null;
		try {
			messageHead = messageAssemble.createMessageHead(messageBean);
		} catch (HZBatchTransferMessageException e) {
			e.printStackTrace();
			throw new HZBatchBusinessMessageException(e.getCode());
		}
		ChnProtocolSignBatchDO chnProtocolSignBatch = new ChnProtocolSignBatchDO();
		chnProtocolSignBatch.setBatchno(tradeSequenceService.getProtocolBatchNo());
		chnProtocolSignBatch.setMsgtype(messageHead.getMsgType());
		chnProtocolSignBatch.setServicetype(messageHead.getServiceType());
		chnProtocolSignBatch.setTransmitleg(messageHead.getSenderCode());
		chnProtocolSignBatch.setReceiver(messageHead.getReceiverCode());
		chnProtocolSignBatch.setTransdate(messageHead.getLocalDate());
		chnProtocolSignBatch.setTranstime(messageHead.getLocalTime());
		chnProtocolSignBatch.setOperatorcode(messageHead.getOperator());
		chnProtocolSignBatch.setOrigbatchno("");//留作以后处理
		try {
			chnProtocolSignBatch = chnProtocolSignBatchDAO.saveProtocolSignBatch(chnProtocolSignBatch);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB014");
		}
		List<AUT031Bean> beanList = Lists.newArrayList();
		List<ChnProtocolSignDetaDO> detaList = Lists.newArrayList();
		for(ProtocolSignBean protocol : protocolList){
			AUT031Bean aut031Bean = new AUT031Bean();
			aut031Bean.setOperateType(protocol.getOperateType());
			aut031Bean.setDebtorUnitCode(protocol.getDebtorUnitCode());
			aut031Bean.setDelegationCode(protocol.getDelegationCode());
			aut031Bean.setSignDate(protocol.getSignDate());
			aut031Bean.setMeteringCode(protocol.getMeteringCode());
			aut031Bean.setMeteringName(protocol.getMeteringName());
			aut031Bean.setDebtorBranchNo(protocol.getDebtorBranchNo());
			aut031Bean.setDebtorAccountNo(protocol.getDebtorAccountNo());
			aut031Bean.setDebtorName(protocol.getDebtorName());
			aut031Bean.setDebtorAddress(protocol.getDebtorAddress());
			beanList.add(aut031Bean);
			
			//保存明细
			ChnProtocolSignDetaDO protocolSignDeta = new ChnProtocolSignDetaDO();
			protocolSignDeta.setBatchtid(chnProtocolSignBatch.getTid());
			protocolSignDeta.setBatchno(chnProtocolSignBatch.getBatchno());
			protocolSignDeta.setOperatetype(protocol.getOperateType());
			protocolSignDeta.setChargingunit(protocol.getDebtorUnitCode());
			protocolSignDeta.setAuthnumber(protocol.getDelegationCode());
			protocolSignDeta.setSigndate(protocol.getSignDate());
			protocolSignDeta.setMeterobjnumber(protocol.getMeteringCode());
			protocolSignDeta.setMeterobjname(protocol.getMeteringName());
			protocolSignDeta.setDebtorname(protocol.getDebtorName());
			protocolSignDeta.setDebtoraccountno(protocol.getDebtorAccountNo());
			protocolSignDeta.setDebtorbranchcode(protocol.getDebtorBranchNo());
			protocolSignDeta.setDebtoraddress(protocol.getDebtorAddress());
			detaList.add(protocolSignDeta);
		}
		try {
			chnProtocolSignDetaDAO.batchSaveProtocolSignDeta(detaList);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB015");
		}
		messageBean.setMessageBean(beanList);
		String message = null;
		try {
			message = messageAssemble.assemble(messageBean);
		} catch (HZBatchTransferMessageException e1) {
			e1.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB009");
		}
		ResultBean resultBean = null;
		//无应答报文，只能通过有无异常进行判断
		try {
			String resultMsg = messageSender.sendMessage(message, messageBean.getMessageTypeEnum().name());
			resultBean = new ResultBean(resultMsg);
		} catch (HZBatchFEException e) {
			e.printStackTrace();
			resultBean = new ResultBean(e.getCode(), e.getMessage());
		}
		
		return resultBean;
	}

	@Override
	public ResultBean downloadProtocol(String debtorUnitCode, String signDate, String downLoadType) throws HZBatchBusinessMessageException {
		AUT032Bean aut032Bean = new AUT032Bean();
		aut032Bean.setDebtorUnitCode(debtorUnitCode);
		aut032Bean.setProtocolDate(signDate);
		aut032Bean.setDownLoadType(downLoadType);
		MessageBean messageBean = new MessageBean();
		messageBean.setMessageBean(aut032Bean);
		messageBean.setMessageTypeEnum(MessageTypeEnum.AUT032);
		MessageHead messageHead = null;
		try {
			messageHead = messageAssemble.createMessageHead(messageBean);
		} catch (HZBatchTransferMessageException e) {
			e.printStackTrace();
			throw new HZBatchBusinessMessageException(e.getCode());
		}
		ChnProtcolDownLogDO chnProtcolDownLog = new ChnProtcolDownLogDO();
		chnProtcolDownLog.setMsgtype(messageHead.getMsgType());
		chnProtcolDownLog.setServicetype(messageHead.getServiceType());
		chnProtcolDownLog.setTransmitleg(messageHead.getSenderCode());
		chnProtcolDownLog.setReceiver(messageHead.getReceiverCode());
		chnProtcolDownLog.setTransdate(messageHead.getLocalDate());
		chnProtcolDownLog.setTranstime(messageHead.getLocalTime());
		chnProtcolDownLog.setOperatorcode(messageHead.getOperator());
		//业务报文内容
		chnProtcolDownLog.setChargingunit(debtorUnitCode);
		chnProtcolDownLog.setDownloadtype(downLoadType);
		chnProtcolDownLog.setSigndate(signDate);
		try {
			chnProtcolDownLogDAO.saveProtcolDownLog(chnProtcolDownLog);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB016");
		}
		String message = null;
		try {
			message = messageAssemble.assemble(messageBean);
		} catch (HZBatchTransferMessageException e1) {
			e1.printStackTrace();
			throw new HZBatchBusinessMessageException("HZB009");
		}
		ResultBean resultBean = null;
		//无应答报文，只能通过有无异常进行判断
		try {
			String resultMsg = messageSender.sendMessage(message, messageBean.getMessageTypeEnum().name());
			resultBean = new ResultBean(resultMsg);
		} catch (HZBatchFEException e) {
			e.printStackTrace();
			resultBean = new ResultBean(e.getCode(), e.getMessage());
		}
		
		return resultBean;
	}

}
