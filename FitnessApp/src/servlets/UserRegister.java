package servlets;

import java.io.*;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.DAO;

@MultipartConfig
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			String name=request.getParameter("name");
			String phone=request.getParameter("phone");
			int age=Integer.parseInt(request.getParameter("age"));
			String gender=request.getParameter("gender");
			String address=request.getParameter("address");
			Part p=request.getPart("photo");
			InputStream photo=null;
			if(p!=null) {
				photo=p.getInputStream();
			}
			HashMap user=new HashMap();
			user.put("email", email);
			user.put("password", password);
			user.put("name", name);
			user.put("phone", phone);
			user.put("gender", gender);
			user.put("age", age);
			user.put("address", address);
			user.put("photo", photo);
			DAO dao=new DAO();
			boolean result=dao.insertUser(user);
			if(result==true) {
				HttpSession session=request.getSession();
				session.setAttribute("message", "User Added Successfully!");
				response.sendRedirect("index.jsp");
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("message", "User Already Registered!");
				response.sendRedirect("index.jsp");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("ExceptionPage.jsp");
		}
	}

}
