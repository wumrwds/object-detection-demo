package com.github.wumrwds.ods.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.wumrwds.ods.entity.vo.Series;

import java.io.Serializable;
import java.util.List;

@JsonInclude(
        JsonInclude.Include.NON_NULL
)
public class StatsDTO implements Serializable {
    private String start;

    private String end;

    private List<Series> series;

    public StatsDTO(String start, String end, List<Series> series) {
        this.start = start;
        this.end = end;
        this.series = series;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }
}
