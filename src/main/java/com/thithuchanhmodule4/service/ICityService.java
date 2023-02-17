package com.thithuchanhmodule4.service;


import com.thithuchanhmodule4.model.City;

public interface ICityService extends IGeneralService<City> {
    City findCityByName(String cityName);
}
