package com.github.wumrwds.ods.component.scheduler;

import com.github.wumrwds.ods.entity.dto.CameraDTO;
import com.github.wumrwds.ods.service.CameraService;
import com.github.wumrwds.ods.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTask {

    private Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private CameraService cameraService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void sendCameraMessage(){
        try {
            logger.info("Start publishing camera data...");
            List<CameraDTO> cameras = cameraService.getCamera(null);
            template.convertAndSend("/topic/camera", JsonUtil.obj2String(cameras));
        }
        catch (Exception e) {
            logger.error("Error in broadcast messages for CameraWebsocket. e = {}", e);
        }
    }
}
