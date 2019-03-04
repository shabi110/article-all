<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>500</title>
    <jsp:include page="../common/public.jsp"/>
</head>

<style>
    body{
        background-color:#fbe0e5;
    }
    .errorContent {
        width: 606px;
        height: 606px;
        position: fixed;
        top: 12%;
        left: 50%;
        margin-left:-303px;
        background: url("../../static/images/404.png") 50% 50% no-repeat;
    }

    .errorContent .errorBtn {
        margin: 500px auto 0;
        text-align: center;
    }
    .errorContent .errorBtn span{
        display: block;
        padding-bottom: 10px;
        color: #ffffff;
    }

    .errorContent .errorBtn a {
        display: block;
        width: 240px;
        height: 54px;
        line-height: 54px;
        border-radius: 54px;
        margin:0 auto;
        text-align: center;
        font-size: 24px;
        color: #f8f8f4;
        background-color: #ed018c;
    }
</style>

<body>
<div class="errorContent">
    <div class="errorBtn">
        <a href="javascript:;">返回首页</a>
    </div>
</div>


</body>
<script>
    $(document).ready(function (e) {
        var counter = 0;
        if (window.history && window.history.pushState) {
            $(window).on('popstate', function () {
                window.history.pushState('forward', null, '');
                window.history.forward(1);
            });
        }
        clearCookie();
        window.history.pushState('forward', null, ''); //在IE中必须得有这两行
        window.history.forward(1);
    });


    function clearCookie() {
        var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
        if (keys) {
            for (var i = keys.length; i--;)
                document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
        }
    }

</script>

</html>