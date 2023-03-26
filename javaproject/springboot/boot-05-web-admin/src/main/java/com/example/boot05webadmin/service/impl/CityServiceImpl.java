package com.example.boot05webadmin.service.impl;

import com.example.boot05webadmin.bean.City;
import com.example.boot05webadmin.mapper.CityMapper;
import com.example.boot05webadmin.service.CityService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    @Resource
    CityMapper cityMapper;

    /**
     * 定制Metrics信息
     */
    Counter counter;

    public CityServiceImpl(MeterRegistry meterRegistry) {
        counter = meterRegistry.counter("cityService.axveCity.count");
    }

    public City getById(int id) {
        return cityMapper.getById(id);
    }

    public void saveCity(City city) {
        counter.increment();//统计计数
        cityMapper.insert(city);
    }
}
