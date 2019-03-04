package com.dog.article.service.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dog.article.common.video.model.VideoCate;
import com.dog.article.dal.video.dao.VideoCateMapper;
import com.dog.article.service.video.service.IVideoCateService;
import com.dog.framework.base.database.mysql.service.impl.BaseMyBatisService;

@Service
public class VideoCateServiceImpl extends BaseMyBatisService<VideoCate> implements IVideoCateService{
	
	@Autowired
	private VideoCateMapper mapper;
	
	
	public VideoCateServiceImpl() {
		super.setEntityClazz(VideoCate.class);
	}

}
