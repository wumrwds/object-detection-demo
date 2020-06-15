package com.github.wumrwds.ods.controller;

import com.github.wumrwds.ods.entity.dto.StatsDTO;
import com.github.wumrwds.ods.service.StatsService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@RestController
@RequestMapping("stats")
@Api(tags = "Stats API")
public class StatsController {

    private static final Logger logger = LoggerFactory.getLogger(StatsController.class);

    @Autowired
    private StatsService statsService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieves historical stats data by querying with specific condition")
    @ApiResponses(value = {@ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred",
                    examples = @Example(value = @ExampleProperty(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            value = "{\"start\":\"2020-06-02\",\"end\":\"2020-06-06\",\"series\":[{\"name\":\"nyc_1\",\"data\":[{\"x\":1591056000000,\"y\":739283.0},{\"x\":1591142400000,\"y\":688440.0},{\"x\":1591228800000,\"y\":568040.0},{\"x\":1591315200000,\"y\":608636.0},{\"x\":1591401600000,\"y\":527495.0}]},{\"name\":\"nyc_2\",\"data\":[{\"x\":1591056000000,\"y\":640296.0},{\"x\":1591142400000,\"y\":734804.0},{\"x\":1591228800000,\"y\":541029.0},{\"x\":1591315200000,\"y\":822358.0},{\"x\":1591401600000,\"y\":547203.0}]}]}"
                    )
                    ))
    })
    public ResponseEntity<StatsDTO> getStats(@RequestParam(name = "camera[]") List<String> cameras,
                                             @RequestParam(name = "metric") String metric,
                                             @RequestParam(name = "time-unit") String timeUnit,
                                             @RequestParam(name = "start") String start,
                                             @RequestParam(name = "end") String end) {
        try {
            StatsDTO statsDTO = statsService.getStats(cameras, metric, start, end);

            return ResponseEntity.ok(statsDTO);
        } catch (Exception e) {
            logger.error("Exception in getStats: {}", e);

            return ResponseEntity.status(400).build();
        }
    }
}
