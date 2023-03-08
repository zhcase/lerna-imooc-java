<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<form action="/request_servlet_war_exploded/direct/check" method="get">
    <input  name="username"/>
    <input  name="password" type="password"/>
    <input type="submit" />
</form>
</body>
</html>