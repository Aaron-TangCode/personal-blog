package com.hailintang.blog.service;

import com.hailintang.blog.bean.Authority;

/**
 * 权限服务接口
 * @author aaron
 *
 */
public interface AuthorityService {
	 /**
	  * 根据id获取权限
	  * @param id
	  * @return
	  */
	Authority getAuthorityById(Long id);
}
