package com.github.wumrwds.ods.service.impl;

import com.github.wumrwds.ods.entity.Stats;
import com.github.wumrwds.ods.entity.dto.StatsDTO;
import com.github.wumrwds.ods.entity.vo.Point;
import com.github.wumrwds.ods.entity.vo.Series;
import com.github.wumrwds.ods.mapper.StatsMapper;
import com.github.wumrwds.ods.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private StatsMapper statsMapper;

    @Override
    public StatsDTO getStats(List<String> cameraList, String metric, String start, String end) {
        List<Stats> statsList = statsMapper.selectByCamera(cameraList, metric, start, end);

        // initialize series map
        Map<String, Series> seriesMap = new HashMap<>(cameraList.size());
        for (String camera : cameraList) {
            seriesMap.put(camera, new Series(camera, new LinkedList<>()));
        }

        // filter records into different group
        StatsDTO dto = new StatsDTO(start, end, new ArrayList<>(cameraList.size()));
        for (Stats stats : statsList) {
            seriesMap.get(stats.getCamera()).getData().add(new Point(stats.getDate(), stats.getY()));
        }
        for (Series series : seriesMap.values()) {
            dto.getSeries().add(series);
        }

        return dto;
    }
}
