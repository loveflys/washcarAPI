package com.qgil.washcar.API.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 支付结果
 * @author wangjie
 *
 */
public class PayResult {

	private ChannelMsgReqHead reqHead;//请求报文头
	private ChannelMsgResHead resHead;//应答报文头
	private String orderNo;//支付平台订单号
	private String orderSts;//订单状态
	private String payChannelCd;//支付渠道编号
	private String payChannelNm;//支付渠道名称
	private String payTime;		//支付时间
	private String payNo;	//支付渠道流水号
	private String dbtrNo;	//付款账号
	private String dbtrNm;	//付款户名
	private BigDecimal amt;	//订单金额
	private List<PayResultDetail> noticeDetail;//通知明细
	
	public PayResult() {
		this.reqHead = new ChannelMsgReqHead("Pus018");
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderSts() {
		return orderSts;
	}
	public void setOrderSts(String orderSts) {
		this.orderSts = orderSts;
	}
	public String getPayChannelCd() {
		return payChannelCd;
	}
	public void setPayChannelCd(String payChannelCd) {
		this.payChannelCd = payChannelCd;
	}
	public String getPayChannelNm() {
		return payChannelNm;
	}
	public void setPayChannelNm(String payChannelNm) {
		this.payChannelNm = payChannelNm;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getDbtrNo() {
		return dbtrNo;
	}
	public void setDbtrNo(String dbtrNo) {
		this.dbtrNo = dbtrNo;
	}
	public String getDbtrNm() {
		return dbtrNm;
	}
	public void setDbtrNm(String dbtrNm) {
		this.dbtrNm = dbtrNm;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public List<PayResultDetail> getNoticeDetail() {
		return noticeDetail;
	}
	public void setNoticeDetail(List<PayResultDetail> noticeDetail) {
		this.noticeDetail = noticeDetail;
	}
}