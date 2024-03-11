package com.example.exam_system.web;

import com.alibaba.fastjson.JSON;
import com.example.exam_system.pojo.SendMaxPage;
import com.example.exam_system.service.MultSelectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/multselect/*")
public class MultSelectServlet extends BaseServlet{
    MultSelectService multSelectService=new MultSelectService();
    public void getMaxPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        int maxPage = multSelectService.getMaxPage();
        SendMaxPage sendMaxPage = new SendMaxPage();
        sendMaxPage.setMaxPage(maxPage);
        String jsonString = JSON.toJSONString(sendMaxPage);
        response.getWriter().write(jsonString);
    }
}
