package com.hailintang.blog.service;

import com.hailintang.blog.bean.Comment;

/**
 * comment服务接口
 * @author aaron
 *
 */
public interface CommentService {
	/**
	 * 获取评论
	 * @param id
	 * @return
	 */
	Comment getCommentById(Long id);
	/**
	 * 删除评论
	 * @param id
	 */
	void removeComment(Long id);
}
