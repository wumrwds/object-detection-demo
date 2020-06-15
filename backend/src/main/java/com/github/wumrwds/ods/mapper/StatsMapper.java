package com.github.wumrwds.ods.mapper;

import com.github.wumrwds.ods.entity.Stats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatsMapper {

    List<Stats> selectByCamera(@Param("cameraList") List<String> cameraList,
                               @Param("metric") String metric,
                               @Param("start") String start,
                               @Param("end") String end
    );

}
