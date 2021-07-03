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

@WebServlet("/TrainerLogin")
public class TrainerLogin extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			
			DAO dao=new DAO();
			HashMap trainer=dao.getTrainer(email, password);
			if(trainer!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("trainerDetails", trainer);
				response.sendRedirect("TrainerHome.jsp");
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("message", "User ID/Password wrong!");
				response.sendRedirect("TrainerLogin.jsp");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("ExceptionPage.jsp");
		}
	}

}
