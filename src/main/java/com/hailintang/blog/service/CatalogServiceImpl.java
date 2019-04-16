package com.hailintang.blog.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hailintang.blog.bean.Catalog;
import com.hailintang.blog.bean.User;
import com.hailintang.blog.repository.CatalogRepository;
/**
 * catalog服务接口实现类
 * @author aaron
 *
 */
@Service
public class CatalogServiceImpl implements CatalogService{

	@Autowired
	private CatalogRepository catalogRepository;
	
	/**
	 * 保存分类
	 */
	@Override
	public Catalog saveCatalog(Catalog catalog) {
		// 判断重复
		List<Catalog> list = catalogRepository.findByUserAndName(catalog.getUser(), catalog.getName());
		if(list !=null && list.size() > 0) {
			throw new IllegalArgumentException("该分类已经存在了");
		}
		return catalogRepository.save(catalog);
	}
	/**
	 * 删除分类
	 */
	@Override
	public void removeCatalog(Long id) {
		catalogRepository.delete(id);
	}
	/**
	 * 查询分类
	 */
	@Override
	public Catalog getCatalogById(Long id) {
		return catalogRepository.findOne(id);
	}
	/**
	 * 查询所有分类
	 */
	@Override
	public List<Catalog> listCatalogs(User user) {
		return catalogRepository.findByUser(user);
	}
	/**
	 * 根据名字查询分类
	 */
	@Override
	public Page<Catalog> listCatalogsByName(String name,Pageable page) {
		// TODO Auto-generated method stub
		name = "%" + name + "%";
		Page<Catalog> catalogs = catalogRepository.findByNameLike(name, page);
		return catalogs;
	}

}
