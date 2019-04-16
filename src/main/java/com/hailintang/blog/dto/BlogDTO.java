package com.hailintang.blog.dto;
//接收前端的传来的数据

import org.springframework.beans.factory.support.AbstractBeanFactory;

public class BlogDTO {
	private Long id;
	private Integer readSize;
	private Integer voteSize;
	private String title;
	private String summary;
	private String username;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getReadSize() {
		return readSize;
	}
	public void setReadSize(Integer readSize) {
		this.readSize = readSize;
	}
	public Integer getVoteSize() {
		return voteSize;
	}
	public void setVoteSize(Integer voteSize) {
		this.voteSize = voteSize;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "BlogDTO [id=" + id + ", readSize=" + readSize + ", voteSize=" + voteSize + ", title=" + title
				+ ", summary=" + summary + ", username=" + username + "]";
	}
	
	
}
