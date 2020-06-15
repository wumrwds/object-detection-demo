package com.github.wumrwds.ods.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(
        JsonInclude.Include.NON_NULL
)
public class Series implements Serializable {
    private String name;

    private List<Point> data;

    public Series(String name, List<Point> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Point> getData() {
        return data;
    }

    public void setData(List<Point> data) {
        this.data = data;
    }
}
