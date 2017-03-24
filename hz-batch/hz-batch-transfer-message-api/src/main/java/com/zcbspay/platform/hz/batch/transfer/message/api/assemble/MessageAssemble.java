package com.zcbspay.platform.hz.batch.transfer.message.api.assemble;

import com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageBean;
import com.zcbspay.platform.hz.batch.transfer.message.api.bean.MessageHead;


/**
 * 报文组装接口 1.生成报文头 2.生成数字签名域 3.组装报文
 * 
 * @author guojia
 * @version
 * @date 2017年2月23日 下午5:54:29
 * @since
 */
public interface MessageAssemble {

    /**
     * 生成报文头
     * 
     * @param bean
     * @return
     */
    public MessageHead createMessageHead(MessageBean bean);

    /**
     * 数字签名方法，根据报文bean以及报文的类型，对报文中的关键要素进行签名串的拼接，调用软加密服务的签名方法生成数字签名字符串
     * 
     * @param bean
     * @return
     */
    public String signature(MessageBean bean);

    /**
     * 组装报文方法，返回报文字符串 报文格式：报文头+报文体（内部数字鉴别码）
     * 
     * @param bean
     * @return
     */
    public String assemble(MessageBean messageBean);
}
