package com.dog.article.service.base.service;

import java.util.List;

import com.dog.article.common.video.model.VideoCate;
import com.dog.article.common.video.model.VideoInfo;

public interface IBaseCacheService {
	
	
	public List<VideoCate> getVideoCateList();
	
	public List<VideoCate> updateVideoCateList();
	
	public VideoInfo getVideoInfo(Integer videoId);
	
	public VideoInfo updateVideoInfo(Integer videoId);
	
	public List<VideoInfo> getRecommendVideoInfoList();
	
	public List<VideoInfo> updateRecommendVideoInfoList();
	
	public List<VideoInfo> getNewVideoInfoList();
	
	public List<VideoInfo> updateNewVideoInfoList();
	
	public List<VideoInfo> getMostVideoInfoList();
	
	public List<VideoInfo> updateMostVideoInfoList();

	public VideoCate updateVideoCateByCode(String code);
	
	public VideoCate getVideoCateByCode(String code); 
	
}
