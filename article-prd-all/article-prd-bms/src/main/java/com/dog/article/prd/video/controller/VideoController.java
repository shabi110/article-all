package com.dog.article.prd.video.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dog.article.common.video.model.VideoCate;
import com.dog.article.common.video.model.VideoInfo;
import com.dog.article.prd.component.annotation.UnSecurity;
import com.dog.article.prd.component.session.EmployeeSession;
import com.dog.article.service.base.service.IBaseCacheService;
import com.dog.article.service.video.service.IVideoCateService;
import com.dog.article.service.video.service.IVideoInfoService;
import com.dog.framework.base.common.utils.EmptyUtil;
import com.dog.framework.base.database.domain.page.PageResult;
import com.dog.framework.base.database.domain.page.PageSearch;
import com.dog.framework.base.database.domain.returns.BaseResult;
import com.dog.framework.base.database.domain.returns.DataResponse;
import com.dog.framework.base.database.domain.search.SearchCondition;
import com.dog.framework.web.annotation.session.NeedSession;
import com.dog.framework.web.annotation.session.UnSession;

@Controller
@RequestMapping(value = "video")
public class VideoController {

	@Autowired
	private IBaseCacheService baseCacheService;
	
	@Autowired
	private IVideoInfoService videoInfoService;
	
	@Autowired
	private IVideoCateService videoCateService;
	
	
	@NeedSession("/video")
	@UnSecurity
	@RequestMapping("")
	public String index(Model model) {
		List<VideoCate> cateList = this.baseCacheService.getVideoCateList();
		model.addAttribute("cateList", cateList);
		return "video/index";
	}
	
	
	
	@UnSession
	@UnSecurity
	@RequestMapping("deleteVideo")
	@ResponseBody
	public DataResponse deleteVideo(Integer id) {
		videoInfoService.removeById(id);
		baseCacheService.updateMostVideoInfoList();
		baseCacheService.updateRecommendVideoInfoList();
		baseCacheService.updateNewVideoInfoList();
		return new DataResponse();
	}

	@UnSession
	@UnSecurity
	@RequestMapping("saveVideo")
	@ResponseBody
	public DataResponse saveVideo(VideoInfo video) {
		EmployeeSession se=EmployeeSession.getEmployeeSession();
		if(EmptyUtil.isEmpty(video.getTitle())) {
			return new DataResponse(1001,"影片标题不能为空");
		}
		if(EmptyUtil.isEmpty(video.getVideoImageUrl())) {
			return new DataResponse(1001,"影片封面图片不能为空");
		}
		if(EmptyUtil.isEmpty(video.getVideoPlayUrl())) {
			return new DataResponse(1001,"影片播放地址不能为空");
		}
		if(EmptyUtil.isEmpty(video.getVideoCateId())) {
			return new DataResponse(1001,"影片分类不能为空");
		}
		VideoCate cate=this.videoCateService.findById(video.getVideoCateId());
		video.setVideoCateCode(cate.getCode());
		video.setVideoCateName(cate.getName());
		if(EmptyUtil.isEmpty(video.getId())) {
			video.setCreateTime(new Date());
			video.setCreateUserId(se.getEmployeeId());
			this.videoInfoService.save(video);
		}else {
			BaseResult br = this.videoInfoService.modifyEntity(video);
			if(br.isSuccess()) {
				
			}
			baseCacheService.updateVideoInfo(video.getId());
		}
		
		baseCacheService.updateMostVideoInfoList();
		baseCacheService.updateRecommendVideoInfoList();
		baseCacheService.updateNewVideoInfoList();
		return new DataResponse();
	}
	
	
	@UnSession
	@UnSecurity
	@RequestMapping("videoList")
	@ResponseBody
	public DataResponse videoList(VideoInfo video,PageSearch ps) {
		
		SearchCondition<VideoInfo> condition = new SearchCondition<VideoInfo>(new VideoInfo(),ps);
		condition.buildOrderByConditions("createTime", "desc");
		if(EmptyUtil.isNotEmpty(video.getTitle())) {
			condition.buildLikeConditions("title", "%"+video.getTitle()+"%");
		}
		
		PageResult<VideoInfo> pr = this.videoInfoService.findByPage(condition);
		return new DataResponse(1000,pr);
	}
	
	
	@UnSession
	@UnSecurity
	@RequestMapping("getVideo")
	@ResponseBody
	public DataResponse getVideo(Integer id) {
		VideoInfo video= this.videoInfoService.findById(id);
		return new DataResponse(1000,video);
	}
	
	
	@UnSession
	@UnSecurity
	@RequestMapping("disableVideo")
	@ResponseBody
	public DataResponse disableVideo(Integer id) {
		VideoInfo video= this.videoInfoService.findById(id);
		if(EmptyUtil.isEmpty(video)) {
			return new DataResponse(1001,"影片不存在");
		}
		
		VideoInfo updateBanner=new VideoInfo();
		updateBanner.setStatus(2);
		updateBanner.setId(id);
		this.videoInfoService.modifyEntity(updateBanner);
		baseCacheService.updateVideoInfo(video.getId());
		baseCacheService.updateMostVideoInfoList();
		baseCacheService.updateRecommendVideoInfoList();
		baseCacheService.updateNewVideoInfoList();
		return new DataResponse();
	}
	
	
}
