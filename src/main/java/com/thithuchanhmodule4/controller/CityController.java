package com.thithuchanhmodule4.controller;

import com.thithuchanhmodule4.model.City;
import com.thithuchanhmodule4.model.Country;
import com.thithuchanhmodule4.service.ICityService;
import com.thithuchanhmodule4.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @GetMapping
    public ModelAndView showAll() {
        Iterable<City> cities = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("/city/index");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/add-new")
    public ModelAndView showAddForm() {
        ModelAndView modelAndView = new ModelAndView("/city/add");
        Iterable<Country> countries = countryService.findAll();
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/add-new")
    public ModelAndView addNewCity(@Validated @ModelAttribute City city, BindingResult bindingResult) {
        city.validate(city, bindingResult);
        ModelAndView modelAndView;
        if (bindingResult.hasFieldErrors()) {
            modelAndView = new ModelAndView("/city/add");
            Iterable<Country> countries = countryService.findAll();
            modelAndView.addObject("countries", countries);
            return modelAndView;
        }
        cityService.save(city);
        Iterable<Country> countries = countryService.findAll();
        modelAndView = new ModelAndView("/city/add");
        modelAndView.addObject("city", new City());
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @GetMapping("/show-city/{cityName}")
    public ModelAndView showOneCity(@PathVariable("cityName") String cityName) {
        City city = cityService.findCityByName(cityName);
        ModelAndView modelAndView = new ModelAndView("/city/city-detail");
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @GetMapping("/update/{cityId}")
    public ModelAndView showUpdateForm(@PathVariable("cityId") Long cityId) {
        Optional<City> city = cityService.findById(cityId);
        ModelAndView modelAndView = new ModelAndView("/city/update");
        if (city.isPresent()) {
            modelAndView.addObject("city", city.get());
            Iterable<Country> countries = countryService.findAll();
            modelAndView.addObject("countries", countries);
            return modelAndView;
        }
        return null;
    }

    @PostMapping("/update")
    public ModelAndView updateCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        city.validate(city, bindingResult);
        ModelAndView modelAndView;
        if (bindingResult.hasFieldErrors()) {
            modelAndView = new ModelAndView("/city/update");
            Iterable<Country> countries = countryService.findAll();
            modelAndView.addObject("countries", countries);
            return modelAndView;
        }
        cityService.save(city);
        Iterable<Country> countries = countryService.findAll();
        modelAndView = new ModelAndView("/city/update");
        modelAndView.addObject("city", city);
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @GetMapping("/delete/{cityId}")
    public String deleteCity(@PathVariable("cityId") Long cityId) {
        cityService.deleteById(cityId);
        return "redirect:/city";
    }
}
