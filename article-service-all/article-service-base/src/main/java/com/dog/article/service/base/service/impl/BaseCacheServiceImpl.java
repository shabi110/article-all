package com.dog.article.service.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dog.article.common.base.constant.RedisKeyConstant;
import com.dog.article.common.base.model.SiteBanner;
import com.dog.article.common.video.model.VideoCate;
import com.dog.article.common.video.model.VideoInfo;
import com.dog.article.service.base.service.IBaseCacheService;
import com.dog.article.service.base.service.ISiteBannerService;
import com.dog.article.service.video.service.IVideoCateService;
import com.dog.article.service.video.service.IVideoInfoService;
import com.dog.framework.base.common.utils.EmptyUtil;
import com.dog.framework.base.database.domain.page.PageResult;
import com.dog.framework.base.database.domain.page.PageSearch;
import com.dog.framework.base.database.domain.search.SearchCondition;
import com.dog.framework.base.database.redis.RedisManager;
import com.google.common.collect.Lists;

@Service
public class BaseCacheServiceImpl implements IBaseCacheService {

	@Autowired
	private IVideoInfoService videoInfoService;
	
	@Autowired
	private IVideoCateService videoCateService;
	
	@Autowired
	private ISiteBannerService siteBannerService;

    @Autowired
    @Qualifier("commonRedisManager")
    private RedisManager redisManager;
    
	//获取视频分类
	@Override
	public List<VideoCate> getVideoCateList() {
		String json = redisManager.getStringValueByKey(RedisKeyConstant.VIDEO_CATE_KEY);
		if(EmptyUtil.isEmpty(json)) {
			return this.updateVideoCateList();
		}
		List<VideoCate> cateList = JSON.parseArray(json, VideoCate.class);
		if(EmptyUtil.isEmpty(cateList)) {
			return this.updateVideoCateList();
		}
		return cateList;
	}

	@Override
	public List<VideoCate> updateVideoCateList() {
		VideoCate params=new VideoCate();
		params.setStatus(1);
		SearchCondition<VideoCate> condition=new SearchCondition<VideoCate>(params);
		condition.buildOrderByConditions("sort", "asc");
		List<VideoCate> videoCateList = this.videoCateService.findByCondition(condition);
		this.redisManager.saveString(RedisKeyConstant.VIDEO_CATE_KEY, JSON.toJSONString(videoCateList));
		return videoCateList;
	}
	
	@Override
	public VideoInfo getVideoInfo(Integer videoId) {
		String json=this.redisManager.getStringValueByKey(RedisKeyConstant.VIDEO_INFO_KEY+videoId);
		if(EmptyUtil.isEmpty(json)) {
			return this.updateVideoInfo(videoId);
		}
		return JSON.parseObject(json, VideoInfo.class);
	}
	
	@Override
	public VideoInfo updateVideoInfo(Integer videoId) {
		VideoInfo video= this.videoInfoService.findById(videoId);
		if(EmptyUtil.isEmpty(video)) {
			return null;
		}
		this.redisManager.saveString(RedisKeyConstant.VIDEO_INFO_KEY+videoId, JSON.toJSONString(video));
		return video;
	}
	
	@Override
	public List<VideoInfo> getRecommendVideoInfoList(){
		String json = redisManager.getStringValueByKey(RedisKeyConstant.VIDEO_RECOMMEND_KEY);
		if(EmptyUtil.isEmpty(json)) {
			return this.updateRecommendVideoInfoList();
		}
		List<VideoInfo> videoList = JSON.parseArray(json, VideoInfo.class);
		if(EmptyUtil.isEmpty(videoList)) {
			return this.updateRecommendVideoInfoList();
		}
		return videoList;
	}
	
	@Override
	public List<VideoInfo> updateRecommendVideoInfoList(){
		PageSearch ps =new PageSearch();
		ps.setPage(1);
		ps.setRows(10);
		VideoInfo params=new VideoInfo();
		params.setIsRecommend(1);
		SearchCondition<VideoInfo> condition=new SearchCondition<VideoInfo>(params,ps);
		condition.buildOrderByConditions("createTime", "desc");
		PageResult<VideoInfo> pr = this.videoInfoService.findByPage(condition);
		if(EmptyUtil.isEmpty(pr.getRows())) {
			return null;
		}
		redisManager.saveString(RedisKeyConstant.VIDEO_RECOMMEND_KEY, JSON.toJSONString(pr.getRows()));
		return pr.getRows();
	}
	
