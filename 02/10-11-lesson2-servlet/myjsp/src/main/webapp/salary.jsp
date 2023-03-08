<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<table>
    <tr>
        <th>year</th>
        <th>salary</th>
    </tr>
    <%
        for(int i=0;i<=50;i++){
            out.println("<tr>");
            out.println("<td>"+i+"</td>");
            int sal=0;
            if(i<=5){
                sal=1500+i*150;
            }else if(i>5&&i<=10){
                sal=1500+150*5+300*(i-5);
            }else if(i>10){
                sal=1500+150*5+300*5+375*(i-10);
            }
            out.println("<td>"+sal+"</td>");
            out.println("</tr>");
        }
    %>
<%--    <tr>--%>
<%--        <th>0</th>--%>
<%--        <th>1500</th>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <th>1</th>--%>
<%--        <th>1500</th>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <th>2</th>--%>
<%--        <th>1800</th>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <th>2</th>--%>
<%--        <th>1800</th>--%>
<%--    </tr>--%>
</table>
</body>
</html>