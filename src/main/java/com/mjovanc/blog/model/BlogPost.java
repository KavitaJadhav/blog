package com.mjovanc.blog.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String text;
    private String createdAt;
    private String updatedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "blog_post_tag",
            joinColumns = {@JoinColumn(name = "blog_post_id")},
            inverseJoinColumns = {@JoinColumn(name = "blog_post_tag_id")}
    )
    @JsonProperty("blog_tags")
    public List<BlogTag> blogTags;

    public BlogPost() {
    }

    public BlogPost(String title, String text, String createdAt, String updatedAt) {
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getID() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    @JsonGetter("blog_tags")
    public List<String> getAllBlogTags() {
        if (blogTags != null) {
            return blogTags.stream()
                    .map(bt -> {
                        return "/v1/tags/" + bt.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }
}
