package com.example.boot05webadmin.service.impl;

import com.example.boot05webadmin.bean.Route;
import com.example.boot05webadmin.mapper.RouteMapper;
import com.example.boot05webadmin.service.RouteService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    @Resource
    RouteMapper routeMapper;

    @Override
    public Route getRouteById(int id) {
        return routeMapper.getRoute(id);
    }
}
