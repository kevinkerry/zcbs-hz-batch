package com.zcbspay.platform.hz.batch.business.message.sequence.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.zcbspay.platform.hz.batch.business.message.sequence.TradeSequenceService;

@Service
public class TradeSequenceServiceImpl implements TradeSequenceService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	private static final String CMT_COLLECT_KEY="SEQUENCE:CMTCOLLECT";
	private static final String CMT_PAYMENT_KEY="SEQUENCE:CMTPAYMENT";
	private static final String CMT_BATCH_KEY="SEQUENCE:CMTBATCH";
	private static final String CMT_PROTOCOL_KEY="SEQUENCE:CMTBATCH";
	@Override
	public String getCMTCollectSeqNo() {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		Long increment = opsForValue.increment(CMT_COLLECT_KEY, 1);
		if(increment>=99999){
			opsForValue.set(CMT_COLLECT_KEY, "0");
		}
		DecimalFormat df = new DecimalFormat("00000");
		String seqNo = "031" + df.format(increment);
		return seqNo;
	}
	@Override
	public String getCMTPaymentSeqNo() {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		Long increment = opsForValue.increment(CMT_PAYMENT_KEY, 1);
		if(increment>=99999){
			opsForValue.set(CMT_PAYMENT_KEY, "0");
		}
		DecimalFormat df = new DecimalFormat("00000");
		String seqNo = "036" + df.format(increment);
		return seqNo;
	}
	@Override
	public String getCollectionBatchNo() {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		Long increment = opsForValue.increment(CMT_BATCH_KEY, 1);
		if(increment>=99999999){
			opsForValue.set(CMT_BATCH_KEY, "0");
		}
		DecimalFormat df = new DecimalFormat("00000000");
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String seqNo = sdf.format(new Date())+ "HZC" + df.format(increment);
		return seqNo;
	}

	@Override
	public String getPaymentBatchNo() {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		Long increment = opsForValue.increment(CMT_BATCH_KEY, 1);
		if(increment>=99999999){
			opsForValue.set(CMT_BATCH_KEY, "0");
		}
		DecimalFormat df = new DecimalFormat("00000000");
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String seqNo = sdf.format(new Date())+ "HZP" + df.format(increment);
		return seqNo;
	}
	@Override
	public String getProtocolBatchNo() {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		Long increment = opsForValue.increment(CMT_PROTOCOL_KEY, 1);
		if(increment>=99999999){
			opsForValue.set(CMT_PROTOCOL_KEY, "0");
		}
		DecimalFormat df = new DecimalFormat("00000000");
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String seqNo = sdf.format(new Date())+ "HZS" + df.format(increment);
		return seqNo;
	}

}
