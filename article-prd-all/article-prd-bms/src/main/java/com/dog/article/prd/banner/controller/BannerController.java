package com.dog.article.prd.banner.controller;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dog.article.common.base.model.SiteBanner;
import com.dog.article.prd.component.annotation.UnSecurity;
import com.dog.article.service.base.service.IBaseCacheService;
import com.dog.article.service.base.service.IBaseSysParamService;
import com.dog.article.service.base.service.ISiteBannerService;
import com.dog.article.service.file.service.IFileUploadService;
import com.dog.framework.base.common.utils.EmptyUtil;
import com.dog.framework.base.common.utils.LogFormatUtil;
import com.dog.framework.base.database.domain.page.PageResult;
import com.dog.framework.base.database.domain.page.PageSearch;
import com.dog.framework.base.database.domain.returns.DataResponse;
import com.dog.framework.base.database.domain.search.SearchCondition;
import com.dog.framework.web.annotation.session.NeedSession;
import com.dog.framework.web.annotation.session.UnSession;

@Controller
@RequestMapping(value = "banner")
public class BannerController {

	private static Logger baseLog = LoggerFactory.getLogger("baseLog");

	@Autowired
	private ISiteBannerService siteBannerService;

	@Autowired
	private IBaseCacheService baseCacheService;

	@Autowired
	private IFileUploadService fileUploadService;
	
	@Autowired
	private IBaseSysParamService baseSysParamService;

	@NeedSession("/banner")
	@UnSecurity
	@RequestMapping("")
	public String index() {
		return "banner/index";
	}

	@UnSession
	@UnSecurity
	@RequestMapping("deleteBanner")
	@ResponseBody
	public DataResponse deleteBanner(Integer id) {
		siteBannerService.removeById(id);
		baseCacheService.updateSiteBannerList();
		return new DataResponse();
	}

	@UnSession
	@UnSecurity
	@RequestMapping("saveBanner")
	@ResponseBody
	public DataResponse saveBanner(SiteBanner banner, MultipartFile photo) {
		
		if(EmptyUtil.isEmpty(banner.getLinkUrl())) {
			return new DataResponse(1001,"banner链接不能为空");
		}
		
		if(EmptyUtil.isEmpty(banner.getSort())) {
			return new DataResponse(1001,"banner排序不能为空");
		}

		String bannerImg = "";
		if (EmptyUtil.isNotEmpty(photo)) {
			baseLog.info(LogFormatUtil.getActionFormat("开始上传头像"));
			String host = baseSysParamService.getValueByKey("imagePath");
			try {
				InputStream input = photo.getInputStream();
				int count = input.available();
				byte[] fileByte = new byte[count];
				input.read(fileByte);
				String fileName = photo.getOriginalFilename();
				baseLog.info(LogFormatUtil.getActionFormat("当前上传请求的file的文件名:" + fileName));
				input.read(fileByte);
				fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
				String fileId = this.fileUploadService.storeFile(fileByte, fileName);
				bannerImg = fileId + "." + fileName;
				baseLog.info(LogFormatUtil.getActionFormat("结束上传头像"));
				baseLog.info(LogFormatUtil.getActionFormat("上传后的文件名：" + bannerImg));
				banner.setImgUrl(host+bannerImg);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			if(EmptyUtil.isEmpty(banner.getId()))
				return new DataResponse(1001,"请上传banner图片");
		}
		
		if(EmptyUtil.isEmpty(banner.getId())) {
			this.siteBannerService.save(banner);
		}else {
			this.siteBannerService.modifyEntity(banner);
		}
		this.baseCacheService.updateSiteBannerList();
		return new DataResponse();
	}
	
	@UnSession
	@UnSecurity
	@RequestMapping("bannerList")
	@ResponseBody
	public DataResponse getBannerList(PageSearch ps) {
		SearchCondition<SiteBanner> condition = new SearchCondition<SiteBanner>(new SiteBanner(),ps);
		condition.buildOrderByConditions("sort", "asc");
		PageResult<SiteBanner> pr = this.siteBannerService.findByPage(condition);
		return new DataResponse(1000,pr);
	}
	
	
	@UnSession
	@UnSecurity
	@RequestMapping("getBanner")
	@ResponseBody
	public DataResponse getBanner(Integer bannerId) {
		SiteBanner banner= this.siteBannerService.findById(bannerId);
		return new DataResponse(1000,banner);
	}
	
	
	@UnSession
	@UnSecurity
	@RequestMapping("disableBanner")
	@ResponseBody
	public DataResponse disableBanner(Integer bannerId) {
		SiteBanner banner= this.siteBannerService.findById(bannerId);
		if(EmptyUtil.isEmpty(banner)) {
			return new DataResponse(1001,"banner不存在");
		}
		
		SiteBanner updateBanner=new SiteBanner();
		updateBanner.setStatus(2);
		updateBanner.setId(bannerId);
		this.siteBannerService.modifyEntity(updateBanner);
		
		return new DataResponse();
	}
	
}
