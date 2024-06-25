package com.example.bt_buoi3;

import java.io.Serializable;

public class Person implements Serializable {

    private int id;
    private String name;
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public String to_String() {
        return this.id + " - " + this.name;
    }
}
