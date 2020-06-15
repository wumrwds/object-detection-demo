package com.github.wumrwds.ods.service;

import com.github.wumrwds.ods.entity.dto.CameraDTO;

import java.util.List;

public interface CameraService {
    List<CameraDTO> getCamera(String name);
}
