package com.mjovanc.blog.controller;

import com.mjovanc.blog.model.BlogTag;
import com.mjovanc.blog.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/tags")
public class BlogTagController {

    final BlogTagService blogTagService;

    @Autowired
    public BlogTagController(BlogTagService blogTagService) {
        this.blogTagService = blogTagService;
    }

    @GetMapping
    public ResponseEntity<List<BlogTag>> getAllBlogTag() {
        return new ResponseEntity<>(blogTagService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BlogTag> findById(@PathVariable Long id) {
        BlogTag blogTag = blogTagService.getBlogTagById(id);
        if (blogTag == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(blogTag, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<BlogTag> createBlogTag(@RequestBody BlogTag blogTag) {
        blogTagService.create(blogTag);
        return new ResponseEntity<>(blogTagService.create(blogTag), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BlogTag> updateBlogTag(@PathVariable Long id, @RequestBody BlogTag blogTag) {
        BlogTag updatedblogTag = blogTagService.update(id, blogTag);

        if (updatedblogTag == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(updatedblogTag, HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BlogTag> deleteBlogTag(@PathVariable Long id) {
        Boolean deleted = blogTagService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
