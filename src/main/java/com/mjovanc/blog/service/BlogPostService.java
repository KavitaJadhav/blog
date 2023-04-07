package com.mjovanc.blog.service;

import com.mjovanc.blog.model.BlogPost;
import com.mjovanc.blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {
    final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }


    public List<BlogPost> getAll() {
        return blogPostRepository.findAll();
    }

    public void create(BlogPost blogPost) {
        blogPostRepository.save(blogPost);
    }

    public BlogPost getBlogPostById(Long id) {
        if (blogPostRepository.existsById(id)) {
            return blogPostRepository.findById(id).get();
        }
        return null;
    }

    public BlogPost update(Long id, BlogPost blogPost) {
        if (blogPostRepository.existsById(id)) {
            BlogPost blogPostFromDB = blogPostRepository.findById(id).get();
            blogPostFromDB.setTitle(blogPost.getTitle());
            blogPostFromDB.setTitle(blogPost.getTitle());
            blogPostFromDB.setUpdatedAt(blogPost.getUpdatedAt());
            blogPostFromDB.setCreatedAt(blogPost.getCreatedAt());

            blogPostRepository.save(blogPostFromDB);
            return blogPostFromDB;
        }
        return null;
    }

    public Boolean delete(Long id) {
        if (blogPostRepository.existsById(id)) {
            BlogPost blogPost = blogPostRepository.findById(id).get();
            blogPostRepository.delete(blogPost);

            return true;
        }
        return false;
    }
}
