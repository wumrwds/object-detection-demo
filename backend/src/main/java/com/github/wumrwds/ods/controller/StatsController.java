package com.github.wumrwds.ods.controller;

import com.github.wumrwds.ods.entity.dto.StatsDTO;
import com.github.wumrwds.ods.service.StatsService;
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
@RequestMapping("stats")
public class StatsController {

    private static final Logger logger = LoggerFactory.getLogger(StatsController.class);

    @Autowired
    private StatsService statsService;

    @GetMapping(value = "")
    public ResponseEntity<StatsDTO> getStats(@RequestParam(name = "camera[]") List<String> cameras,
                                               @RequestParam(name = "metric") String metric,
                                               @RequestParam(name = "time-unit") String timeUnit,
                                               @RequestParam(name = "start") String start,
                                               @RequestParam(name = "end") String end) {
        try {
            StatsDTO statsDTO = statsService.getStats(cameras, metric, start, end);

            return ResponseEntity.ok(statsDTO);
        }
        catch (Exception e) {
            logger.error("Exception in getStats: {}", e);

            return ResponseEntity.status(400).build();
        }
    }
}
