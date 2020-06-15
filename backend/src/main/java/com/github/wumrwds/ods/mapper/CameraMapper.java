package com.github.wumrwds.ods.mapper;

import com.github.wumrwds.ods.entity.Camera;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CameraMapper {

    List<Camera> selectByName(@Param("name") String name);

    List<Camera> selectAll();

}
