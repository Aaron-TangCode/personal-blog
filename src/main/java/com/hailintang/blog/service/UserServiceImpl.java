package com.hailintang.blog.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hailintang.blog.bean.User;
import com.hailintang.blog.repository.UserRepository;
/**
 * user服务接口实现类
 * @author aaron
 *
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	/**
	 * 保存用户
	 */
	@Transactional
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	/**
	 * 删除用户
	 */
	@Transactional
	@Override
	public void removeUser(Long id) {
		userRepository.delete(id);
	}
	/**
	 * 批量删除用户
	 */
	@Transactional
	@Override
	public void removeUsersInBatch(List<User> users) {
		userRepository.deleteInBatch(users);
	}
	/**
	 * 更新用户
	 */
	@Transactional
	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	/**
	 * 查询用户
	 */
	@Override
	public User getUserById(Long id) {
		return userRepository.getOne(id);
	}
	/**
	 * 查询所有用户
	 */
	@Override
	public List<User> listUsers() {
		return userRepository.findAll();
	}
	/**
	 * 模糊查询
	 */
	@Override
	public Page<User> listUsersByNameLike(String name, Pageable pageable) {
		// 模糊查询
		name = "%" + name + "%";
		Page<User> users = userRepository.findByNameLike(name, pageable);
		return users;
	}
	/**
	 * 根据用户名查询用户
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}
	/**
	 * 根据用户名，列表查询
	 */
	@Override
	public List<User> listUsersByUsernames(Collection<String> usernames) {
		return userRepository.findByUsernameIn(usernames);
	}

}
