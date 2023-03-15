<%--
  Created by IntelliJ IDEA.
  User: yd
  Date: 2023/3/9
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  String url=request.getRequestURI().toString();
  response.getWriter().println(url);
%>
<%
    out.println("hello");
    session.setAttribute("user","张三");
    out.println((String)session.getAttribute("user"));
%>
<%
   String coypeRight= application.getInitParameter("copyright");
    out.println(coypeRight);
%>
</body>
</html>
