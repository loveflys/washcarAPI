package com.qgil.washcar.API.entity;

public class QrcodeResultEntity extends BaseEntity {
	private QrcodeResult qrcoderesult;
	private String orderid;
	public QrcodeResult getQrcoderesult() {
		return qrcoderesult;
	}

	public void setQrcoderesult(QrcodeResult qrcoderesult) {
		this.qrcoderesult = qrcoderesult;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}	
}
