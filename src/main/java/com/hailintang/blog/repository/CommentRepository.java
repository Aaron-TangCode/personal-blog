package com.hailintang.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hailintang.blog.bean.Comment;
/**
 * comment的repository
 * @author aaron
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long>{
 
}
