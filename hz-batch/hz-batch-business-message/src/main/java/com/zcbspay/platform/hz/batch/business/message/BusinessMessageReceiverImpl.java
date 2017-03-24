package com.zcbspay.platform.hz.batch.business.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcbspay.platform.hz.batch.business.message.api.BusinessMessageReceiver;
import com.zcbspay.platform.hz.batch.business.message.api.bean.MessageBean;
import com.zcbspay.platform.hz.batch.business.message.api.bean.MessageTypeEnum;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnProtcolDownLogDAO;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnReconDownLogDAO;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnSignInOutLogDAO;
import com.zcbspay.platform.hz.batch.business.message.dao.ChnTxnDAO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnProtcolDownLogDO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnReconDownLogDO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnSignInOutLogDO;
import com.zcbspay.platform.hz.batch.business.message.pojo.ChnTxnDO;
import com.zcbspay.platform.hz.batch.common.utils.DateUtil;
import com.zcbspay.platform.hz.batch.dao.ChnAgreementDAO;
import com.zcbspay.platform.hz.batch.message.bean.CollectBillBean;
import com.zcbspay.platform.hz.batch.message.bean.MessageHead;
import com.zcbspay.platform.hz.batch.message.bean.PaymentBillBean;
import com.zcbspay.platform.hz.batch.message.bean.ProtocolDetaBean;
import com.zcbspay.platform.hz.batch.message.bean.request.GMT031Bean;
import com.zcbspay.platform.hz.batch.message.bean.response.AUT032RSPBean;
import com.zcbspay.platform.hz.batch.message.bean.response.DLD032RSPBean;
import com.zcbspay.platform.hz.batch.message.bean.response.DLD037RSPBean;
import com.zcbspay.platform.hz.batch.pojo.ChnAgreementDO;



@Service
@com.alibaba.dubbo.config.annotation.Service(version="1.0",retries=0)
public class BusinessMessageReceiverImpl implements BusinessMessageReceiver {

	@Autowired
	private ChnSignInOutLogDAO chnSignInOutLogDAO;
	@Autowired
	private ChnTxnDAO chnTxnDAO;
	@Autowired
	private ChnReconDownLogDAO chnReconDownLogDAO;
	@Autowired
	private ChnProtcolDownLogDAO chnProtcolDownLogDAO;
	@Autowired
	private ChnAgreementDAO chnAgreementDAO;
	@Override
	public void signInAndSignOut(MessageBean messageBean) {
		ChnSignInOutLogDO chnSignInOutLog = new ChnSignInOutLogDO();
		GMT031Bean gmt031Bean = (GMT031Bean) messageBean.getMessageBean();
		MessageHead messageHead = new MessageHead();
		chnSignInOutLog.setMsgtype(messageHead .getMsgType());
		chnSignInOutLog.setServicetype(messageHead.getServiceType());
		chnSignInOutLog.setTransmitleg(messageHead.getSenderCode());
		chnSignInOutLog.setReceiver(messageHead.getReceiverCode());
		chnSignInOutLog.setTransdate(messageHead.getLocalDate());
		chnSignInOutLog.setTranstime(messageHead.getLocalTime());
		chnSignInOutLog.setRspcode(messageHead.getRspCode());
		chnSignInOutLog.setOperatorcode(messageHead.getOperator());
		chnSignInOutLog.setSignpartycode(messageHead.getSenderCode());
		chnSignInOutLog.setSigndate(gmt031Bean.getSignInDate());
		chnSignInOutLog.setSigntime(gmt031Bean.getSignInTime());
		chnSignInOutLog.setSigntype(gmt031Bean.getSignInType());
		chnSignInOutLogDAO.saveSignInOutLog(chnSignInOutLog);
		
		//redis中加入签到状态
	}

