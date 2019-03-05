<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();//springboot默认根路径是 '/'，可以在属性文件进行配置
	String base = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <base href="<%=base%>" />
    <link rel="stylesheet" type="text/css" href="/html/css/style.css"/>
	<script type="text/javascript" src="/html/js/jquery.js"></script>
    <script language="javascript">
		$(function(){
		    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
			$(window).resize(function(){  
		   		$('.error').css({'position':'absolute','left':($(window).width()-490)/2});
	    	})  
		});  
	</script>
</head>
<body>
    <div class="error">
	    <h2>非常遗憾，您访问的页面不存在！</h2>
	    <p>看到这个提示，就自认倒霉吧!</p>
    </div>
</body>
</html>