package com.osf.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String uri = "/WEB-INF"+request.getRequestURI()+".jsp";
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("utf-8");
//		PrintWriter pw = response.getWriter();
//		pw.println(uri);
//		request.setAttribute("cho", "rong");
		
//		RequestDispatcher rd = request.getRequestDispatcher(uri);
//		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
