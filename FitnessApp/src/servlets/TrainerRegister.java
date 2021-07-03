package servlets;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@WebServlet("/TrainerRegister")
public class TrainerRegister extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			String name=request.getParameter("name");
			String phone=request.getParameter("phone");
			String dt=request.getParameter("dob");
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date=df.parse(dt);
			java.sql.Date dob=new java.sql.Date(date.getTime());
			String gender=request.getParameter("gender");
			String aadhaar=request.getParameter("aadhaar");
			String address=request.getParameter("address");
			String category=request.getParameter("category");
			Part p1=request.getPart("photo");
			InputStream photo=null;
			if(p1!=null) { 
				photo=p1.getInputStream();
			}
			Part p2=request.getPart("certificate1");
			String fileName1="";
			InputStream certificate1=null;
			if(p2!=null) {
				certificate1=p2.getInputStream();
				fileName1=p2.getSubmittedFileName();
			}
			Part p3=request.getPart("certificate2");
			String fileName2="";
			InputStream certificate2=null;
			if(p3!=null) {
				certificate2=p3.getInputStream();
				fileName2=p3.getSubmittedFileName();
			}
			String exp=request.getParameter("exp");
			String description=request.getParameter("description");
			HashMap trainer=new HashMap();
			trainer.put("email", email);
			trainer.put("password", password);
			trainer.put("name", name);
			trainer.put("phone", phone);
			trainer.put("gender", gender);
			trainer.put("dob", dob);
			trainer.put("address", address);
			trainer.put("aadhaar", aadhaar);
			trainer.put("category", category);
			trainer.put("photo", photo);
			trainer.put("certificate1", certificate1);
			trainer.put("certificate2", certificate2);
			trainer.put("fileName1", fileName1);
			trainer.put("fileName2", fileName2);
			trainer.put("exp", exp);
			trainer.put("description", description);
			DAO dao=new DAO();
			boolean result=dao.insertTrainer(trainer);
			if(result==true) {
				HttpSession session=request.getSession();
				session.setAttribute("message", "Trainer Added Successfully!");
				response.sendRedirect("TrainerLogin.jsp");
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("message", "Trainer Already Registered!");
				response.sendRedirect("TrainerLogin.jsp");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("ExceptionPage.jsp");
		}
	}

}
