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
@WebServlet("/AddSchedule")
public class AddSchedule extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			HashMap trainerDetails = (HashMap) session.getAttribute("trainerDetails");
			if (trainerDetails != null) {
				String email=(String)trainerDetails.get("email");
				String ex1 = request.getParameter("ex1");
				String ex2 = request.getParameter("ex2");
				String ex3 = request.getParameter("ex3");
				String ex4 = request.getParameter("ex4");
				String meal1 = request.getParameter("meal1");
				String meal2 = request.getParameter("meal2");
				String meal3 = request.getParameter("meal3");
				String meal4 = request.getParameter("meal4");
				HashMap schedule = new HashMap();
				schedule.put("email", email);
				schedule.put("ex1", ex1);
				schedule.put("ex2", ex2);
				schedule.put("ex3", ex3);
				schedule.put("ex4", ex4);
				schedule.put("meal1", meal1);
				schedule.put("meal2", meal2);
				schedule.put("meal3", meal3);
				schedule.put("meal4", meal4);
				DAO dao = new DAO();
				boolean result = dao.insertSchedule(schedule);
				if (result == true) {
					session.setAttribute("message", "Trainer's Schedule Added Successfully!");
					response.sendRedirect("TrainerHome.jsp");
				} else {
					session.setAttribute("message", "Trainer's Schedule insertion failed!");
					response.sendRedirect("TrainerHome.jsp");
				}
			} else {
				session.setAttribute("message", "Plz LOGIN First!");
				response.sendRedirect("TrainerLogin.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("ExceptionPage.jsp");
		}
	}

}
