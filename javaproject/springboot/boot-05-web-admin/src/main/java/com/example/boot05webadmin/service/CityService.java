package com.example.boot05webadmin.service;

import com.example.boot05webadmin.bean.City;

public interface CityService {
    public City getById(int id);

    public void saveCity(City city);

}
