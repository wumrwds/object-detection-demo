package com.github.wumrwds.ods.component.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/camera")
public class CameraWebSocket {

    private static final Logger logger = LoggerFactory.getLogger(CameraWebSocket.class);

    /** Thread-safe set containing the session for each client */
    private static CopyOnWriteArraySet<CameraWebSocket> webSockets = new CopyOnWriteArraySet<>();


    /** Connection Session to Client */
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        logger.info("New Websocket connection opened. Current total number of connections is {}.", webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        logger.info("Websocket connection closed. Current total number of connections is {}.", webSockets.size());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("Error in Websocket. e = {}.", error);
    }

    @OnMessage
    public void onMessage(String message) {
        logger.info("Websocket on message. message = {}.", message);
    }

    public static void broadcast(String message) {
        for (CameraWebSocket webSocket : webSockets) {
            logger.info("Websocket broadcast new message. message = {}.", message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}