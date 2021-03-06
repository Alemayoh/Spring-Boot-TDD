package com.alex.TDDTestApp.model;

import javax.persistence.*;

@Entity
@Table(name="CARS")
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    public Car() {}

    public Car(String name, String type) {
        this.name = name;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
