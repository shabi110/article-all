package com.dog.article.common.video.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "video_cate")
public class VideoCate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * 分类名称
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * 分类编码
	 */
	@Column(name="code")
	private String code;
	
	/**
	 * 状态 1 正常 2 停用
	 */
	@Column(name="status")
	private Integer status;
	
	/**
	 * 文章分类列表页title
	 */
	@Column(name="page_title")
	private String pageTitle;
	
	/**
	 * 文章分类列表页关键字
	 */
	@Column(name="keywords")
	private String keywords;
	
	/**
	 * 文章分类列表页描述
	 */
	@Column(name="description")
	private String description;
	
	/**
	 * 类型 1-百科 2-热点
	 */
	@Column(name="type")
	private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
