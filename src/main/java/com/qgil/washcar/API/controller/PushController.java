package com.qgil.washcar.API.controller;

import com.alibaba.fastjson.JSONArray;
import com.qgil.washcar.API.entity.BaseEntity;
import com.qgil.washcar.API.entity.PushConfig;
import com.qgil.washcar.API.entity.PushExtra;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 陈安一 on 2018/3/28.
 */
@ServerEndpoint(value = "/paySocketServer/{channel}")
@Component 
@Controller
public class PushController {
	
	private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
	
	private static PushController paySocket;  
	
	/**
	 * 私有化构造方法
	 */
    public PushController (){}  
    
    /**
     * 单例
     * @return
     */
    public static PushController getSingleton() {  
	    if (paySocket == null) {  
	        synchronized (PushController.class) {  
		        if (paySocket == null) {  
		        	paySocket = new PushController();  
		        }  
	        }  
	    }  
	    return paySocket;  
    } 
	
    @OnOpen
	public void onOpen(Session session,EndpointConfig config, @PathParam("channel") String channel) {
		if(StringUtils.isNotBlank(channel)) {
			System.out.println("channel==>" + channel);
    		session.getUserProperties().put("channel", channel);
        	sessions.add(session);
    	}
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		for(Session s : sessions){
			try {
				s.getBasicRemote().sendText(message + "!!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@OnClose
	public void onClose(Session session, CloseReason reason) {
		sessions.remove(session);
	}

	@OnError
	public void onError(Throwable t) {
		System.out.println("Error : " + t.getMessage());
	}
	
	public Set<Session> getSessions() {
		return sessions;
	}

	/**
	 * 发送消息
	 */
	public static void sendMessage(String message, String channel) {
		String sc = null; 
		for(Session session : sessions){
			sc = (String) session.getUserProperties().get("channel");
			if(StringUtils.isNotBlank(sc) && sc.equals(channel)) {
				try {
					session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@GetMapping("/test")
    public void pushMessage (
    		@RequestParam("msg") String msg,
    		@RequestParam(value="channel", required = false, defaultValue = "") String channel) {
    	Boolean result = false;
    	
    	paySocket.sendMessage(msg, channel);
    	
    }
}
