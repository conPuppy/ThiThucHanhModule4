package com.thithuchanhmodule4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
public class City implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    private Country country;

    private double area;
    private int population;
    private double GDP;
    private String description;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        City city = (City) target;
        double area = city.getArea();
        long population = city.getPopulation();
        double GDP = city.getGDP();
        if (area < 1) {
            errors.rejectValue("area", "area.unique");
        }
        if (population < 1) {
            errors.rejectValue("population", "population.unique");
        }
        if (GDP < 1) {
            errors.rejectValue("GDP", "gdp.unique");
        }
    }
}
