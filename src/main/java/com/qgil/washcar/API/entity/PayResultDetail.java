package com.qgil.washcar.API.entity;

import java.math.BigDecimal;

/**
 * 支付结果明细
 * @author wangjie
 *
 */
public class PayResultDetail {

	private String billNo;	//账单号
	private BigDecimal amt;	//金额
	
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	
}
