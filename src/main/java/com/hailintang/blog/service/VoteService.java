package com.hailintang.blog.service;

import com.hailintang.blog.bean.Vote;
/**
 * vote服务接口
 * @author aaron
 *
 */
public interface VoteService {
	/**
	 * 获取vote
	 * @param id
	 * @return
	 */
	Vote getVoteById(Long id);
	/**
	 * 删除vote
	 * @param id
	 */
	void removeVote(Long id);
}
