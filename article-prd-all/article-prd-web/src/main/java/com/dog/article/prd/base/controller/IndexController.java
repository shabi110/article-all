package com.dog.article.prd.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dog.article.common.video.model.VideoCate;
import com.dog.article.common.video.model.VideoInfo;
import com.dog.article.service.base.service.IBaseCacheService;

@Controller
@RequestMapping("")
public class IndexController {

	@Autowired
	private IBaseCacheService baseCacheService;
	
	
	@RequestMapping(value="")
	public String index(Model model) {
		List<VideoCate> cateList = this.baseCacheService.getVideoCateList();
		List<VideoInfo> recommendList =  this.baseCacheService.getRecommendVideoInfoList();
		List<VideoInfo> newsList =  this.baseCacheService.getNewVideoInfoList();
		List<VideoInfo> mostList =  this.baseCacheService.getMostVideoInfoList();
		model.addAttribute("cateList", cateList);
		model.addAttribute("recommendList", recommendList);
		model.addAttribute("newsList", newsList);
		model.addAttribute("mostList", mostList);
		return "index";
	}
	
	
	
}
