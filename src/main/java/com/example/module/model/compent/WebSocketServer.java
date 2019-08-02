package com.example.module.model.compent;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description:
 * Created by lpfei on 2019/8/2
 * http://coolaf.com/tool/chattest  websocket在线测试链接
 * Springboot2+Netty实现Websocket，使用官方的netty-all的包，比原生的websocket更加稳定更加高性能，同等配置情况下可以handle更多的连接。
 */
@Slf4j
@Component
@ServerEndpoint("/webSocket/{userId}")
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。
    private static final AtomicInteger onlineCount = new AtomicInteger();
    //使用map对象，便于根据userId来获取对应的WebSocket
    private static ConcurrentHashMap<String, WebSocketServer> websocketList = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收sid
    private String userId = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        websocketList.put(userId, this);
        log.info("websocketList->" + JSON.toJSONString(websocketList));
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听:" + userId + ",当前在线人数为" + getOnlineCount());
        this.userId = userId;
        try {
            sendMessage("success");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (websocketList.get(this.userId) != null) {
            websocketList.remove(this.userId);
            subOnlineCount();           //在线数减1
            log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误:{}", error);
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + userId + "的信息:" + message);
        try {
            eachSend(userId, message);
        } catch (IOException e) {
            log.error("websocket IO异常:{}", e);
        }
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        log.info("推送消息到窗口" + userId + "，推送内容:" + message);
        try {
            eachSend(userId, message);
        } catch (IOException e) {
            log.error("websocket IO异常:{}", e);
        }
    }

    private static void eachSend(String userId, String message) throws IOException {
        if (userId == null) {
            //群发消息
            websocketList.forEach((k, v) -> {
                try {
                    v.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            websocketList.get(userId).sendMessage(message);
        }
    }

    private static int getOnlineCount() {
        return onlineCount.get();
    }

    private static void addOnlineCount() {
        onlineCount.getAndIncrement();
    }

    private static void subOnlineCount() {
        onlineCount.getAndDecrement();
    }
}
