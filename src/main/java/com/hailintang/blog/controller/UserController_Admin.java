package com.hailintang.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hailintang.blog.bean.Authority;
import com.hailintang.blog.bean.User;
import com.hailintang.blog.service.AuthorityService;
import com.hailintang.blog.service.UserService;
import com.hailintang.blog.util.ConstraintViolationExceptionHandler;
import com.hailintang.blog.vo.Response;

/**
 * 管理员控制器
 * @author aaron
 *
 */
@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")//指定角色权限才能操作方法
public class UserController_Admin {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService  authorityService;

	/**
	 * 查询所用用户
	 * @return
	 */
	@GetMapping
	public ModelAndView list(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="name",required=false,defaultValue="") String name,
			Model model) {
	 
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<User> page = userService.listUsersByNameLike(name, pageable);
		List<User> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("userList", list);
		return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list", "userModel", model);
	}

	/**
	 * 获取form表单
	 * @param model
	 * @return
	 */
	@GetMapping("/add")
	public ModelAndView createForm(Model model) {
		model.addAttribute("user", new User(null, null, null, null));
		return new ModelAndView("users/add", "userModel", model);
	}

	/**
	 * admins的用于管理的编辑页面和添加页面
	 * @param user
	 * @param authorityId
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Response> addOrEditUser(User user, Long authorityId) {
		
		List<Authority> authorities = new ArrayList<>();
		//判断用户是管理员还是普通用户
		authorities.add(authorityService.getAuthorityById(authorityId));
		user.setAuthorities(authorities);
		//判断是增加操作，还是修改操作
		if(user.getId() == null) {
			user.setEncodePassword(user.getPassword());//加密密码
		}else {
			//判断密码是否做了改变
			User originalUser = userService.getUserById(user.getId());
			String rawPassword = originalUser.getPassword();
			//给密码加密
			PasswordEncoder  encoder = new BCryptPasswordEncoder();
			String encodePasswd = encoder.encode(user.getPassword());
			//密码是否修改了
			boolean isMatch = encoder.matches(rawPassword, encodePasswd);
			if (!isMatch) {
				user.setEncodePassword(user.getPassword());
			}else {
				user.setPassword(user.getPassword());
			}
		}
		//保存或修改信息
		try {
			userService.saveUser(user);
		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		
		return ResponseEntity.ok().body(new Response(true, "处理成功", user));
	}

	/**
	 * 删除用户
	 * @param id
	 * @param model
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
		try {
			userService.removeUser(id);
		} catch (Exception e) {
			return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
		}
		return  ResponseEntity.ok().body( new Response(true, "处理成功"));
	}
	
	/**
	 * 修改用户
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping(value = "edit/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return new ModelAndView("users/edit", "userModel", model);
	}
}
