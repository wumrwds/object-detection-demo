package com.github.wumrwds.ods.service.impl;

import com.github.wumrwds.ods.entity.Camera;
import com.github.wumrwds.ods.entity.dto.CameraDTO;
import com.github.wumrwds.ods.mapper.CameraMapper;
import com.github.wumrwds.ods.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CameraServiceImpl implements CameraService {
    @Autowired
    private CameraMapper cameraMapper;

    /** TODO remove these variables for generating random number */
    private int randomMinVehicle = 10;
    private int randomMaxVehicle = 1000;
    private int randomMinPedestrian = 0;
    private int randomMaxPedestrian = 120;

    @Override
    public List<CameraDTO> getCamera(String name) {
        List<Camera> cameras;
        if (name != null && !name.isEmpty()) {
            cameras = cameraMapper.selectByName(name);
        }
        else {
            cameras = cameraMapper.selectAll();
        }

        List<CameraDTO> dtoList = new LinkedList<>();
        for (Camera camera : cameras) {
            dtoList.add(new CameraDTO(camera.getCameraId(), camera.getName(), camera.getLat(), camera.getLng(),
                    ThreadLocalRandom.current().nextInt(randomMinVehicle, randomMaxVehicle + 1),
                    ThreadLocalRandom.current().nextInt(randomMinPedestrian, randomMaxPedestrian + 1))
            );
        }
        return dtoList;
    }
}
