package com.hailintang.blog.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 点赞
 * @author aaron
 *
 */
@Entity//实体
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id//主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//点赞的唯一标识
 
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;//所属用户
	
	@Column(nullable = false)
	@org.hibernate.annotations.CreationTimestamp//由数据库自动创建时间
	private Timestamp createTime;
 
	protected Vote() {
	}
	
	public Vote(User user) {
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
 
	public Timestamp getCreateTime() {
		return createTime;
	}
 
}
