package com.qgil.washcar.API.entity;

import java.util.ResourceBundle;

public class PayConst {

	//读取pay.properties配置文件
	static ResourceBundle rb = ResourceBundle.getBundle("pay");
	
	public static final String PAY_URL = rb.getString("pay_url");	//支付接口地址
	public static final String SECRET_KEY = rb.getString("secret_key");	//AES加密秘钥
	public static final String MERCHANT_NO_DXM = rb.getString("merchant_no_dxm");//调箱门商户号
	public static final String MERCHANT_NO_QQCT = rb.getString("merchant_no_qqct");//三期测温商户号
	public static final String MERCHANT_NO_WASHCAR = rb.getString("merchant_no_washcar"); //洗车商户号
	public static final String ORDER_TITLE = rb.getString("order_title"); //账单标题
	public static final Double WASHCAR_COST = Double.parseDouble(rb.getString("washcar_cost")); //洗车费用
	public static final String CHANID = rb.getString("chanid");	//业务来源  01xx 网上服务大厅， 02xx 舱单传输系统
	public static final String LOGIN_TYPE = rb.getString("login_type");	//登录类型  0支付平台，1网上服务大厅， 2 舱单传输系统
	public static final String NOTIFY_URL = rb.getString("notify_url");	//支付结果通知地址
	public static final String PAYCHAN_WECHAT = "010101";	//微信支付
	public static final String PAYCHAN_ALIPAY = "010201";	//支付宝
	public static final String PAYCHAN_ALIWX = "010301";	//二合一（未投入使用）
}
