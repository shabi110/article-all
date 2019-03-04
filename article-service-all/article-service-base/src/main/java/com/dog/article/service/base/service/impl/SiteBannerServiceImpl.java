package com.dog.article.service.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dog.article.common.base.model.SiteBanner;
import com.dog.article.dal.base.dao.SiteBannerMapper;
import com.dog.article.service.base.service.ISiteBannerService;
import com.dog.framework.base.database.mysql.service.impl.BaseMyBatisService;

@Service
public class SiteBannerServiceImpl extends BaseMyBatisService<SiteBanner> implements ISiteBannerService{
	
	@Autowired
	private SiteBannerMapper mapper;
	
	
	public SiteBannerServiceImpl() {
		super.setEntityClazz(SiteBanner.class);
	}

}
