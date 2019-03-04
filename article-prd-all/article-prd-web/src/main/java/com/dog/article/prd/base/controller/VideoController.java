package com.dog.article.prd.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dog.article.common.video.model.VideoInfo;
import com.dog.article.service.base.service.IBaseCacheService;
import com.dog.article.service.video.service.IVideoInfoService;
import com.dog.framework.base.database.domain.page.PageResult;
import com.dog.framework.base.database.domain.page.PageSearch;
import com.dog.framework.base.database.domain.search.SearchCondition;

@Controller
@RequestMapping("")
public class VideoController {
	
	@Autowired
	private IBaseCacheService baseCacheService;
	
	
}
