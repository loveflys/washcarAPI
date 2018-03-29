package com.qgil.washcar.API.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qgil.washcar.API.entity.Bill;
import com.qgil.washcar.API.entity.ChannelMsgResHead;
import com.qgil.washcar.API.entity.PayConst;
import com.qgil.washcar.API.entity.PayResult;
import com.qgil.washcar.API.entity.PushExtra;
import com.qgil.washcar.API.entity.QrcodeResult;
import com.qgil.washcar.API.entity.QrcodeResultEntity;
import com.qgil.washcar.API.service.PayServices;
import com.qgil.washcar.API.utils.AESUtil;

@Controller
public class PayController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	@SuppressWarnings("deprecation")
	@GetMapping("/getQrCode")
	@ResponseBody
	public QrcodeResultEntity getQrCode(@RequestParam(value="dataid", required = false, defaultValue = "") String dataid,
			@RequestParam(value="cost", required = false, defaultValue = "15") String cost,
			@RequestParam(value="deviceId", required = false, defaultValue = "") String deviceId) {
		QrcodeResult res = new QrcodeResult();
		QrcodeResultEntity result = new QrcodeResultEntity();
		PayServices pay = new PayServices();
		log.info("生成订单start===========");
        Bill bill = pay.uploadBill(new BigDecimal(Double.parseDouble(cost)).setScale(2, BigDecimal.ROUND_HALF_UP), deviceId);
        log.info("生成订单====orderNo======="+bill.getOrderno());
        log.info("生成订单====标题======="+bill.getBilltitle());
        log.info("生成订单====时间======="+Calendar.getInstance().getTime().toLocaleString());
        log.info("生成订单end===========");
        log.info("申请支付获取二维码start===========");
        String qrcode = pay.payApply(bill.getOrderno(), "alipay".equals(dataid)?PayConst.PAYCHAN_ALIPAY:PayConst.PAYCHAN_WECHAT);
        log.info("申请支付获取二维码result==>" + qrcode);
        log.info("申请支付获取二维码end===========");
        if ("alipay".equals(dataid)) {
        	res.setAli_qrcode(qrcode);
        } else {
        	res.setWx_qrcode(qrcode);
        }
        result.setOk();
        result.setQrcoderesult(res);
        result.setOrderid(bill.getOrderno());
        result.setFee(PayConst.WASHCAR_COST);
        return result;
	}
	
	@SuppressWarnings("static-access")
	@PostMapping("/getresult")
	@ResponseBody
	public void getResult(HttpServletRequest request, HttpServletResponse response) {
		PayResult payresult = new PayResult();
		try {
			byte[] test = AESUtil.readStream(request.getInputStream());
			String tempContent = new String(test);
			String content = AESUtil.decrypt(tempContent, PayConst.SECRET_KEY);
			log.info("支付回调==>" + ("OR02".equals(payresult.getOrderSts())?"支付成功":"支付失败"));
			log.info("支付回调返回信息==>" + content);
			payresult = JSONObject.parseObject(content, PayResult.class);			
			PushController push = new PushController();
	    	List<PushExtra> extralist = new ArrayList<PushExtra>();
	        extralist.add(new PushExtra("id", payresult.getOrderNo()));
	        extralist.add(new PushExtra("status", payresult.getOrderSts()));
	        push.sendMessage(JSON.toJSONString(extralist), payresult.getDbtrNo());
	        ChannelMsgResHead res = new ChannelMsgResHead();
	        res.setProcd("AAAAAA");
	        res.setProinfo("");
	        res.setUserName("");
	        String resp = new String(AESUtil.encrypt(JSONObject.toJSONString(new ChannelMsgResHead()) ,PayConst.SECRET_KEY));
	        BufferedOutputStream out = new BufferedOutputStream(  
                    response.getOutputStream()); 
			out.write(resp.getBytes());
			log.info("输出到支付平台的通知==>" + resp);
			out.flush();  
	        out.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