	@Override
	public void downloadProtocol(MessageBean messageBean) {
		AUT032RSPBean aut032rspBean = (AUT032RSPBean) messageBean.getMessageBean();
		MessageHead messageHead = aut032rspBean.getMessageHead();
		ChnProtcolDownLogDO chnProtcolDownLog = new ChnProtcolDownLogDO();
		chnProtcolDownLog.setMsgtype(messageHead.getMsgType());
		chnProtcolDownLog.setServicetype(messageHead.getServiceType());
		chnProtcolDownLog.setTransmitleg(messageHead.getSenderCode());
		chnProtcolDownLog.setReceiver(messageHead.getReceiverCode());
		chnProtcolDownLog.setTransdate(messageHead.getLocalDate());
		chnProtcolDownLog.setTranstime(messageHead.getLocalTime());
		chnProtcolDownLog.setOperatorcode(messageHead.getOperator());
		chnProtcolDownLogDAO.saveProtcolDownLog(chnProtcolDownLog);
		
		List<ProtocolDetaBean> rspBeanList = aut032rspBean.getDetaList();
		ChnAgreementDO chnAgreement = null;
		for(ProtocolDetaBean bean : rspBeanList){
			chnAgreement = null;
			if("1".equals(bean.getOperateType())){//新增
				 chnAgreement = new ChnAgreementDO();
				 chnAgreement.setChargingunit(bean.getDebtorUnitCode());
				 chnAgreement.setEntrustnum(bean.getDelegationCode());
				 chnAgreement.setMeterobjnumber(bean.getMeteringCode());
				 chnAgreement.setDebtorbranchcode(bean.getDebtorBranchNo());
				 chnAgreement.setDebtorname(bean.getDebtorName());
				 chnAgreement.setDebtoraccountno(bean.getDebtorAccountNo());
				 chnAgreement.setStatus("00");
				 chnAgreement.setRspdate(DateUtil.getCurrentDateTime());
				 chnAgreement.setRspcode(bean.getRspCode());
				 chnAgreementDAO.saveAgreement(chnAgreement);
			}else if("2".equals(bean.getOperateType())){//撤销
				 chnAgreement = chnAgreementDAO.getAgreement(bean.getDebtorUnitCode(), bean.getDebtorAccountNo());
				 if(chnAgreement!=null){
					 if("00".equals(bean.getRspCode())){//撤销成功
						 chnAgreement.setStatus("09");
						 chnAgreementDAO.updateAgreement(chnAgreement);
					 }
				 }
			}
		}
	}

