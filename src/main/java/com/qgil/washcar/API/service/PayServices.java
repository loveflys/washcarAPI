package com.qgil.washcar.API.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qgil.washcar.API.entity.Bill;
import com.qgil.washcar.API.entity.BillDetail;
import com.qgil.washcar.API.entity.PayApply;
import com.qgil.washcar.API.entity.PayConst;
import com.qgil.washcar.API.entity.PayResult;
import com.qgil.washcar.API.utils.AESUtil;
import com.qgil.washcar.API.utils.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenay on 2018/3/22.
 */

public class PayServices {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    /**
     * 上传账单
     * @return
     */
    public Bill uploadBill() {
        Bill bill = new Bill(PayConst.MERCHANT_NO_WASHCAR);
        List<BillDetail> details = new ArrayList<BillDetail>();
        BillDetail detail = new BillDetail(PayConst.ORDER_TITLE);
        detail.setFiracctype("");//一级收费类型
        detail.setSecacctype("");//二级收费类型
        detail.setDbtrno("");//付款人账号
        detail.setDbtrnm("");//付款人名称
        detail.setBillno(System.currentTimeMillis() + "");//账单号
        detail.setAmt(new BigDecimal(PayConst.WASHCAR_COST).setScale(2, BigDecimal.ROUND_HALF_UP));//金额
        detail.setUnefftime("");//失效时间
        details.add(detail);
        bill.setBillDetail(details);
        bill.setNotifyurl(PayConst.NOTIFY_URL);
        try {
            String result = HttpRequestUtil.doPost(PayConst.PAY_URL, new String(AESUtil.encrypt(EncodeByGson(bill) , PayConst.SECRET_KEY)));
            result = AESUtil.decrypt(result, PayConst.SECRET_KEY);
            Bill res = JSONObject.parseObject(result, Bill.class);
            bill.setOrderno(res.getOrderno());
            if(!bill.getBillDetail().isEmpty()) {
                bill.setBillno(bill.getBillDetail().get(0).getBillno());
                bill.setBilltitle(bill.getBillDetail().get(0).getBilltitle());
                bill.setAmt(bill.getBillDetail().get(0).getAmt().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            return bill;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String EncodeByGson(Object obj) {
        if(obj == null || obj.toString().equals("null")) return null;
        if(obj != null && obj.getClass() == String.class){
            return obj.toString();
        }
        return gson.toJson(obj);
    }
    /**
     * 支付申请
     */
    public String payApply(String orderNo, String paychan) {
        try {
            PayApply apply = new PayApply(orderNo, paychan);
            String result = HttpRequestUtil.doPost(PayConst.PAY_URL, new String(AESUtil.encrypt(EncodeByGson(apply) , PayConst.SECRET_KEY)));
            result = AESUtil.decrypt(result, PayConst.SECRET_KEY);
            apply = JSONObject.parseObject(result, PayApply.class);
            if(StringUtils.isNotBlank(apply.getQrcodeurl())) {
                return apply.getQrcodeurl();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 处理支付结果
     */
    public void handle(PayResult result) {

    }

}
