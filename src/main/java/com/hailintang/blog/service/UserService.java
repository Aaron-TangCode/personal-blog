package com.hailintang.blog.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hailintang.blog.bean.User;
/**
 * user服务接口
 * @author aaron
 *
 */
public interface UserService {
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	User saveUser(User user);
	
	/**
	 * 删除用户
	 * @param id
	 */
	void removeUser(Long id);
	
	/**
	 * 删除列表里面的所有用户
	 * @param users
	 * @return
	 */
	void removeUsersInBatch(List<User> users);
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	User updateUser(User user);
	
	/**
	 * 查询用户
	 * @param id
	 * @return
	 */
	User getUserById(Long id);
	
	/**
	 * 获取用户列表
	 * @return
	 */
	List<User> listUsers();
	
	/**
	 * 模糊查询
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<User> listUsersByNameLike(String name, Pageable pageable);
	
	/**
	 * 根据用户名称，列表查询
	 * @param usernames
	 * @return
	 */
	List<User> listUsersByUsernames(Collection<String> usernames);
}
