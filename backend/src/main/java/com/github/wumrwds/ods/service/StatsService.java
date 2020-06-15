package com.github.wumrwds.ods.service;

import com.github.wumrwds.ods.entity.dto.StatsDTO;

import java.util.List;

public interface StatsService {
    StatsDTO getStats(List<String> cameraList, String metric, String start, String end);
}
