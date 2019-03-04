package com.dog.article.service.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dog.article.common.video.model.VideoInfo;
import com.dog.article.dal.video.dao.VideoInfoMapper;
import com.dog.article.service.video.service.IVideoInfoService;
import com.dog.framework.base.database.mysql.service.impl.BaseMyBatisService;

@Service
public class VideoInfoServiceImpl extends BaseMyBatisService<VideoInfo> implements IVideoInfoService {

	@Autowired
	private VideoInfoMapper mapper;
	
	
	public VideoInfoServiceImpl() {
		super.setEntityClazz(VideoInfo.class);
	}
}
