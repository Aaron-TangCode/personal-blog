package com.hailintang.blog.vo;
import java.io.Serializable;

import com.hailintang.blog.bean.Catalog;
/**
 * Catalog实体类
 * @author aaron
 *
 */
public class CatalogVO implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	private String username;
	private Catalog catalog;
	
	public CatalogVO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

}
