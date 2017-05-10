package com.zcbspay.platform.hz.batch.fe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.zcbspay.platform.hz.batch.common.utils.DateUtil;
import com.zcbspay.platform.hz.batch.fe.api.MessageSender;
import com.zcbspay.platform.hz.batch.fe.enums.CommunicationModeEnum;
import com.zcbspay.platform.hz.batch.fe.enums.MessageTypeEnum;
import com.zcbspay.platform.hz.batch.fe.exception.HZBatchFEException;
import com.zcbspay.platform.hz.batch.fe.socket.BaseSocketShortClient;
import com.zcbspay.platform.hz.batch.fe.socket.message.RequestMessage;
import com.zcbspay.platform.hz.batch.fe.util.Constant;
import com.zcbspay.platform.hz.batch.transfer.message.api.unpack.MessageUnpack;
import com.zcbspay.platform.jni.YHT_JNI;

@Service("messageSender")
public class MessageSenderImpl implements MessageSender {

	private final static Logger logger = LoggerFactory
			.getLogger(MessageSenderImpl.class);
	@Reference(version = "1.0")
	private MessageUnpack messageUnpack;

	@Override
	public String sendMessage(String message, String msgType)
			throws HZBatchFEException {
		MessageTypeEnum messageType = MessageTypeEnum.valueOf(msgType);
		String fileName = null;
		RequestMessage msg = new RequestMessage();
		msg.setFilePaht(Constant.getInstance().getFilePath());
		switch (messageType) {
		case AUT031:// 代收业务委托协议签约报文AUT031,无用

			break;
		case AUT032:// 代收业务委托协议下载报文AUT032,无用

			break;
		case CMT031:// 借记报文CMT031 批量代收
			// 1.将报文内容保存在本地的服务器的文本文件中，
			try {
				fileName = fileWrite(message, messageType);
				msg.setMsgType("01");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case CMT036:// 贷记报文CMT036 批量代付
			try {
				fileName = fileWrite(message, messageType);
				msg.setMsgType("01");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case DLD032:// 收费明细下载报文DLD032
			try {
				fileName = fileWrite(message, messageType);
				msg.setMsgType("02");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case DLD037:// 付费明细下载报文DLD037
			try {
				fileName = fileWrite(message, messageType);
				msg.setMsgType("02");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case GMT031:// 签到/签退报文GMT031，探测类，无实际签到签退功能，渠道方不校验
			try {
				msg.setMsgType("02");
				fileName = fileWrite(message, messageType);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			throw new HZBatchFEException("HZB007");
		}
		String path = Constant.getInstance().getFilePath()+DateUtil.getCurrentDate()+File.separator;
		msg.setFileName(path + fileName);
		msg.setIp(Constant.getInstance().getLocal_IP());
		msg.setPort(Constant.getInstance().getLocal_port());
		msg.setTimeOut(Constant.getInstance().getTimeOut());

		CommunicationModeEnum communicationMode = CommunicationModeEnum.JNI;
		if (communicationMode == CommunicationModeEnum.JNI) {
			logger.info("RequestMessage json:" + JSON.toJSONString(msg));
			YHT_JNI yht_JNI = new YHT_JNI();
			if (msg.getMsgType().equals("01")) {// 无应答报文或文件
				int retcode = 0;/*yht_JNI.sendYHTBWFile(Constant.getInstance()
						.getJni_IP(), Constant.getInstance().getJni_prot(), msg
						.getFileName(), path);*/
				logger.info("sendYHTBWFile return code:" + retcode);
			} else if (msg.getMsgType().equals("02")) {// 有应答报文或文件

				String rcvedFName = yht_JNI.recvYHTBWFile(Constant
						.getInstance().getJni_IP(), Constant.getInstance()
						.getJni_prot(), msg.getFileName(), path);
				logger.info("recvYHTBWFile return code:" + rcvedFName);
				if (rcvedFName.equals("-1")) {//
					logger.info("连接出错");
				} else if (rcvedFName.equals("-2")) {//
					logger.info("发送报文数据出错");
				} else if (rcvedFName.equals("-3")) {//
					logger.info("接收报文数据出错");
				} else {
					try {
						fileRead(rcvedFName);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		} else if (communicationMode == CommunicationModeEnum.SOCKET) {
			BaseSocketShortClient client = new BaseSocketShortClient(Constant
					.getInstance().getIP(), Integer.valueOf(Constant
					.getInstance().getPort()), 30);
			try {
				client.sendMessage(msg.toXML().getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "success";
	}

	public String fileWrite(String fileContent, MessageTypeEnum messageType)
			throws IOException {
		String path = Constant.getInstance().getFilePath()+DateUtil.getCurrentDate()+File.separator;
		createDir(path);
		String fileName = messageType.name() + DateUtil.getCurrentTime() +"S.Z01";
		File file = new File(path + fileName);
		if (!file.exists()) {
			boolean flag = file.createNewFile();
			logger.info("create file:{},create file name:{}",
					file.getAbsolutePath(), flag);
		}
		FileWriter fw = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fw);
		// 这里需要转码，由utf-8转为gbk
		String fileContent_gbk = new String(fileContent.getBytes(), "UTF-8");
		bufferedWriter.write(fileContent_gbk);
		bufferedWriter.flush();
		bufferedWriter.close();
		fw.close();
		transferFile(file.getAbsolutePath(), file.getAbsolutePath());
		return fileName;
	}

	public void fileRead(String fileName) throws IOException {
		fileName = fileName.substring(0, fileName.lastIndexOf("/"))
				.toLowerCase() + fileName.substring(fileName.lastIndexOf("/"));
		File file = new File(fileName);
		if (file.exists()) {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String content = br.readLine();
			System.out.println(messageUnpack);
			messageUnpack.unpack(content);
			br.close();
			fr.close();
		}
	}

	public void transferFile(String srcFileName, String destFileName)
			throws IOException {
		String line_separator = System.getProperty("line.separator");
		FileInputStream fis = new FileInputStream(srcFileName);
		StringBuffer content = new StringBuffer();
		DataInputStream in = new DataInputStream(fis);
		BufferedReader d = new BufferedReader(
				new InputStreamReader(in, "UTF-8"));// , "UTF-8"
		String line = null;
		while ((line = d.readLine()) != null)
			content.append(line);
		d.close();
		in.close();
		fis.close();

		Writer ow = new OutputStreamWriter(new FileOutputStream(destFileName),
				"GBK");
		ow.write(content.toString());
		ow.close();
	}

	// 创建目录
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {// 判断目录是否存在
			logger.info("创建目录失败，目标目录已存在！");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
			destDirName = destDirName + File.separator;
		}
		if (dir.mkdirs()) {// 创建目标目录
			logger.info("创建目录成功！" + destDirName);
			return true;
		} else {
			logger.info("创建目录失败！");
			return false;
		}
	}
	
	
}
