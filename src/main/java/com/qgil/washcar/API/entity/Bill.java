package com.qgil.washcar.API.entity;

import com.qgil.washcar.API.utils.SingletonManager;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 账单
 * @author wangjie
 *
 */
public class Bill {

	private ChannelMsgReqHead reqHead;	//请求报文头
	private ChannelMsgResHead resHead;	//应答报文头
	private String cdtrno;	//商户号
	private List<BillDetail> billDetail;//帐单明细
	private String reqtype;		//请求类型  默认是web，web、h5
	private String notifyurl;	//支付结果通知地址
	
	//以下是数据库保存的内容
	private String orderno;	//订单号
	private String operuser;	//操作员
	private String billno;	//账单号
	private Double amt;		//账单金额
	private String billtitle;//账单标题
	private String ordersts;//订单状态
	private String payChannelCd;//支付渠道编号
	private String payChannelNm;//支付渠道名称
	private Date payTime;//支付时间
	private String payNo;//支付渠道流水号
	private String dbtrNo;//付款账号
	private String dbtrNm;//付款户名
	private String paychan;//支付渠道  010101:微信 010201:支付宝
	private String ssdw;	//所属单位
	private String tzbh;	//台账编号
	private String remark;	//备注
	
	public Bill() {
		this.reqHead = new ChannelMsgReqHead("Pus001");
		this.reqtype = "web";
		this.notifyurl = PayConst.NOTIFY_URL;
		this.remark = "青港物流统一计费平台";
	}
	
	public Bill(String cdtrno) {
		this.reqHead = new ChannelMsgReqHead("Pus001");
		this.cdtrno = cdtrno;
		this.reqtype = "web";
		this.notifyurl = PayConst.NOTIFY_URL;
		this.remark = "青港物流统一计费平台";
	}
	
	public Bill(String ssdw, String cdtrno) {
		this(cdtrno);
		this.ssdw = ssdw;
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
	public String getCdtrno() {
		return cdtrno;
	}
	public void setCdtrno(String cdtrno) {
		this.cdtrno = cdtrno;
	}
	public List<BillDetail> getBillDetail() {
		return billDetail;
	}
	public void setBillDetail(List<BillDetail> billDetail) {
		this.billDetail = billDetail;
	}
	public String getReqtype() {
		return reqtype;
	}
	public void setReqtype(String reqtype) {
		this.reqtype = reqtype;
	}
	public String getNotifyurl() {
		return notifyurl;
	}
	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	
	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}
	
	public String getOperuser() {
		return operuser;
	}

	public void setOperuser(String operuser) {
		this.operuser = operuser;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public String getBilltitle() {
		return billtitle;
	}

	public void setBilltitle(String billtitle) {
		this.billtitle = billtitle;
	}

	public String getOrdersts() {
		return ordersts;
	}

	public void setOrdersts(String ordersts) {
		this.ordersts = ordersts;
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

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
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

	public String getPaychan() {
		return paychan;
	}

	public void setPaychan(String paychan) {
		this.paychan = paychan;
	}

	public String getSsdw() {
		return ssdw;
	}

	public void setSsdw(String ssdw) {
		this.ssdw = ssdw;
	}

	public String getTzbh() {
		return tzbh;
	}

	public void setTzbh(String tzbh) {
		this.tzbh = tzbh;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 整理账单
	 * @param result
	 */
	public void arrange(PayResult result) {
		this.ordersts = result.getOrderSts();
		this.payChannelCd = result.getPayChannelCd();
		this.payChannelNm = result.getPayChannelNm();
		this.payNo = result.getPayNo();
		this.dbtrNm = result.getDbtrNm();
		this.dbtrNo = result.getDbtrNo();
		try {
			this.payTime = StringUtils.isNotBlank(result.getPayTime()) ? SingletonManager.getIstance().parse(result.getPayTime()) : new Date();
		} catch (ParseException e) {
			e.printStackTrace();
		}
/*		if(!this.billDetail.isEmpty()) {
			this.billno = this.billDetail.get(0).getBillno();
			this.billtitle = this.billDetail.get(0).getBilltitle();
			this.amt = this.billDetail.get(0).getAmt().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}*/
	}
}
