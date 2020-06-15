package com.github.wumrwds.ods.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(
        JsonInclude.Include.NON_NULL
)
public class Stats {

    private String camera;

    private String date;

    private double y;

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
