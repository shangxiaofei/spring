package com.spring.test.service.javacore.clone;

import java.io.Serializable;


public class Person implements Serializable {
    private int age;
    private String name;
    private Pet pet;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

}
