package com.github.wumrwds.ods.controller;

import com.github.wumrwds.ods.entity.dto.CameraDTO;
import com.github.wumrwds.ods.service.CameraService;
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

import static javax.servlet.http.HttpServletResponse.*;

@RestController
@RequestMapping("camera")
@Api(tags = "Camera API")
public class CameraController {

    private static final Logger logger = LoggerFactory.getLogger(CameraController.class);

    @Autowired
    private CameraService cameraService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieves all or part of camera info by querying with specific camera label name")
    @ApiResponses(value = {@ApiResponse(code = SC_OK, message = "ok",
            examples = @Example(value = @ExampleProperty(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    value = "[{\"id\":\"nyc_1\",\"name\":\"NYC Camera 1\",\"lat\":40.641449,\"lng\":-73.778107,\"vehicleNum\":1231,\"pedestrianNum\":6492},{\"id\":\"nyc_2\",\"name\":\"NYC Camera 2\",\"lat\":40.80667,\"lng\":-73.964777,\"vehicleNum\":1231,\"pedestrianNum\":6492},{\"id\":\"nyc_3\",\"name\":\"NYC Camera 3\",\"lat\":40.730733,\"lng\":-73.995581,\"vehicleNum\":1231,\"pedestrianNum\":6492}]"
            ))),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred")
    })
    public ResponseEntity<List<CameraDTO>> getStats(@RequestParam(name = "name", required = false) String name) {
        try {
            List<CameraDTO> dtoList = cameraService.getCamera(name);
            return ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            logger.error("Exception in getStats: {}", e);

            return ResponseEntity.status(400).build();
        }
    }
}
