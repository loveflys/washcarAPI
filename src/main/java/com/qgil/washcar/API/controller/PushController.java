package com.qgil.washcar.API.controller;
import com.alibaba.fastjson.JSONArray;
import com.qgil.washcar.API.entity.BaseEntity;
import com.qgil.washcar.API.entity.PushConfig;
import com.qgil.washcar.API.entity.PushExtra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * Created by 陈安一 on 2018/3/28.
 */
@RestController
@RequestMapping("/push")
public class PushController {
	private final Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private PushConfig pushConfig;
	//在极光注册上传应用的 appKey 和 masterSecret  
    private static JPushClient jpush = null;  
    @RequestMapping(value="/sendMessage", method = RequestMethod.GET)
    public BaseEntity pushMessage (
    		@RequestParam("msg") String msg,
    		@RequestParam("title") String title,
    		@RequestParam("msgContent") String msgContent,
    		@RequestParam("extra") String extra,
    		@RequestParam(value="appKey", required = false, defaultValue = "") String appKey,
    		@RequestParam(value="masterSecret", required = false, defaultValue = "") String masterSecret) {
    	BaseEntity base = new BaseEntity();
    	if ("".equals(appKey)) {
    		appKey = pushConfig.getAppKey();
    	}
    	if ("".equals(masterSecret)) {
    		masterSecret = pushConfig.getMasterSecret();
    	}
    	jpush = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());  
    	System.out.println(jpush==null);
    	List<PushExtra> extralist = JSONArray.parseArray(extra, PushExtra.class);
    	Map<String,String> extras = new HashMap<String,String>();
    	for (PushExtra pushExtra : extralist) {
			extras.put(pushExtra.getExtraKey(), pushExtra.getExtraValue());
		}
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(Notification.android(msg, title, extras))
                .setMessage(Message.newBuilder()
                		.setTitle(title)
                		.setMsgContent(msgContent)
                		.addExtras(extras)
                		.build())
                .build();
        try {
            PushResult result = jpush.sendPush(payload);
            log.info("极光推送==>Got result - " + result);
            base.setOk();
        } catch (APIConnectionException e) {
        	base.setErr("-200", e.getMessage());;
            // Connection error, should retry later
        	log.error("极光推送连接异常==>Connection error, should retry later", e);
        } catch (APIRequestException e) {
        	base.setErr("-200", e.getMessage());;
            // Should review the error, and fix the request
        	log.error("极光推送请求异常==>Should review the error, and fix the request", e);
        	log.info("极光推送请求异常==>HTTP Status: " + e.getStatus());
        	log.info("极光推送请求异常==>Error Code: " + e.getErrorCode());
        	log.info("极光推送请求异常==>Error Message: " + e.getErrorMessage());
        }   	
    	
        return base;
    }
}
