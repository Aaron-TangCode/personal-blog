package com.hailintang.blog.service;

import java.util.List;

import com.hailintang.blog.bean.Catalog;
import com.hailintang.blog.bean.User;
/**
 * catalog服务接口
 * @author aaron
 *
 */
public interface CatalogService {
	/**
	 * 新增分类或修改分类
	 * @param catalog
	 * @return
	 */
	Catalog saveCatalog(Catalog catalog);
	
	/**
	 * 删除分类
	 * @param id
	 */
	void removeCatalog(Long id);

	/**
	 * 查询分类
	 * @param id
	 * @return
	 */
	Catalog getCatalogById(Long id);
	
	/**
	 * 查询所有分类
	 * @param user
	 * @return
	 */
	List<Catalog> listCatalogs(User user);
}