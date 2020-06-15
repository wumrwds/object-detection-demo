package com.github.wumrwds.ods.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(
        JsonInclude.Include.NON_NULL
)
public class Camera {
    private String cameraId;

    private String name;

    private double lat;

    private double lng;

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
