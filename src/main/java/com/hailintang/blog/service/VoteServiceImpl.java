package com.hailintang.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hailintang.blog.bean.Vote;
import com.hailintang.blog.repository.VoteRepository;
/**
 * vote服务接口实现类
 * @author aaron
 *
 */
@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;
	/**
	 * 获取vote
	 */
	@Override
	public Vote getVoteById(Long id) {
		return voteRepository.findOne(id);
	}
	/**
	 * 删除vote
	 */
	@Override
	@Transactional
	public void removeVote(Long id) {
		voteRepository.delete(id);
	}

}
