package com.dog.article.prd.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dog.article.common.base.model.SiteBanner;
import com.dog.article.common.video.model.VideoCate;
import com.dog.article.common.video.model.VideoInfo;
import com.dog.article.service.base.service.IBaseCacheService;
import com.dog.article.service.video.service.IVideoInfoService;
import com.dog.framework.base.database.domain.page.PageResult;
import com.dog.framework.base.database.domain.page.PageSearch;
import com.dog.framework.base.database.domain.search.RangeCondition;
import com.dog.framework.base.database.domain.search.RangeConditionType;
import com.dog.framework.base.database.domain.search.SearchCondition;
import com.google.common.collect.Lists;

@Controller
public class IndexController {

	@Autowired
	private IBaseCacheService baseCacheService;
	
	
	@RequestMapping(value="")
	public String index(Model model) {
		List<VideoCate> cateList = this.baseCacheService.getVideoCateList();
		List<VideoInfo> recommendList =  this.baseCacheService.getRecommendVideoInfoList();
		List<VideoInfo> newsList =  this.baseCacheService.getNewVideoInfoList();
		List<VideoInfo> mostList =  this.baseCacheService.getMostVideoInfoList();
		List<SiteBanner> bannerList = this.baseCacheService.getSiteBannerList();
		model.addAttribute("cateList", cateList);
		model.addAttribute("recommendList", recommendList);
		model.addAttribute("newsList", newsList);
		model.addAttribute("mostList", mostList);
		model.addAttribute("bannerList", bannerList);
		return "index";
	}
	
	@Autowired
	private IVideoInfoService videoInfoService;
	
	@RequestMapping({"/v_{cateCode}.html"})
	public String videoIndex(@PathVariable String cateCode,Model model) {

		return this.videoList(cateCode,1,model);
	}
	
	@RequestMapping({"v_{cateCode}/v_{page}.html"})
	public String videoList(@PathVariable("cateCode") String cateCode,@PathVariable("page") Integer page,Model model) {
		PageSearch ps = new PageSearch();
		ps.setPage(page);
		ps.setRows(30);
		VideoInfo params=new VideoInfo();
		params.setVideoCateCode(cateCode);
		params.setStatus(1);
		SearchCondition<VideoInfo> condition=new SearchCondition<VideoInfo>(params,ps);
		condition.buildOrderByConditions("createTime", "desc");
		PageResult<VideoInfo> pr = this.videoInfoService.findByPage(condition);
		model.addAttribute("videoPage", pr);
		model.addAttribute("cateCode", cateCode);
		model.addAttribute("page", page);
		List<VideoCate> cateList = this.baseCacheService.getVideoCateList();
		model.addAttribute("cateList", cateList);
		model.addAttribute("cate", this.baseCacheService.getVideoCateByCode(cateCode));
		model.addAttribute("totalPage", totalPage(Integer.valueOf(pr.getTotal()+"")));
		List<SiteBanner> bannerList = this.baseCacheService.getSiteBannerList();
		model.addAttribute("bannerList", bannerList);
		return "video";
	}

	@RequestMapping({"{id}.html"})
	public String video(@PathVariable("id") Integer id,Model model) {
		VideoInfo video = this.baseCacheService.getVideoInfo(id);
		model.addAttribute("video", video);
		List<VideoCate> cateList = this.baseCacheService.getVideoCateList();
		model.addAttribute("cateList", cateList);
		VideoInfo befo = new VideoInfo();
		befo.setVideoCateCode(video.getVideoCateCode());
		SearchCondition<VideoInfo> b=new SearchCondition<VideoInfo>(new VideoInfo());
		b.buildRangeConditions(new RangeCondition("id",video.getId(),RangeConditionType.LessThan));
		b.buildOrderByConditions("id", "desc");
		befo = this.videoInfoService.findOneByCondition(b);
		VideoInfo next = new VideoInfo();
		next.setVideoCateCode(video.getVideoCateCode());
		SearchCondition<VideoInfo> n=new SearchCondition<VideoInfo>(next);
		n.buildRangeConditions(new RangeCondition("id",video.getId(),RangeConditionType.GreaterThan));
		n.buildOrderByConditions("id", "asc");
		next = this.videoInfoService.findOneByCondition(n);
		
		model.addAttribute("pre", befo);
		model.addAttribute("next", next);
		List<SiteBanner> bannerList = this.baseCacheService.getSiteBannerList();
		model.addAttribute("bannerList", bannerList);
		return "video.detai";
	
	}
	
	
	
	private int totalPage(int total) {
		int totalPage=total/30;
		if(total%30>0) {
			return totalPage+1;
		}
		return totalPage;
	}
}
