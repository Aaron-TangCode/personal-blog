package com.hailintang.blog.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hailintang.blog.bean.Catalog;
import com.hailintang.blog.bean.User;
/**
 * catalog的repository
 * @author aaron
 *
 */
public interface CatalogRepository extends JpaRepository<Catalog, Long>{
	/**
	 * 根据用户查询
	 * @param user
	 * @return
	 */
	List<Catalog> findByUser(User user);
	
	/**
	 * 根据用户和名字查询
	 * @param user
	 * @param name
	 * @return
	 */
	List<Catalog> findByUserAndName(User user,String name);
}
