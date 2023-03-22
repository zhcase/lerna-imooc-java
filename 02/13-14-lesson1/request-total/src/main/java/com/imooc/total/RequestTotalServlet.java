package com.imooc.total;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RequestTotalServlet", value = "/rt")
public class RequestTotalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        List<String> timeList = (List) context.getAttribute("timeList");
        List<Integer> valueList = (List) context.getAttribute("valueList");
        response.setContentType("text/html;charset=utf-8");
//              response.getWriter().println(timeList.toString());
//              response.getWriter().println("<br/>");
//              response.getWriter().println(valueList.toString());
        Map result = new HashMap();
        result.put("timeList", timeList);
        result.put("valueList", valueList);
        System.out.println(result);
        String json = JSON.toJSONString(result);
        System.out.println(json);
        response.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
