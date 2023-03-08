<%--
  Created by IntelliJ IDEA.
  User: yd
  Date: 2023/3/8
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="include/header.jsp" %>
  <%
    out.println("<h1>新闻标题</h1>");
    out.println("<p>新闻正文</p>");

  %>
<hr>
<%@include file="include/footer.jsp" %>
</body>
</html>
