package com.zcbspay.platform.hz.batch.fe.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zcbspay.platform.hz.batch.common.utils.DateUtil;



/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年10月13日 上午9:32:56
 * @since
 */
public class Constant {

    private static final Logger log = LoggerFactory.getLogger(Constant.class);

    private String filePath;
    private String IP;
    private String port;
    private String timeOut;
    private boolean canRun;
    private String refresh_interval;
    private static Constant constant;
    private String local_IP;
    private String local_port;
    
    private String jni_IP;
    private String jni_prot;
    private String jni_file_path;
    private String time_out;
    
    public static synchronized Constant getInstance() {
        if (constant == null) {
            constant = new Constant();
        }
        return constant;
    }

    private Constant() {
        refresh();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (canRun) {
                    try {
                        refresh();
                        int interval = NumberUtils.toInt(refresh_interval, 60) * 1000;// 刷新间隔，单位：秒
                       // log.info("refresh Constant datetime:" + DateUtil.getCurrentDateTime());
                        Thread.sleep(interval);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void refresh() {
        try {
            String path = "/home/fe/";
            File file = new File(path + "hz_batch_fe.properties");
            if (!file.exists()) {
                path = getClass().getResource("/").getPath();
                file = null;
            }
            Properties prop = new Properties();
            InputStream stream = null;
            stream = new BufferedInputStream(new FileInputStream(new File(path + "hz_batch_fe.properties")));
            prop.load(stream);
            filePath = prop.getProperty("file_path");
            IP =  prop.getProperty("IP");
            port = prop.getProperty("port");
            local_IP =  prop.getProperty("local_IP");
            local_port = prop.getProperty("local_port");
            timeOut = prop.getProperty("time_out");
            
            jni_IP = prop.getProperty("jni_IP");
            jni_prot = prop.getProperty("jni_prot");
            jni_file_path = prop.getProperty("jni_file_path");
            time_out = prop.getProperty("time_out");
            canRun = true;
            refresh_interval = prop.getProperty("refresh_interval");
        }
        catch (FileNotFoundException e) {
            log.error("FileNotFoundException", e);
        }
        catch (IOException e) {
            log.error("IOException", e);
        }
    }

    

  
	public boolean isCanRun() {
        return canRun;
    }

    public void setCanRun(boolean canRun) {
        this.canRun = canRun;
    }

    public String getRefresh_interval() {
        return refresh_interval;
    }

    public void setRefresh_interval(String refresh_interval) {
        this.refresh_interval = refresh_interval;
    }

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getLocal_IP() {
		return local_IP;
	}

	public void setLocal_IP(String local_IP) {
		this.local_IP = local_IP;
	}

	public String getLocal_port() {
		return local_port;
	}

	public void setLocal_port(String local_port) {
		this.local_port = local_port;
	}

	public String getJni_IP() {
		return jni_IP;
	}

	public void setJni_IP(String jni_IP) {
		this.jni_IP = jni_IP;
	}

	public String getJni_prot() {
		return jni_prot;
	}

	public void setJni_prot(String jni_prot) {
		this.jni_prot = jni_prot;
	}

	public String getJni_file_path() {
		return jni_file_path;
	}

	public void setJni_file_path(String jni_file_path) {
		this.jni_file_path = jni_file_path;
	}

	public String getTime_out() {
		return time_out;
	}

	public void setTime_out(String time_out) {
		this.time_out = time_out;
	}

	
}
