package com.redis.redisproject.Model;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionID=1L;
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
