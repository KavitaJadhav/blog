package com.mjovanc.blog.service;

import com.mjovanc.blog.model.BlogTag;
import com.mjovanc.blog.repository.BlogTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogTagService {
    final BlogTagRepository blogTagRepository;

    @Autowired
    public BlogTagService(BlogTagRepository blogTagRepository) {
        this.blogTagRepository = blogTagRepository;
    }


    public List<BlogTag> getAll() {
        return blogTagRepository.findAll();
    }

    public void create(BlogTag blogTag) {
        blogTagRepository.save(blogTag);
    }

    public BlogTag getBlogTagById(Long id) {
        if (blogTagRepository.existsById(id)) {
            return blogTagRepository.findById(id).get();
        }
        return null;
    }

    public BlogTag update(Long id, BlogTag blogTag) {
        if (blogTagRepository.existsById(id)) {
            BlogTag blogTagFromDB = blogTagRepository.findById(id).get();
            blogTagFromDB.setName(blogTag.getName());


            blogTagRepository.save(blogTagFromDB);
            return blogTagFromDB;
        }
        return null;
    }

    public Boolean delete(Long id) {
        if (blogTagRepository.existsById(id)) {
            BlogTag blogTag = blogTagRepository.findById(id).get();
            blogTagRepository.delete(blogTag);

            return true;
        }
        return false;
    }
}
