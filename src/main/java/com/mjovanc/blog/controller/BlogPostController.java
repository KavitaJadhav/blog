package com.mjovanc.blog.controller;


import com.mjovanc.blog.model.BlogPost;
import com.mjovanc.blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/posts")
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllBlogPost() {
        List<BlogPost> blogs = blogPostRepository.findAll();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BlogPost> findById(@PathVariable Long id) {
        System.out.println("id :"+ id);
        if (blogPostRepository.existsById(id)) {
            return new ResponseEntity<>(blogPostRepository.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost blogPost) {
        blogPostRepository.save(blogPost);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id, @RequestBody BlogPost blogPost) {
        if (blogPostRepository.existsById(id)) {
            BlogPost blogPostFromDB = blogPostRepository.findById(id).get();
            blogPostFromDB.setTitle(blogPost.getTitle());
            blogPostFromDB.setTitle(blogPost.getTitle());
            blogPostFromDB.setUpdatedAt(blogPost.getUpdatedAt());
            blogPostFromDB.setCreatedAt(blogPost.getCreatedAt());

            blogPostRepository.save(blogPostFromDB);
            return new ResponseEntity<>(blogPostFromDB, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BlogPost> deleteBlogPost(@PathVariable Long id) {
        if (blogPostRepository.existsById(id)) {
            BlogPost blogPost = blogPostRepository.findById(id).get();
            blogPostRepository.delete(blogPost);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
