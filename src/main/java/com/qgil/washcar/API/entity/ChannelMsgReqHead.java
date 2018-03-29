package com.qgil.washcar.API.entity;

import com.qgil.washcar.API.utils.SingletonManager;

import java.util.Date;

/**
 * 请求报文头
 * @author wangjie
 *
 */
public class ChannelMsgReqHead {

	private String userName;	//登录用户
	private String transactionCode;	//交易码
	private String sendTime;	//发送时间
	private String chanid;		//业务来源 01xx 网上服务大厅， 02xx 舱单传输系统
	private String loginType;	//登录类型   0支付平台，1网上服务大厅， 2 舱单传输系统
	
	public ChannelMsgReqHead() {}
	
	public ChannelMsgReqHead(String transactionCode) {
		this.transactionCode = transactionCode;
		this.sendTime = SingletonManager.getIstance().format(new Date());
		this.chanid = PayConst.CHANID;
		this.loginType = PayConst.LOGIN_TYPE;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getChanid() {
		return chanid;
	}
	public void setChanid(String chanid) {
		this.chanid = chanid;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
}
