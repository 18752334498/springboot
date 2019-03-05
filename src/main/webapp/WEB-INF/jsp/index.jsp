<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <script type="text/javascript" src="/html/js/jquery.js"></script>
</head>
<body style="background-color: #68D851">
	<div style="border: 2px solid red;margin: auto;width: 300px;padding: 10px">
		<h1>登陆成功，这是首页。。。。。</h1>
	</div>
	
	<p>${sessionScope.user.username }:${sessionScope.user.password }</p>
	
	
</body>
</html>