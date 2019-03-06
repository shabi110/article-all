<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@page import="com.alibaba.dubbo.config.ApplicationConfig"%>
<%@page import="com.dog.article.prd.base.util.ProjectConfig"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%
	application.setAttribute("staticHost",ProjectConfig.getCdnHost());
	if (application.getAttribute("version") == null) {
		application.setAttribute("version", "1.5.7");
	}
	if (application.getAttribute("staticHost") == null) {
		//application.setAttribute("staticHost", request.getScheme() + "://" + request.getServerName() +":"+request.getServerPort()+ request.getContextPath() + "/static");
		application.setAttribute("staticHost", request.getContextPath() + "/static");
	}

	if (application.getAttribute("fileUrl") == null) {
		application.setAttribute("fileUrl", ProjectConfig.getImagesHost()+"ori/");
	}
	if (application.getAttribute("imagesHost") == null) {
		application.setAttribute("imagesHost", ProjectConfig.getImagesHost());
	}
	if (application.getAttribute("imagesMinHost") == null) {
		application.setAttribute("imagesMinHost", ProjectConfig.getImagesHost()+"min/");
	}
	if (application.getAttribute("imagesSmallHost") == null) {
		application.setAttribute("imagesSmallHost", ProjectConfig.getImagesHost()+"small/");
	}
	if (application.getAttribute("imagesPhotoHost") == null) {
		application.setAttribute("imagesPhotoHost", ProjectConfig.getImagesHost()+"photo/");
	}
%>


<script type="text/javascript">
	//全局变量
	var g_requestContextPath = "${pageContext.request.contextPath}",
		ctx	 = "${pageContext.request.contextPath}";
</script>
<script src="${staticHost}/js/jquery-1.11.3.js"></script>
<script src="${staticHost}/js/TcPlayer-2.2.0.js"></script>
<script src="${staticHost}/js/video.js"></script>
<script src="${staticHost}/js/swiper.min.js"></script>
<script src="${staticHost}/js/paging.js"></script>
<script src="${staticHost}/js/flexible.js"></script>
<link rel="stylesheet" href="${staticHost}/css/swiper.min.css">
<!--手机端-->
<link rel="stylesheet" type="text/css" href="${staticHost}/css/style_phone.css" media="screen and (max-width: 960px)"/>
<!--电脑端-->
<link rel="stylesheet" type="text/css" href="${staticHost}/css/style_PC.css" media="screen and (min-width: 960px)"/>

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-135654188-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-135654188-1');
</script>
