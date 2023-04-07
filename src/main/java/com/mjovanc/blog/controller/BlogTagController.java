package com.mjovanc.blog.controller;

import com.mjovanc.blog.model.BlogTag;
import com.mjovanc.blog.repository.BlogTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/tags")
public class BlogTagController {

    @Autowired
    BlogTagRepository blogTagRepository;

    @GetMapping
    public ResponseEntity<List<BlogTag>> getAllTags() {
        return new ResponseEntity<>(blogTagRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BlogTag> getTagById(@PathVariable Long id) {
        if (blogTagRepository.existsById(id)) {
            return new ResponseEntity<>(blogTagRepository.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<BlogTag> createBlogTag(@RequestBody BlogTag blogTag) {
        blogTagRepository.save(blogTag);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<BlogTag> updateBlogTag(@PathVariable Long id, @RequestBody BlogTag blogTag) {
        if (blogTagRepository.existsById(id)) {
            BlogTag blogTagInDB = blogTagRepository.findById(id).get();
            blogTagInDB.setName(blogTag.getName());
            return new ResponseEntity(blogTagInDB, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BlogTag> deleteBlogTag(@PathVariable Long id) {
        if (blogTagRepository.existsById(id)) {
            BlogTag blogTag = blogTagRepository.findById(id).get();
            blogTagRepository.delete(blogTag);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
