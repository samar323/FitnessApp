package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String aid=request.getParameter("aid");
			String password=request.getParameter("password");
			
			DAO dao=new DAO();
			HashMap admin=dao.getAdmin(aid, password);
			if(admin!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("adminDetails", admin);
				response.sendRedirect("AdminHome.jsp?status=pending");
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("message", "ID/Password wrong!");
				response.sendRedirect("AdminLogin.jsp");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("ExceptionPage.jsp");
		}
	}

}
