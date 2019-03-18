package com.hailintang.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hailintang.blog.bean.Blog;
import com.hailintang.blog.bean.Catalog;
import com.hailintang.blog.bean.User;


/**
 * blog服务接口
 * @author aaron
 *
 */
public interface BlogService {
	/**
	 * 保存博客
	 * @param blog
	 * @return
	 */
	Blog saveBlog(Blog blog);
	
	/**
	 * 删除博客
	 * @param id
	 */
	void removeBlog(Long id);
	
	/**
	 * 查询博客
	 * @param id
	 * @return
	 */
	Blog getBlogById(Long id);
	
	/**
	 * 查询博客
	 * @param id
	 * @return
	 */
	Page<Blog> listBlogsByTitle(String title,Pageable pageable);
	
	/**
	 * 模糊查询
	 */
	
	Page<Blog> listBlogsByNameLike(String name, Pageable pageable);
	/**
	 * 根据title来获取博客
	 * @param user
	 * @param title
	 * @param pageable
	 * @return
	 */
	Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable);
 
	/**
	 * 根据title来排序博客
	 * @param suser
	 * @param title
	 * @param pageable
	 * @return
	 */
	Page<Blog> listBlogsByTitleVoteAndSort(User suser, String title, Pageable pageable);
	
	/**
	 * 根据分类来获取博客
	 * @param catalog
	 * @param pageable
	 * @return
	 */
	Page<Blog> listBlogsByCatalog(Catalog catalog, Pageable pageable); 
	/**
	 * 阅读量递增
	 * @param id
	 */
	void readingIncrease(Long id);
	
	/**
	 * 发表评论
	 * @param blogId
	 * @param commentContent
	 * @return
	 */
	Blog createComment(Long blogId, String commentContent);
	
	/**
	 * 删除评论
	 * @param blogId
	 * @param commentId
	 */
	void removeComment(Long blogId, Long commentId);
	
	/**
	 * 点赞
	 * @param blogId
	 * @return
	 */
	Blog createVote(Long blogId);
	
	/**
	 * 取消点赞
	 * @param blogId
	 * @param voteId
	 */
	void removeVote(Long blogId, Long voteId);
}
