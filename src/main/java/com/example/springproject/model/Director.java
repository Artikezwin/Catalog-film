package com.example.springproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String name;
    private Integer age;
    private String country;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Film> filmList;

}
