﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <meta name="description" content="不超过150个字符"/>
    <meta name="keywords" content=""/>
    <title>51撸吧</title>
    <meta name="keywords" content="成人图片，成人小说，成人电影" />
    <meta name="description" content="大型的新型的成人网站，免费提供成人图片，成人小说，成人电影等内容．" />

    <jsp:include page="common/public.jsp"/>

</head>

<body>
	
    <div class="header">
        <div class="wrap clearfix">
            <div class="menu">
               <!--  <a class="cur" href="javascript:">图片区</a>
                <a href="javascript:" class="hide">小说区</a> -->
                <!-- <a href="javascript:">电影区</a> -->
               <!--  <a href="javascript:" class="hide"><i>HOT</i>博彩区</a>
                <a href="javascript:" class="hide">撸撸区</a>
                <a href="javascript:" class="hide">MP3</a> -->
            </div>
            <a href="javascript:" class="logo">
                <img src="${staticHost}/images/logo.png" alt="">
            </a>
        </div>
    </div>

    <div class="main">
        <div class="banner swiper-container">
            <div class="swiper-wrapper">
            	<c:forEach var = "item" items="${bannerList }">
            	 <div class="swiper-slide">
                   <a href="${item.linkUrl }" target="_blank"><img src="${item.imgUrl }" alt=""></a> 
                </div>
            	</c:forEach>
            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination"></div>
            <!-- Add Arrows -->
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
        </div>

        <div class="nav">
            <div class="wrap">
                <div class="column clearfix hide">
                    <h5>图片区</h5>
                    <div class="link">
                        <a class="cur" href="javascript:">卡通动漫</a>
                        <a href="javascript:">亚洲图片</a>
                        <a href="javascript:">欧美图片</a>
                        <a href="javascript:">偷拍自拍</a>
                        <a href="javascript:">乱伦熟女</a>
                        <a href="javascript:">精品套图</a>
                        <a href="javascript:">同性美图</a>
                        <a href="javascript:">美腿丝袜</a>
                    </div>
                </div>

                <div class="column clearfix hide">
                    <h5>小说区</h5>
                    <div class="link">
                        <a href="javascript:">情感小说</a>
                        <a href="javascript:">校园春色</a>
                        <a href="javascript:">武侠古典</a>
                        <a href="javascript:">家庭乱伦</a>
                        <a href="javascript:">另类小说</a>
                        <a href="javascript:">性爱技巧</a>
                        <a href="javascript:">本站APP</a>
                        <a href="javascript:">曝光黑庄</a>
                    </div>
                </div>

                <div class="column clearfix ">
                    <h5>电影区</h5>
                    <div class="link">
                    	<c:forEach var="item" items="${cateList }">
                    		<a href="../v_${item.code }.html" target="_blank">${item.name }</a>
                    	</c:forEach>
                    </div>
                </div>

                <div class="column clearfix hide">
                    <h5>博彩区</h5>
                    <div class="link">
                        <a href="javascript:">BBIN视讯</a>
                        <a href="javascript:">AG视讯</a>
                        <a href="javascript:">MG电子</a>
                        <a href="javascript:">PT电子</a>
                        <a href="javascript:">五分彩</a>
                        <a href="javascript:">时时彩</a>
                        <a href="javascript:">老虎机</a>
                        <a href="javascript:">体育投注</a>
                    </div>
                </div>

                <div class="column clearfix hide">
                    <h5>撸撸区</h5>
                    <div class="link">
                        <a href="javascript:">推女郎</a>
                        <a href="javascript:">头条女神</a>
                        <a href="javascript:">3Agril</a>
                        <a href="javascript:">推女神</a>
                        <a href="javascript:">爱蜜社</a>
                        <a href="javascript:">美媛新刊</a>
                        <a href="javascript:">秀人网</a>
                        <a href="javascript:">短视频</a>
                    </div>
                </div>

                <div class="column clearfix hide">
                    <h5>MP3区</h5>
                    <div class="link">
                        <a href="javascript:">诱惑短篇</a>
                        <a href="javascript:">性爱录音</a>
                        <a href="javascript:">少妇白洁</a>
                        <a href="javascript:">欲望都市</a>
                        <a href="javascript:">百花盛放</a>
                        <a href="javascript:">笑傲神雕</a>
                        <a href="javascript:">淫荡爱娃</a>
                        <a href="javascript:">本站APP</a>
                    </div>
                </div>

            </div>
        </div>

        <div class="tabColumn">
            <div class="wrap">
                <div class="tabBtn">
                    <a class="cur" href="javascript:">站长推荐</a>
                    <a href="javascript:">最新发布</a>
                    <a href="javascript:">人气热播</a>
                </div>
                <div class="tabCon">
                    <div class="list clearfix">
                    	<c:forEach var="item" items="${recommendList }">
	                    	<a href="../${item.id}.html">
	                            <div class="img">
	                                <img src="${item.videoImageUrl }" alt="${item.videoImageAlt }">
	                            </div>
	                            <p>${item.title }</p>
	                        </a>
                    	</c:forEach>
                        
                       
                     <%--   <a href="javascript:">
                            <div class="img">
                                <img src="${staticHost}/images/img1.jpg" alt="">
                            </div>
                            <p>女捜査官BDSM媚薬拷问 轮姦鬼イカせに絶叫抵抗！！ 黒川すみれ</p>
                        </a> --%>
                    </div>

                    <div class="list clearfix hide">
                    	<c:forEach var="item" items="${newsList }">
	                    	<a href="../${item.id}.html">
	                            <div class="img">
	                                <img src="${item.videoImageUrl }" alt="${item.videoImageAlt }">
	                            </div>
	                            <p>${item.title }</p>
	                        </a>
                    	</c:forEach>
                    
                        <%-- 
                        <a href="javascript:">
                            <div class="img">
                                <img src="${staticHost}/images/img1.jpg" alt="">
                            </div>
                            <p>女捜査官BDSM媚薬拷问 轮姦鬼イカせに絶叫抵抗！！ 黒川すみれ2</p>
                        </a> --%>
                    </div>

                    <div class="list clearfix hide">
                    
                    	<c:forEach var="item" items="${mostList }">
	                    	<a href="../${item.id}.html">
	                            <div class="img">
	                                <img src="${item.videoImageUrl }" alt="${item.videoImageAlt }">
	                            </div>
	                            <p>${item.title }</p>
	                        </a>
                    	</c:forEach>
                      
                    </div>

                </div>

            </div>
        </div>

        <div class="advertisment">
            <div class="wrap">
                <a href="http://bbo122.com/" target="_blank">
                    <img src="${staticHost}/images/img2.gif" alt="">
                </a>
            </div>
        </div>

    </div>

    <div class="footer">
        <div class="wrap">
            <div class="txt">
                <span>提示：点击查看永久收藏和无法观看电影说明</span> | <a href="javascript:">关闭浮窗</a>
            </div>
            <p></p>
            <p>警告：如果您未滿18歲或您當地法律許可之法定年齡、或是對情色反感或是衛道人士建議您離開本站！ 本站歸類為限制級、限定為成年者已具有完整行為能力且願接受本站內影音內容、及各項條款之網友才可瀏覽，未滿18歲謝絕進入。
                本站已遵照「iWIN網路內容防護機構」進行分類，如有家長發現未成年兒童／少年瀏覽此站、請按照此方法過濾本站內容 >>『網站分級制度』</p>
        </div>
    </div>

    <script type="text/javascript">

        $(document).ready(function(){

            var swiper = new Swiper('.swiper-container', {
                spaceBetween: 30,
                centeredSlides: true,
                loop: true,
                autoplay: {
                    delay: 2500,
                    disableOnInteraction: false,
                },
                pagination: {
                    el: '.swiper-pagination',
                    clickable: true,
                },
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                },
            });

            $('.tabBtn a').each(function(i){
                $(this).click(function(){
                    $('.tabBtn a').removeClass('cur');
                    $('.tabCon .list').hide();
                    $(this).addClass('cur');
                    $('.tabCon .list').eq(i).show();
                })
            })

        });

    </script>
</body>
</html>
