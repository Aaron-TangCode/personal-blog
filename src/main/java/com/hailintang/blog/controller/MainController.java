package com.hailintang.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hailintang.blog.bean.Authority;
import com.hailintang.blog.bean.User;
import com.hailintang.blog.service.AuthorityService;
import com.hailintang.blog.service.UserService;


/**
 * 主页控制器
 * @author aaron
 *
 */
@Controller
public class MainController {
	
	private static final Long ROLE_USER_AUTHORITY_ID = 2L;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService  authorityService;
	/**
	 * 主页
	 * @return
	 */
	@GetMapping("/")
	public String root() {
		return "redirect:/index";
	}
	/**
	 * 重定向到博客主页
	 * @return
	 */
	@GetMapping("/index")
	public String index() {
		return "redirect:/blogs";
	}

	/**
	 * 获取登录界面
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	/**
	 * 错误页面
	 * @param model
	 * @return
	 */
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		model.addAttribute("errorMsg", "登陆失败，账号或者密码错误！");
		return "login";
	}
	/**
	 * 注册页面
	 * @return
	 */
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	/**
	 * 普通用户注册用户
	 * @param user
	 * @param result
	 * @param redirect
	 * @return
	 */
	@PostMapping("/register")
	public String registerUser(User user) {
		List<Authority> authorities = new ArrayList<>();
		authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
		user.setAuthorities(authorities);
		//给密码用强哈希加密
		PasswordEncoder  encoder = new BCryptPasswordEncoder();
		String encodePasswd = encoder.encode(user.getPassword());
		user.setPassword(encodePasswd);
		userService.saveUser(user);
		return "redirect:/login";
	}
}
