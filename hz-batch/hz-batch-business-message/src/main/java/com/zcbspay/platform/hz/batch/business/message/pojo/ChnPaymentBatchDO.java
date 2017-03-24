package com.zcbspay.platform.hz.batch.business.message.pojo;
// default package
// Generated 2017-3-22 14:50:41 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TChnPaymentBatch generated by hbm2java
 */
@Entity
@Table(name = "T_CHN_PAYMENT_BATCH")
public class ChnPaymentBatchDO implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4235166249149411947L;
	private long tid;
	private String batchno;
	private String msgtype;
	private String servicetype;
	private String transmitleg;
	private String receiver;
	private String transdate;
	private String transtime;
	private Long totalqty;
	private Long totalamt;
	private String rsprejectcode;
	private String respmsg;
	private String rspdate;
	private String operatorcode;
	private String notes;
	private String remarks;
	private String origbatchno;
	
	public ChnPaymentBatchDO() {
	}

	public ChnPaymentBatchDO(long tid) {
		this.tid = tid;
	}

	public ChnPaymentBatchDO(long tid, String batchno, String msgtype,
			String servicetype, String transmitleg, String receiver,
			String transdate, String transtime, Long totalqty, Long totalamt,
			String rsprejectcode, String respmsg, String rspdate,
			String operatorcode, String notes, String remarks) {
		this.tid = tid;
		this.batchno = batchno;
		this.msgtype = msgtype;
		this.servicetype = servicetype;
		this.transmitleg = transmitleg;
		this.receiver = receiver;
		this.transdate = transdate;
		this.transtime = transtime;
		this.totalqty = totalqty;
		this.totalamt = totalamt;
		this.rsprejectcode = rsprejectcode;
		this.respmsg = respmsg;
		this.rspdate = rspdate;
		this.operatorcode = operatorcode;
		this.notes = notes;
		this.remarks = remarks;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_chn_payment_batch") 
	@SequenceGenerator(name="seq_chn_payment_batch",sequenceName="SEQ_CHN_PAYMENT_BATCH",allocationSize=1)
	@Column(name = "TID", unique = true, nullable = false, precision = 12, scale = 0)
	public long getTid() {
		return this.tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	@Column(name = "BATCHNO", length = 32)
	public String getBatchno() {
		return this.batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	@Column(name = "MSGTYPE", length = 6)
	public String getMsgtype() {
		return this.msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	@Column(name = "SERVICETYPE", length = 2)
	public String getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	@Column(name = "TRANSMITLEG", length = 10)
	public String getTransmitleg() {
		return this.transmitleg;
	}

	public void setTransmitleg(String transmitleg) {
		this.transmitleg = transmitleg;
	}

	@Column(name = "RECEIVER", length = 10)
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "TRANSDATE", length = 8)
	public String getTransdate() {
		return this.transdate;
	}

	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}

	@Column(name = "TRANSTIME", length = 6)
	public String getTranstime() {
		return this.transtime;
	}

	public void setTranstime(String transtime) {
		this.transtime = transtime;
	}

	@Column(name = "TOTALQTY", precision = 12, scale = 0)
	public Long getTotalqty() {
		return this.totalqty;
	}

	public void setTotalqty(Long totalqty) {
		this.totalqty = totalqty;
	}

	@Column(name = "TOTALAMT", precision = 12, scale = 0)
	public Long getTotalamt() {
		return this.totalamt;
	}

	public void setTotalamt(Long totalamt) {
		this.totalamt = totalamt;
	}

	@Column(name = "RSPREJECTCODE", length = 4)
	public String getRsprejectcode() {
		return this.rsprejectcode;
	}

	public void setRsprejectcode(String rsprejectcode) {
		this.rsprejectcode = rsprejectcode;
	}

	@Column(name = "RESPMSG", length = 256)
	public String getRespmsg() {
		return this.respmsg;
	}

	public void setRespmsg(String respmsg) {
		this.respmsg = respmsg;
	}

	@Column(name = "RSPDATE", length = 14)
	public String getRspdate() {
		return this.rspdate;
	}

	public void setRspdate(String rspdate) {
		this.rspdate = rspdate;
	}

	@Column(name = "OPERATORCODE", length = 8)
	public String getOperatorcode() {
		return this.operatorcode;
	}

	public void setOperatorcode(String operatorcode) {
		this.operatorcode = operatorcode;
	}

	@Column(name = "NOTES", length = 256)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "REMARKS", length = 256)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "ORIGBATCHNO", length = 32)
	public String getOrigbatchno() {
		return this.origbatchno;
	}

	public void setOrigbatchno(String origbatchno) {
		this.origbatchno = origbatchno;
	}

}
