package com.hailintang.blog.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hailintang.blog.bean.Blog;
import com.hailintang.blog.bean.User;
import com.hailintang.blog.service.BlogService;
import com.hailintang.blog.service.UserService;
import com.hailintang.blog.util.ConstraintViolationExceptionHandler;
import com.hailintang.blog.vo.Response;


/**
 * 博客主页控制器
 * @author aaron
 *
 */
@RestController
@RequestMapping("/blog")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")//指定角色权限才能操作方法
public class BlogController_Admin {
 
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 查看博客列表和搜索框功能
	 * @return
	 */
	@GetMapping
	public ModelAndView list(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="name",required=false,defaultValue="") String name,
			Model model) {
	 
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		//name是在admins页面的博客管理搜索框输出
		Page<Blog> page = blogService.listBlogsByNameLike(name, pageable);
		List<Blog> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("blogList", list);
		return new ModelAndView(async==true?"blog/list :: #mainContainerRepleace":"blog/list", "blogModel", model);
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
	 * 修改博客
	 */
	@PostMapping
	public ResponseEntity<Response> create(Blog blog) {
		try {
			System.out.println(blog);
			Blog newBlog = blogService.getBlogById(blog.getId());
			newBlog.setReadSize(blog.getReadSize());
			newBlog.setVoteSize(blog.getVoteSize());
			newBlog.setTitle(blog.getTitle());
			newBlog.setSummary(blog.getSummary());
			blogService.saveBlog(newBlog);
		
		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		
		return ResponseEntity.ok().body(new Response(true, "处理成功", blog));
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
		Blog blog = blogService.getBlogById(id);
		model.addAttribute("blog", blog);
		return new ModelAndView("blog/edit", "blogModel", model);
	}
}


