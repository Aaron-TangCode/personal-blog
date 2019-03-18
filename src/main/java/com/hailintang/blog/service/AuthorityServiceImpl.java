package com.hailintang.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hailintang.blog.bean.Authority;
import com.hailintang.blog.repository.AuthorityRepository;


/**
 * 权限服务实现类
 * @author aaron
 *
 */
@Service
public class AuthorityServiceImpl  implements AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	/**
	 * 根据id获取权限
	 */
	@Override
	public Authority getAuthorityById(Long id) {
		return authorityRepository.findOne(id);
	}

}
