package com.example.exam_system.utils;

import com.alibaba.fastjson.JSON;
import com.example.exam_system.pojo.SendInfoTorF;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TrueOrFalse {
    private SendInfoTorF information;
    public void sendMessageTrue(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/json;charset=utf-8");
        information=new SendInfoTorF();
        information.setIsOK("true");
        String jsonString = JSON.toJSONString(information);
        PrintWriter writer = response.getWriter();
        writer.write(jsonString);
    }
    public void sendMessageFalse(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/json;charset=utf-8");
        information=new SendInfoTorF();
        information.setIsOK("false");
        String jsonString = JSON.toJSONString(information);
        PrintWriter writer = response.getWriter();
        writer.write(jsonString);
    }
}
