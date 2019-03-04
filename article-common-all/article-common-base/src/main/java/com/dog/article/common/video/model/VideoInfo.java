package com.dog.article.common.video.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "video_info")
public class VideoInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 标题
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 分类ID
	 */
	@Column(name = "video_cate_id")
	private Integer videoCateId;
	
	/**
	 * 分类名称
	 */
	@Column(name = "video_cate_name")
	private String videoCateName;
	
	/**
	 * 分类编码
	 */
	@Column(name = "video_cate_code")
	private String videoCateCode;
	
	/**
	 * 描述
	 */
	@Column(name = "description")
	private String description;
	
	/**
	 * 阅读数量
	 */
	@Column(name = "read_num")
	private Integer readNum;
	
	/**
	 * 实际阅读数量
	 */
	@Column(name = "real_read_num")
	private Integer realReadNum;
	
	/**
	 * 封面图片的alt
	 */
	@Column(name = "image_alt")
	private String imageAlt;
	
	/**
	 * 封面图片的url
	 */
	@Column(name = "url")
	private String url;
	
	/**
	 * 内容
	 */
	@Column(name = "content")
	private String content;
	
	/**
	 * 标签id串 逗号隔开
	 */
	@Column(name = "target_ids")
	private String targetIds;
	
	/**
	 * 专题id串 逗号隔开
	 */
	@Column(name = "subject_ids")
	private String subjectIds;
	
	/**
	 * 作者ID
	 */
	@Column(name = "author_id")
	private Integer authorId;
	
	/**
	 * 作者code
	 */
	@Column(name = "author_code")
	private String authorCode;
	
	/**
	 * 作者名称
	 */
	@Column(name = "author_name")
	private String authorName;
	
	/**
	 * 更新人
	 */
	@Column(name = "modify_user_id")
	private Integer modifyUserId;
	
	/**
	 * 更新时间
	 */
	@Column(name = "modify_time")
	private Date modifyTime;
	
	/**
	 * 添加人
	 */
	@Column(name = "create_user_id")
	private Integer createUserId;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 文章状态 0 草稿 1 发布 2 删除
	 */
	@Column(name = "status")
	private Integer status;
	
	/**
	 * 发布时间
	 */
	@Column(name = "publish_time")
	private Date publishTime;
	
	/**
	 * 发送时间
	 */
	@Column(name = "send_time")
	private Date sendTime;
	
	/**
	 * 视频封面alt
	 */
	@Column(name = "video_image_alt")
	private String videoImageAlt;
	
	/**
	 * 视频封面url
	 */
	@Column(name = "video_image_url")
	private String videoImageUrl;
	
	/**
	 * 页面keywords搜索关键字
	 */
	@Column(name = "key_words")
	private String keyWords;
	
	/**
	 * 视频播放地址
	 */
	@Column(name = "video_play_url")
	private String videoPlayUrl;
	
	/**
	 * 是否推荐 1-是 0-否
	 */
	@Column(name = "is_recommend")
	private Integer isRecommend;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getVideoCateId() {
		return videoCateId;
	}

	public void setVideoCateId(Integer videoCateId) {
		this.videoCateId = videoCateId;
	}

	public String getVideoCateName() {
		return videoCateName;
	}

	public void setVideoCateName(String videoCateName) {
		this.videoCateName = videoCateName;
	}

	public String getVideoCateCode() {
		return videoCateCode;
	}

	public void setVideoCateCode(String videoCateCode) {
		this.videoCateCode = videoCateCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	public Integer getRealReadNum() {
		return realReadNum;
	}

	public void setRealReadNum(Integer realReadNum) {
		this.realReadNum = realReadNum;
	}

	public String getImageAlt() {
		return imageAlt;
	}

	public void setImageAlt(String imageAlt) {
		this.imageAlt = imageAlt;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTargetIds() {
		return targetIds;
	}

	public void setTargetIds(String targetIds) {
		this.targetIds = targetIds;
	}

	public String getSubjectIds() {
		return subjectIds;
	}

	public void setSubjectIds(String subjectIds) {
		this.subjectIds = subjectIds;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Integer getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getVideoImageAlt() {
		return videoImageAlt;
	}

	public void setVideoImageAlt(String videoImageAlt) {
		this.videoImageAlt = videoImageAlt;
	}

	public String getVideoImageUrl() {
		return videoImageUrl;
	}

	public void setVideoImageUrl(String videoImageUrl) {
		this.videoImageUrl = videoImageUrl;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getVideoPlayUrl() {
		return videoPlayUrl;
	}

	public void setVideoPlayUrl(String videoPlayUrl) {
		this.videoPlayUrl = videoPlayUrl;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	
	
}
