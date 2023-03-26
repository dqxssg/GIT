package com.example.boot05webadmin.mapper;

import com.example.boot05webadmin.bean.Route;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RouteMapper {
     Route getRoute(int id);
}
