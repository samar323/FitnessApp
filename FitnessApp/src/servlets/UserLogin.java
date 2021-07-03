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

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			
			DAO dao=new DAO();
			HashMap user=dao.getUser(email, password);
			if(user!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("userDetails", user);
				response.sendRedirect("UserHome.jsp");
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("message", "User ID/Password wrong!");
				response.sendRedirect("index.jsp");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("ExceptionPage.jsp");
		}
	}

}