	@Override
	public List<VideoInfo> getNewVideoInfoList(){
		String json = redisManager.getStringValueByKey(RedisKeyConstant.VIDEO_NEW_KEY);
		if(EmptyUtil.isEmpty(json)) {
			return this.updateNewVideoInfoList();
		}
		List<VideoInfo> videoList = JSON.parseArray(json, VideoInfo.class);
		if(EmptyUtil.isEmpty(videoList)) {
			return this.updateNewVideoInfoList();
		}
		return videoList;
	}
	
	@Override
	public List<VideoInfo> updateNewVideoInfoList(){
		PageSearch ps =new PageSearch();
		ps.setPage(1);
		ps.setRows(10);
		VideoInfo params=new VideoInfo();
		SearchCondition<VideoInfo> condition=new SearchCondition<VideoInfo>(params,ps);
		condition.buildOrderByConditions("createTime", "desc");
		PageResult<VideoInfo> pr = this.videoInfoService.findByPage(condition);
		if(EmptyUtil.isEmpty(pr.getRows())) {
			return null;
		}
		redisManager.saveString(RedisKeyConstant.VIDEO_NEW_KEY, JSON.toJSONString(pr.getRows()));
		return pr.getRows();
	}
	
	@Override
	public List<VideoInfo> getMostVideoInfoList(){
		String json = redisManager.getStringValueByKey(RedisKeyConstant.VIDEO_MOST_KEY);
		if(EmptyUtil.isEmpty(json)) {
			return this.updateMostVideoInfoList();
		}
		List<VideoInfo> videoList = JSON.parseArray(json, VideoInfo.class);
		if(EmptyUtil.isEmpty(videoList)) {
			return this.updateMostVideoInfoList();
		}
		return videoList;
	}
	
	@Override
	public List<VideoInfo> updateMostVideoInfoList(){
		PageSearch ps =new PageSearch();
		ps.setPage(1);
		ps.setRows(10);
		VideoInfo params=new VideoInfo();
		SearchCondition<VideoInfo> condition=new SearchCondition<VideoInfo>(params,ps);
		condition.buildOrderByConditions("realReadNum", "desc");
		PageResult<VideoInfo> pr = this.videoInfoService.findByPage(condition);
		if(EmptyUtil.isEmpty(pr.getRows())) {
			return null;
		}
		redisManager.saveString(RedisKeyConstant.VIDEO_MOST_KEY, JSON.toJSONString(pr.getRows()));
		return pr.getRows();
	}
	
	@Override
	public VideoCate updateVideoCateByCode(String code) {
		
		VideoCate cate= new VideoCate();
		cate.setStatus(1);
		cate.setCode(code);
		cate = this.videoCateService.findOneByCondition(new SearchCondition<VideoCate>(cate));
		if(EmptyUtil.isEmpty(cate)) {
			return null;
		}
		redisManager.saveString(RedisKeyConstant.VIDEO_CATE_CODE_KEY+code, JSON.toJSONString(cate));
		return cate;
	}
	
	@Override
	public VideoCate getVideoCateByCode(String code) {
		String json = redisManager.getStringValueByKey(RedisKeyConstant.VIDEO_CATE_CODE_KEY+code);
		if(EmptyUtil.isEmpty(json)) {
			return updateVideoCateByCode(code);
		}
		VideoCate cate=JSON.parseObject(json,VideoCate.class);
		return cate;
	}
	
	@Override
	public List<SiteBanner> getSiteBannerList(){
		String json = redisManager.getStringValueByKey(RedisKeyConstant.BANNER_LIST_KEY);
		if(EmptyUtil.isEmpty(json)) {
			return updateSiteBannerList();
		}
		return JSON.parseArray(json,SiteBanner.class);
	}
	
	@Override
	public List<SiteBanner> updateSiteBannerList(){
		PageSearch ps = new PageSearch();
		ps.setPage(1);
		ps.setRows(4);
		SiteBanner b = new SiteBanner();
		b.setStatus(1);
		SearchCondition<SiteBanner> condition = new SearchCondition<SiteBanner>(b,ps);
		condition.buildOrderByConditions("sort", "asc");
		PageResult<SiteBanner> pr= this.siteBannerService.findByPage(condition);
		if(EmptyUtil.isEmpty(pr.getRows())) {
			return Lists.newArrayList();
		}
		redisManager.saveString(RedisKeyConstant.BANNER_LIST_KEY,JSON.toJSONString(pr.getRows()));
		return pr.getRows();
	}
}
