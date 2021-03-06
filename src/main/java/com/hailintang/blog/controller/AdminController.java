package com.hailintang.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hailintang.blog.vo.Menu;


/**
 * 管理员页面
 * @author aaron
 *
 */
@Controller
@RequestMapping("/admins")
public class AdminController {

	/**
	 * 获取后台管理主页面
	 * @return
	 */
	@GetMapping
	public ModelAndView listUsers(Model model) {
		List<Menu> list = new ArrayList<>();
		list.add(new Menu("用户管理", "/users"));
		list.add(new Menu("博客管理", "/blog"));
		list.add(new Menu("分类管理","/catalog"));
		model.addAttribute("list", list);
		return new ModelAndView("/admins/index", "model", model);
	}
}
