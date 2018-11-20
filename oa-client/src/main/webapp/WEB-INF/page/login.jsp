<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OA系统登录页面</title>
<script type="text/javascript" src="${ctx}/js/jquery-1.6.4.js"></script>

</head>
<body>
<p><font color="red">${msg}</font></p>
<form action="${ctx}/login" method="POST">
用户名：<input type="text" name="username"/> <br>
密&nbsp;&nbsp;&nbsp;码：<input type="text" name="password"/><br>
<input type="submit" value="登录" />
</form>
</body>
</html>