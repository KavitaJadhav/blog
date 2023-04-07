package com.mjovanc.blog.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
public class BlogTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "blogTags")
    List<BlogPost> blogPosts;

    @JsonGetter("blogPosts")
    public List<String> getAllBlogPosts() {
        if (blogPosts != null) {
            return blogPosts.stream()
                    .map(bp -> {
                        return "/v1/posts/" + bp.getID();
                    }).collect(Collectors.toList());
        }
        return null;
    }

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
