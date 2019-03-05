<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>后台管理</title>
    <jsp:include page="../common/public.jsp"/>
    <link href="${pageContext.request.contextPath}/static/css/live.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/static/js/lib/date/WdatePicker.js"></script>
</head>

<body>
<div class="mainTitle">
    <div class="titleContent">
        <span class="lgLine ilblock ovfHid ml20 verMid"></span> <span
            class="verMid ml10 colorF">影片管理</span>
    </div>
    <jsp:include page="../common/userSet.jsp"/>
</div>
<div class="mainArea flexWrap posRel">
    <jsp:include page="../common/nav.jsp">
        <jsp:param value="影片列表" name="name"/>
        <jsp:param value="影片管理" name="pName"/>
    </jsp:include>
    <div class="mainBox flexCon">
        <div class="mainSearch">
            <div style="right: 10px; top: 20px;">
                <button type="button" class="pageBtn addBtn">添加影片</button>
                <button type="button" class="pageBtn searchBtn">查询影片</button>
                <button type="button" class="pageBtn refBtn">刷新</button>
            </div>
        </div>

        <div class="mainContent">
            <div class="bar" style="height: 40px;">
                <div id="showpager" class="pages posRel ac fr">
                    <div class="color6 ilblock">共<span class="allrecords"></span> 条, 每页 <span class="eachrecords"></span>条</div>
                    <p title="上一页" class="writeBtn prev"><span class="iconLeft"></span></p>
                    <div class="ilblock eachItem verTop"></div>
                    <p title="下一页" class="writeBtn next"><span class="iconRight"></span></p>
                    <div class="ilblock pagego">
                        <input type="text" class="ilblock ipt keypageipt fz12" placeholder="跳转页数"/>
                        <span title="go" class="btn ilblock">确定</span>
                    </div>
                </div>
            </div>

            <div class="tableTitle flexWrap ac">
                <div style="width: 20%">标题</div>
                <div style="width: 10%">分类</div>
                <div style="width: 25%">影片封面地址</div>
                <div style="width: 25%">播放地址</div>
                <div style="width: 5%">是否推荐</div>
                <div style="width: 5%">状态</div>
                <div style="width: 10%">操作</div>
            </div>

            <div id="showrecords" class="ac"></div>
        </div>
    </div>
</div>
<jsp:include page="../common/changePassword.jsp"/>
<div id="mask" class="bodyMask opa80 hide"></div>

<!-- 查询客户窗口 -->
<div id="searchWin" class="popForm popFormWide wtBg posFixed hide">
    <form id="searchBox" action="${pageContext.request.contextPath}/video/videoList" method="post">
        <div class="title flexWrap ">
            <div class="flexCon fz16 liveTitle">查询影片</div>
            <div class="pt5"><a href="" onclick="closePopForm(this);return !1;" class="close block">
                <span class="icon block"></span></a>
            </div>
        </div>

        <div class="wtBg ptb10 ac">
            <span class="formLabel ar mr10">影片标题</span>
            <input type="text" class="serIpt" name="title" placeholder="请输入昵称"/>
        </div>

        <div style="text-align: center" class="pt20">
            <button id="searchBtn" type="button" class="pageBtn popFormBtn">确认</button>
            <button class="pageBtn popFormBtn" onclick="closePopForm(this);return !1;">取消</button>
        </div>
    </form>
</div>

<!-- 添加客户窗口 -->
<div id="addWin" class="popForm popFormWide wtBg posFixed hide">
    <form id="tableForm" action="${pageContext.request.contextPath}/video/saveVideo" method="post">
        <input type="hidden" id="id" name="id"/>
        <div class="title flexWrap ">
            <div class="flexCon fz16 liveTitle">添加影片</div>
            <div class="pt5"><a href="" onclick="closePopForm(this);return !1;" class="close block">
                <span class="icon block"></span></a>
            </div>
        </div>

        <div class="wtBg ptb10 ac">
            <span class="formLabel ar mr10">影片标题</span>
            <input type="text" class="serIpt" id="title" name="title"/>
        </div>
         <div class="clearfix ptb10">
            <div class="serSelect fl posRel">
                <span class="formLabel ar mr10" style="width: 157px">影片分类</span>
            </div>
            <div tabindex="0" class="serSelect fl posRel mr10">
                <div class="text posRel wtBg plr10 serIpt videoCate" style="line-height: 23px;">请选择</div>
                <ul tabindex="0" class="posAbs wtBg ovfHid serIpt">
                	<c:forEach	var="item" items="${cateList }">
                		<li data-value="${item.id }">${item.name }</li>
                	</c:forEach>
                    
                </ul>
                <input type="hidden" id="videoCateId" name="videoCateId" value="0"/>
            </div>
        </div>
		<div class="wtBg ptb10 ac">
            <span class="formLabel ar mr10">影片封面图片</span>
            <input type="text" class="serIpt" id="videoImageUrl" name="videoImageUrl"/>
        </div>
        <div class="wtBg ptb10 ac">
            <span class="formLabel ar mr10">影片播放地址</span>
            <input type="text" class="serIpt" id="videoPlayUrl" name="videoPlayUrl" placeholder="影片播放地址"/>
        </div>

        <div class="clearfix ptb10">
            <div class="serSelect fl posRel">
                <span class="formLabel ar mr10" style="width: 157px">是否首页推荐</span>
            </div>
            <div tabindex="0" class="serSelect fl posRel mr10">
                <div class="text posRel wtBg plr10 serIpt isRecommendStatus" style="line-height: 23px;">否</div>
                <ul tabindex="0" class="posAbs wtBg ovfHid serIpt">
                	<li data-value="0">否</li>
                    <li data-value="1">是</li>
                </ul>
                <input type="hidden" id="isRecommend" name="isRecommend" value="0"/>
            </div>
        </div>

        <div style="text-align: center" class="pt20">
            <button id="submitBtn" type="submit" class="pageBtn popFormBtn">确定</button>
            <button class="pageBtn popFormBtn" onclick="closePopForm(this);return !1;">取消</button>
        </div>
    </form>
</div>



</body>
<script src="${pageContext.request.contextPath}/static/js/lib/artDialog/dialog-min.js"></script>
<script src="${pageContext.request.contextPath}/static/modules/video/index.js"></script>
</html>