package com.github.wumrwds.ods.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(
        JsonInclude.Include.NON_NULL
)
public class CameraDTO implements Serializable {
    private String id;

    private String name;

    private double lat;

    private double lng;

    private int vehicleNum;

    private int PedestrianNum;

    public CameraDTO(String id, String name, double lat, double lng, int vehicleNum, int pedestrianNum) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.vehicleNum = vehicleNum;
        PedestrianNum = pedestrianNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(int vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public int getPedestrianNum() {
        return PedestrianNum;
    }

    public void setPedestrianNum(int pedestrianNum) {
        PedestrianNum = pedestrianNum;
    }
}
