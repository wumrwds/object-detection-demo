package com.github.wumrwds.ods.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.wumrwds.ods.serializer.DateSerializer;

import java.io.Serializable;

@JsonInclude(
        JsonInclude.Include.NON_NULL
)
public class Point implements Serializable {
    @JsonSerialize(using = DateSerializer.class)
    private String x;
    private double y;

    public Point(String x, double y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
