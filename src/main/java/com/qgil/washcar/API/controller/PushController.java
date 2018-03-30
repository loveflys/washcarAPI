package com.qgil.washcar.API.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
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
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by 陈安一 on 2018/3/28.
 */
@ServerEndpoint(value = "/paySocketServer/{channel}")
@Component 
@Controller
public class PushController {
	protected org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
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
			log.info("channel==>" + channel+"ConnectedSocket");
    		session.getUserProperties().put("channel", channel);
        	sessions.add(session);
    	}
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("SocketAcceptMsg==>" + message);
	}

	@OnClose
	public void onClose(Session session, CloseReason reason) {
		log.info("SocketClose");
		sessions.remove(session);
	}

	@OnError
	public void onError(Throwable t) {
		log.error("SocketServiceError : " + t.getMessage());
	}
	
	public Set<Session> getSessions() {
		return sessions;
	}

	/**
	 * 发送消息
	 */
	@SuppressWarnings("deprecation")
	public static void sendMessage(String message, String channel) {
		String sc = null; 
		for(Session session : sessions){
			sc = (String) session.getUserProperties().get("channel");
			if(StringUtils.isNotBlank(sc) && sc.equals(channel)) {
				try {
					System.out.println("sendMessage==>" + message);
					System.out.println("sendMessageTo==>" + channel);
					System.out.println("sendMessageTime==>" + new Date().toLocaleString());
					session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
