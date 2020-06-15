package com.github.wumrwds.ods.controller;

import com.github.wumrwds.ods.entity.dto.CameraDTO;
import com.github.wumrwds.ods.service.CameraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("camera")
public class CameraController {

    private static final Logger logger = LoggerFactory.getLogger(CameraController.class);

    @Autowired
    private CameraService cameraService;

    @GetMapping(value = "")
    public ResponseEntity<List<CameraDTO>> getStats(@RequestParam(name = "name", required = false) String name) {
        try {
            List<CameraDTO> dtoList = cameraService.getCamera(name);
            return ResponseEntity.ok(dtoList);
        }
        catch (Exception e) {
            logger.error("Exception in getStats: {}", e);

            return ResponseEntity.status(400).build();
        }
    }
}
