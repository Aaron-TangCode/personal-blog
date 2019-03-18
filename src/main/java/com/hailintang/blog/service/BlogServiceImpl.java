package com.hailintang.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hailintang.blog.bean.Blog;
import com.hailintang.blog.bean.Catalog;
import com.hailintang.blog.bean.Comment;
import com.hailintang.blog.bean.User;
import com.hailintang.blog.bean.Vote;
import com.hailintang.blog.bean_es.EsBlog;
import com.hailintang.blog.repository.BlogRepository;


/**
 * blog服务接口实现类
 * @author aaron
 *
 */
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private EsBlogService esBlogService;
	/**
	 * 保存blog
	 */
	@Transactional
	@Override
	public Blog saveBlog(Blog blog) {
		boolean isNew = (blog.getId() == null);
		EsBlog esBlog = null;
		
		Blog returnBlog = blogRepository.save(blog);
		//同步更新esBlog
		if (isNew) {
			esBlog = new EsBlog(returnBlog);
		} else {
			esBlog = esBlogService.getEsBlogByBlogId(blog.getId());
			esBlog.update(returnBlog);
		}
		esBlogService.updateEsBlog(esBlog);
		return returnBlog;
	}
	/**
	 * 删除博客
	 */
	@Transactional
	@Override
	public void removeBlog(Long id) {
		blogRepository.delete(id);
		EsBlog esblog = esBlogService.getEsBlogByBlogId(id);
		esBlogService.removeEsBlog(esblog.getId());
	}
	/**
	 * 查询博客
	 */
	@Override
	public Blog getBlogById(Long id) {
		return blogRepository.findOne(id);
	}
	/**
	 * 根据title模糊查询博客
	 */
	@Override
	public Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable) {
		// 模糊查询
		title = "%" + title + "%";
		//Page<Blog> blogs = blogRepository.findByUserAndTitleLikeOrderByCreateTimeDesc(user, title, pageable);
		String tags = title;
		Page<Blog> blogs = blogRepository.findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(title,user, tags,user, pageable);
		return blogs;
	}
	/**
	 * 根据title模糊查询博客
	 */
	@Override
	public Page<Blog> listBlogsByTitleVoteAndSort(User user, String title, Pageable pageable) {
		// 模糊查询
		title = "%" + title + "%";
		Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user, title, pageable);
		return blogs;
	}
	/**
	 * 根据catalog分页查询
	 */
	@Override
	public Page<Blog> listBlogsByCatalog(Catalog catalog, Pageable pageable) {
		Page<Blog> blogs = blogRepository.findByCatalog(catalog, pageable);
		return blogs;
	}
	/**
	 * 阅读量增加
	 */
	@Override
	public void readingIncrease(Long id) {
		Blog blog = blogRepository.findOne(id);
		blog.setReadSize(blog.getCommentSize()+1);
		this.saveBlog(blog);
	}
	/**
	 * 新增评论
	 */
	@Override
	public Blog createComment(Long blogId, String commentContent) {
		Blog originalBlog = blogRepository.findOne(blogId);
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		Comment comment = new Comment(user, commentContent);
		originalBlog.addComment(comment);
		return this.saveBlog(originalBlog);
	}
	/**
	 * 移除评论
	 */
	@Override
	public void removeComment(Long blogId, Long commentId) {
		Blog originalBlog = blogRepository.findOne(blogId);
		originalBlog.removeComment(commentId);
		this.saveBlog(originalBlog);
	}
	/**
	 * 点赞
	 */
	@Override
	public Blog createVote(Long blogId) {
		Blog originalBlog = blogRepository.findOne(blogId);
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		Vote vote = new Vote(user);
		boolean isExist = originalBlog.addVote(vote);
		if (isExist) {
			throw new IllegalArgumentException("该用户已经点过赞了");
		}
		return this.saveBlog(originalBlog);
	}
	/**
	 * 取消点赞
	 */
	@Override
	public void removeVote(Long blogId, Long voteId) {
		Blog originalBlog = blogRepository.findOne(blogId);
		originalBlog.removeVote(voteId);
		this.saveBlog(originalBlog);
	}
	/**
	 * 列举所有博客
	 */
	@Override
	public Page<Blog> listBlogsByTitle(String title,Pageable pageable) {
		// TODO Auto-generated method stub
		title = "%" + title + "%";
		Page<Blog> blogs = blogRepository.findAll(pageable);
		return blogs;
	}
	@Override
	public Page<Blog> listBlogsByNameLike(String title, Pageable pageable) {
		// TODO Auto-generated method stub
		title = "%" + title + "%";
		Page<Blog> blogs = blogRepository.findByTitleLike(title,pageable);
		return blogs;
	}
}
