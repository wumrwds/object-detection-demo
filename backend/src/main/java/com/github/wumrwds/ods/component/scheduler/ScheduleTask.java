package com.github.wumrwds.ods.component.scheduler;

import com.github.wumrwds.ods.component.websocket.CameraWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleTask {

    private Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Scheduled(cron = "0 */1 * * * ?")
    public void sendCameraMessage(){
        try {
            String message = "test";

            CameraWebSocket.broadcast(message);
        }
        catch (Exception e) {
            logger.error("Error in broadcast messages for CameraWebsocket. e = {}", e);
        }
    }
}
