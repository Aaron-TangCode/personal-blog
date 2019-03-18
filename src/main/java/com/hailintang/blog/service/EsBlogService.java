package com.hailintang.blog.service;
 

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hailintang.blog.bean.User;
import com.hailintang.blog.bean_es.EsBlog;
import com.hailintang.blog.vo.TagVO;
/**
 * esBlog服务接口
 * @author aaron
 *
 */
public interface EsBlogService {
 	
	/**
	 * 删除esBlog
	 * @param id
	 */
	void removeEsBlog(String id);
	
	/**
	 * 保存或更新esBlog
	 * @param esBlog
	 * @return
	 */
	EsBlog updateEsBlog(EsBlog esBlog);
	
	/**
	 * 查询esBlog
	 * @param blogId
	 * @return
	 */
	EsBlog getEsBlogByBlogId(Long blogId);
	/**
	 * 查询esBlog
	 * @param title\summary
	 * @return
	 */
	Page<EsBlog> getEsBlogByBlogTitleOrSummary(String title,String summary,Pageable pageable);
 
	/**
	 * 最新的博客列表，分页
	 * @param keyword
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listNewestEsBlogs(String keyword, Pageable pageable);
 
	/**
	 * 最火的博客列表，分页
	 * @param keyword
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listHotestEsBlogs(String keyword, Pageable pageable);
	
	/**
	 * 博客列表，分页
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listEsBlogs(Pageable pageable);
	/**
	 * 最新top5
	 * @return
	 */
	List<EsBlog> listTop5NewestEsBlogs();
	
	/**
	 *最热top5
	 */
	List<EsBlog> listTop5HotestEsBlogs();
	
	/**
	 * 最热top30标签
	 * @return
	 */
	List<TagVO> listTop30Tags();

	/**
	 * 最热top12用户
	 * @return
	 */
	List<User> listTop12Users();
}
