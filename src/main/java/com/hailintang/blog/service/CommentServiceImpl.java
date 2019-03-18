package com.hailintang.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hailintang.blog.bean.Comment;
import com.hailintang.blog.repository.CommentRepository;
/**
 * comment服务接口实现类
 * @author aaron
 *
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	/**
	 * 获取评论
	 */
	@Override
	public Comment getCommentById(Long id) {
		return commentRepository.findOne(id);
	}
	/**
	 * 删除评论
	 */
	@Override
	@Transactional
	public void removeComment(Long id) {
		commentRepository.delete(id);
	}

}
