package com.mjovanc.blog.controller;


import com.mjovanc.blog.model.BlogPost;
import com.mjovanc.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/posts")
public class BlogPostController {

    final BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllBlogPost() {
        return new ResponseEntity<>(blogPostService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BlogPost> findById(@PathVariable Long id) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        if (blogPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(blogPost, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost blogPost) {
        blogPostService.create(blogPost);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id, @RequestBody BlogPost blogPost) {
        BlogPost updatedblogPost = blogPostService.update(id, blogPost);

        if (updatedblogPost == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(updatedblogPost, HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BlogPost> deleteBlogPost(@PathVariable Long id) {
        Boolean deleted = blogPostService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