	@Override
	public void downLoadBill(MessageBean messageBean) {
		ChnReconDownLogDO reconDownLog = new ChnReconDownLogDO();
		if(messageBean.getMessageTypeEnum()==MessageTypeEnum.DLD032){//代收对账处理
			DLD032RSPBean dld032rspBean = (DLD032RSPBean) messageBean.getMessageBean();
			MessageHead messageHead = dld032rspBean.getMessageHead();
			reconDownLog.setMsgtype(messageHead .getMsgType());
			reconDownLog.setServicetype(messageHead.getServiceType());
			reconDownLog.setTransmitleg(messageHead.getSenderCode());
			reconDownLog.setReceiver(messageHead.getReceiverCode());
			reconDownLog.setTransdate(messageHead.getLocalDate());
			reconDownLog.setTranstime(messageHead.getLocalTime());
			reconDownLog.setOperatorcode(messageHead.getOperator());
			reconDownLog.setSendercode(dld032rspBean.getSenderCode());
			reconDownLog.setDownloaddate(dld032rspBean.getDownLoadDate());
			reconDownLog.setDownloadtype(dld032rspBean.getDownLoadType());
			reconDownLog.setLocaldate(dld032rspBean.getLocalDate());
			reconDownLog.setLocaltime(dld032rspBean.getLocalTime());
			reconDownLog = chnReconDownLogDAO.saveReconDownLog(reconDownLog);
			List<CollectBillBean> billDetaList = dld032rspBean.getBillDetaList();
			for(CollectBillBean collectBillBean:billDetaList){
				ChnTxnDO chnTxn = new ChnTxnDO();
				chnTxn.setChargingunit(collectBillBean.getDebtorUnitCode());
				chnTxn.setTransdate(collectBillBean.getCommitDate());
				chnTxn.setTxid(collectBillBean.getTxId());
				chnTxn.setCreditorbranchcode(collectBillBean.getCreditorBranchCode());
				chnTxn.setCreditoraccountno(collectBillBean.getCreditorAccountNo());
				chnTxn.setDebtorbranchcode(collectBillBean.getDebtorBranchNo());
				chnTxn.setDebtoraccountno(collectBillBean.getDebtorAccountNo());
				chnTxn.setDebtorname(collectBillBean.getDebtorName());
				chnTxn.setCurrencysymbol(collectBillBean.getCurrencyCode());
				chnTxn.setAmount(Long.valueOf(collectBillBean.getAmount()));
				chnTxn.setMeterobjnumber(collectBillBean.getMeteringCode());
				chnTxn.setAuthnumber(collectBillBean.getEmpowerCode());
				chnTxn.setVouchernumber(collectBillBean.getVoucherCode());
				chnTxn.setRspcode(collectBillBean.getRspCode());
				chnTxn.setSettledate(collectBillBean.getSettDate());
				chnTxn.setSettlestatus(collectBillBean.getSettFlag());
				chnTxn.setLogid(reconDownLog.getTid());
				chnTxnDAO.saveChnTxn(chnTxn);
			}
		}else if(messageBean.getMessageTypeEnum()==MessageTypeEnum.DLD037){//代付对账处理
			DLD037RSPBean dld037rspBean = (DLD037RSPBean) messageBean.getMessageBean();
			MessageHead messageHead = dld037rspBean.getMessageHead();
			reconDownLog.setMsgtype(messageHead .getMsgType());
			reconDownLog.setServicetype(messageHead.getServiceType());
			reconDownLog.setTransmitleg(messageHead.getSenderCode());
			reconDownLog.setReceiver(messageHead.getReceiverCode());
			reconDownLog.setTransdate(messageHead.getLocalDate());
			reconDownLog.setTranstime(messageHead.getLocalTime());
			reconDownLog.setOperatorcode(messageHead.getOperator());
			reconDownLog.setSendercode(dld037rspBean.getSenderCode());
			reconDownLog.setDownloaddate(dld037rspBean.getDownLoadDate());
			reconDownLog.setDownloadtype(dld037rspBean.getDownLoadType());
			reconDownLog.setLocaldate(dld037rspBean.getLocalDate());
			reconDownLog.setLocaltime(dld037rspBean.getLocalTime());
			reconDownLog = chnReconDownLogDAO.saveReconDownLog(reconDownLog);
			List<PaymentBillBean> billDetaList = dld037rspBean.getBillDetaList();
			for(PaymentBillBean paymentBillBean:billDetaList){
				ChnTxnDO chnTxn = new ChnTxnDO();
				chnTxn.setChargingunit(paymentBillBean.getDebtorUnitCode());
				chnTxn.setTransdate(paymentBillBean.getCommitDate());
				chnTxn.setTxid(paymentBillBean.getTxId());
				chnTxn.setCreditorbranchcode(paymentBillBean.getCreditorBranchCode());
				chnTxn.setCreditoraccountno(paymentBillBean.getCreditorAccountNo());
				chnTxn.setCreditorname(paymentBillBean.getCreditorName());
				chnTxn.setDebtorbranchcode(paymentBillBean.getDebtorBranchNo());
				chnTxn.setDebtoraccountno(paymentBillBean.getDebtorAccountNo());
				chnTxn.setCurrencysymbol(paymentBillBean.getCurrencyCode());
				chnTxn.setAmount(Long.valueOf(paymentBillBean.getAmount()));
				chnTxn.setRspcode(paymentBillBean.getRspCode());
				chnTxn.setSettledate(paymentBillBean.getSettDate());
				chnTxn.setSettlestatus(paymentBillBean.getSettFlag());
				chnTxn.setLogid(reconDownLog.getTid());
				chnTxnDAO.saveChnTxn(chnTxn);
			}
		}
	}

}
