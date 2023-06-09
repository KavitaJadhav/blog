package com.mjovanc.blog.repository;

import com.mjovanc.blog.model.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogTag, Long> {
}
