package com.dog.article.common.base.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Table(name = "site_banner")
public class SiteBanner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型 1000 首页推荐文章 1001 直播间页面  1002 视频页面
     */
    @Column(name = "type")
    private String type;

    /**
     * 链接地址
     */
    @Column(name = "link_url")
    private String linkUrl;

    /**
     * 图片地址
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 图片alt
     */
    @Column(name = "img_alt")
    private String imgAlt;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;
    
    
    /**
     * 状态 1 正常 2 停用
     */
    @Column(name = "status")
    private Integer status;

    private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgAlt() {
		return imgAlt;
	}

	public void setImgAlt(String imgAlt) {
		this.imgAlt = imgAlt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
    

}