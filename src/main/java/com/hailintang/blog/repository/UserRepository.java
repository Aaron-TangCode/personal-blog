package com.hailintang.blog.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hailintang.blog.bean.User;
/**
 * user的repository
 * @author aaron
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{
	/**
	 * 根据用户名字分页查询
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<User> findByNameLike(String name, Pageable pageable);
	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	/**
	 * 根据用户名列表查询
	 * @param usernames
	 * @return
	 */
	List<User> findByUsernameIn(Collection<String> usernames);
}
