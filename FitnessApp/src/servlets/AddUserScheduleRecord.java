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
@WebServlet("/AddUserScheduleRecord")
public class AddUserScheduleRecord extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			HashMap userDetails = (HashMap) session.getAttribute("userDetails");
			if (userDetails != null) {
				String email=(String)userDetails.get("email");
				String ex1 = request.getParameter("ex1");
				ex1=ex1!=null?"yes":"no";
				String ex2 = request.getParameter("ex2");
				ex2=ex2!=null?"yes":"no";
				String ex3 = request.getParameter("ex3");
				ex3=ex3!=null?"yes":"no";
				String ex4 = request.getParameter("ex4");
				ex4=ex4!=null?"yes":"no";
				String meal1 = request.getParameter("meal1");
				meal1=meal1!=null?"yes":"no";
				String meal2 = request.getParameter("meal2");
				meal2=meal2!=null?"yes":"no";
				String meal3 = request.getParameter("meal3");
				meal3=meal3!=null?"yes":"no";
				String meal4 = request.getParameter("meal4");
				meal4=meal4!=null?"yes":"no";
				int ex_rating=0,meal_rating=0;
				if(ex1.equalsIgnoreCase("yes"))
					ex_rating++;
				if(ex2.equalsIgnoreCase("yes"))
					ex_rating++;
				if(ex3.equalsIgnoreCase("yes"))
					ex_rating++;
				if(ex4.equalsIgnoreCase("yes"))
					ex_rating++;
				if(meal1.equalsIgnoreCase("yes"))
					meal_rating++;
				if(meal2.equalsIgnoreCase("yes"))
					meal_rating++;
				if(meal3.equalsIgnoreCase("yes"))
					meal_rating++;
				if(meal4.equalsIgnoreCase("yes"))
					meal_rating++;
				ex_rating=ex_rating*100/4;
				meal_rating=meal_rating*100/4;
				HashMap record = new HashMap();
				record.put("email", email);
				record.put("ex1", ex1);
				record.put("ex2", ex2);
				record.put("ex3", ex3);
				record.put("ex4", ex4);
				record.put("meal1", meal1);
				record.put("meal2", meal2);
				record.put("meal3", meal3);
				record.put("meal4", meal4);
				record.put("ex_rating", ex_rating);
				record.put("meal_rating", meal_rating);
				DAO dao = new DAO();
				boolean result = dao.insertScheduleRecord(record);
				if (result == true) {
					session.setAttribute("message", "Record Added Successfully!");
					response.sendRedirect("UserFollowTrainer.jsp");
				} else {
					session.setAttribute("message", "Record insertion failed!");
					response.sendRedirect("UserFollowTrainer.jsp");
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
