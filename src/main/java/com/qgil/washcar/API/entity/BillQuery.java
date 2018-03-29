package com.qgil.washcar.API.entity;

import java.math.BigDecimal;

/**
 * 账单查询
 * @author wangjie
 *
 */
public class BillQuery {

	private ChannelMsgReqHead reqHead;//请求报文头
	private ChannelMsgResHead resHead;//应答报文头
	private String billNo;	//账单号
	private String orderNo;	//订单号
	private String orderSts;//订单装填
	private String orderDate;//订单日期
	private String bizSystem;//业务系统号
	private BigDecimal amt;//金额
	private String cdtrNo;//商号编号
	private String cdtrNm;//商号名称
	private String dbtrNo;//付款账号
	private String dbtrNm;//付款账号
	private String pcNo;//支付渠道编号
	private String pcNm;//支付渠道名称
	private String pcBizNo;//支付渠道交易流水
	private String payTime;//支付成功时间
	private String failInfo;//支付失败原因
	private String opruser;//所属用户
	
	public BillQuery(String billNo) {
		this.reqHead = new ChannelMsgReqHead("Pus020");
		this.billNo = billNo;
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
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
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
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getBizSystem() {
		return bizSystem;
	}
	public void setBizSystem(String bizSystem) {
		this.bizSystem = bizSystem;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getCdtrNo() {
		return cdtrNo;
	}
	public void setCdtrNo(String cdtrNo) {
		this.cdtrNo = cdtrNo;
	}
	public String getCdtrNm() {
		return cdtrNm;
	}
	public void setCdtrNm(String cdtrNm) {
		this.cdtrNm = cdtrNm;
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
	public String getPcNo() {
		return pcNo;
	}
	public void setPcNo(String pcNo) {
		this.pcNo = pcNo;
	}
	public String getPcNm() {
		return pcNm;
	}
	public void setPcNm(String pcNm) {
		this.pcNm = pcNm;
	}
	public String getPcBizNo() {
		return pcBizNo;
	}
	public void setPcBizNo(String pcBizNo) {
		this.pcBizNo = pcBizNo;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getFailInfo() {
		return failInfo;
	}
	public void setFailInfo(String failInfo) {
		this.failInfo = failInfo;
	}
	public String getOpruser() {
		return opruser;
	}
	public void setOpruser(String opruser) {
		this.opruser = opruser;
	}
}