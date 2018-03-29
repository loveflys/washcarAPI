package com.qgil.washcar.API.entity;

import java.math.BigDecimal;

/**
 * 订单明细
 * @author wangjie
 *
 */
public class BillDetail {

	private String firacctype;	//一级收费类型
	private String secacctype;	//二级收费类型
	private String dbtrnm;		//付款人名称
	private String billtitle;	//账单标题
	private String billno;		//账单号
	private BigDecimal amt;		//金额
	private String unefftime;	//失效时间
	private String dbtrno;		//付款人账号
	
	public BillDetail(String billtitle) {
		this.billtitle = billtitle;
	}
	
	public String getFiracctype() {
		return firacctype;
	}
	public void setFiracctype(String firacctype) {
		this.firacctype = firacctype;
	}
	public String getSecacctype() {
		return secacctype;
	}
	public void setSecacctype(String secacctype) {
		this.secacctype = secacctype;
	}
	public String getDbtrnm() {
		return dbtrnm;
	}
	public void setDbtrnm(String dbtrnm) {
		this.dbtrnm = dbtrnm;
	}
	public String getBilltitle() {
		return billtitle;
	}
	public void setBilltitle(String billtitle) {
		this.billtitle = billtitle;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getUnefftime() {
		return unefftime;
	}
	public void setUnefftime(String unefftime) {
		this.unefftime = unefftime;
	}
	public String getDbtrno() {
		return dbtrno;
	}
	public void setDbtrno(String dbtrno) {
		this.dbtrno = dbtrno;
	}
}
