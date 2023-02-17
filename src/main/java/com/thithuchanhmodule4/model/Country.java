package com.thithuchanhmodule4.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String countryName;
}
