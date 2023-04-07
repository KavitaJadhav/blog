package com.mjovanc.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BlogTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public BlogTag() {
    }

    public Long getId() {
        return id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BlogTag(String name) {
        this.name = name;
    }
}
