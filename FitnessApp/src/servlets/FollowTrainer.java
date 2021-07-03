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
@WebServlet("/FollowTrainer")
public class FollowTrainer extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			HashMap userDetails =(HashMap)session.getAttribute("userDetails");
			if(userDetails!=null){
				String user_email=(String)userDetails.get("email");
				String trainer_email=request.getParameter("trainer_email");
				HashMap follow = new HashMap();
				follow.put("user_email", user_email);
				follow.put("trainer_email", trainer_email);
				DAO dao = new DAO();
				boolean result = dao.insertUserFollowTrainer(follow);
				if (result == true) {
					session.setAttribute("message", "Successfully Followed!");
					response.sendRedirect("TrainerDetails.jsp?email="+trainer_email);
				} else {
					session.setAttribute("message", "You are already followed a trainer!");
					response.sendRedirect("TrainerDetails.jsp?email="+trainer_email);
				}
			} else {
				session.setAttribute("message", "Plz LOGIN First!");
				response.sendRedirect("index.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("ExceptionPage.jsp");
		}
	}

}
