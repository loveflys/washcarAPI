package com.qgil.washcar.API.entity;

import com.qgil.washcar.API.utils.SingletonManager;

import java.util.Date;

/**
 * 应答报文头
 * @author wangjie
 *
 */
public class ChannelMsgResHead {

	private String userName;	//登录用户名
	private String transactionCode;	//交易码
	private String sendTime;	//发送时间
	private String chanid;		//业务来源
	private String procd;		//处理码
	private String proinfo;		//处理描述
	
	public ChannelMsgResHead() {
		this.transactionCode = "AAAAAA";
		this.sendTime = SingletonManager.getIstance().format(new Date());
		this.chanid = PayConst.CHANID;
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
	public String getProcd() {
		return procd;
	}
	public void setProcd(String procd) {
		this.procd = procd;
	}
	public String getProinfo() {
		return proinfo;
	}
	public void setProinfo(String proinfo) {
		this.proinfo = proinfo;
	}
}
