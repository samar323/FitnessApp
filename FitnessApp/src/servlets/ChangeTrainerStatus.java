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
@WebServlet("/ChangeTrainerStatus")
public class ChangeTrainerStatus extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String status = request.getParameter("status");

			DAO dao = new DAO();
			boolean result = dao.changeTrainerStatus(email, status);
			if (result == true) {
				HttpSession session = request.getSession();
				session.setAttribute("message", "Trainer's status Updated successfully!");
				response.sendRedirect("AdminHome.jsp");
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("message", "Trainer's status Updation Failed!");
				response.sendRedirect("AdminHome.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("ExceptionPage.jsp");
		}
	}
}
