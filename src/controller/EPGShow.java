package controller;

import model.bl.EPGModelDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EPGShow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cName=req.getParameter("cName");
        EPGModelDAO epgModelDAO=new EPGModelDAO();
        String result=epgModelDAO.getEPG(cName);
        PrintWriter writer=resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
