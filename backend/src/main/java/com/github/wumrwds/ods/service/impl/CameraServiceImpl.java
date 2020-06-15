package com.github.wumrwds.ods.service.impl;

import com.github.wumrwds.ods.entity.Camera;
import com.github.wumrwds.ods.entity.dto.CameraDTO;
import com.github.wumrwds.ods.mapper.CameraMapper;
import com.github.wumrwds.ods.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CameraServiceImpl implements CameraService {
    @Autowired
    private CameraMapper cameraMapper;

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
            dtoList.add(new CameraDTO(camera.getCameraId(), camera.getName(), camera.getLat(),
                    camera.getLng(), 1231, 6492));
        }
        return dtoList;
    }
}
