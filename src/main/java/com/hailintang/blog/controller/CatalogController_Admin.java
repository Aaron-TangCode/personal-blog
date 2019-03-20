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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hailintang.blog.bean.Catalog;
import com.hailintang.blog.service.CatalogService;
import com.hailintang.blog.util.ConstraintViolationExceptionHandler;
import com.hailintang.blog.vo.Response;

/**
 * 管理员控制器
 * @author aaron
 *
 */
@RestController
@RequestMapping("/catalog")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")//指定角色权限才能操作方法
public class CatalogController_Admin {
		
	@Autowired
	private CatalogService catalogService;
	

	/**
	 * 查询所有分类
	 * @return
	 */
	@GetMapping
	public ModelAndView list(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="name",required=false,defaultValue="") String name,
			Model model) {
	 
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		
		Page<Catalog> page = catalogService.listCatalogsByName(name,pageable);
		List<Catalog> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("catalogList", list);
		return new ModelAndView(async==true?"catalog/list :: #mainContainerRepleace":"catalog/list", "catalogModel", model);
	}
	
	/**
	 * 修改分类信息
	 */
	@PostMapping
	public ResponseEntity<Response> create(Catalog catalog) {
		try {
			System.out.println(catalog);
			Catalog newCatalog = catalogService.getCatalogById(catalog.getId());
			newCatalog.setName(catalog.getName());
			catalogService.saveCatalog(newCatalog);
		
		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		
		return ResponseEntity.ok().body(new Response(true, "处理成功", catalog));
	}

	/**
	 * 修改分类的表单
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping(value = "edit/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		Catalog catalog = catalogService.getCatalogById(id);
		model.addAttribute("catalog", catalog);
		return new ModelAndView("catalog/edit", "catalogModel", model);
	}
}
