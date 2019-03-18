package com.hailintang.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hailintang.blog.bean.Vote;
/**
 * vote的repository
 * @author aaron
 *
 */
public interface VoteRepository extends JpaRepository<Vote, Long>{
 
}
