package com.qgil.washcar.API.entity;

/**
 * 支付申请
 * @author wangjie
 *
 */
public class PayApply {

	private ChannelMsgReqHead reqHead;	//请求报文头
	private ChannelMsgResHead resHead;	//应答报文头
	private String orderno;	//订单号
	private String paychan;	//支付渠道
	private String authcode;//主扫模式条码、二维码支付条码信息， 条码支付的授权码（条码抢扫手机扫到的一串数字）
	private String qrcodeurl;//付款二维码地址
	private String payurl;	//公众账号/服务窗订单支付地址
	private String orderSts;//订单状态
	
	public PayApply() {
		super();
	}
	
	public PayApply(String orderno, String paychan) {
		this.reqHead = new ChannelMsgReqHead("Pus030");
		this.orderno = orderno;
		this.paychan = paychan;
	}
	
	public ChannelMsgReqHead getReqHead() {
		return reqHead;
	}
	public void setReqHead(ChannelMsgReqHead reqHead) {
		this.reqHead = reqHead;
	}
	public ChannelMsgResHead getResHead() {
		return resHead;
	}
	public void setResHead(ChannelMsgResHead resHead) {
		this.resHead = resHead;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getPaychan() {
		return paychan;
	}
	public void setPaychan(String paychan) {
		this.paychan = paychan;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public String getQrcodeurl() {
		return qrcodeurl;
	}
	public void setQrcodeurl(String qrcodeurl) {
		this.qrcodeurl = qrcodeurl;
	}
	public String getPayurl() {
		return payurl;
	}
	public void setPayurl(String payurl) {
		this.payurl = payurl;
	}
	public String getOrderSts() {
		return orderSts;
	}
	public void setOrderSts(String orderSts) {
		this.orderSts = orderSts;
	}
}
