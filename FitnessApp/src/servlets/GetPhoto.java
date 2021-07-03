package servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
@WebServlet("/GetPhoto")
public class GetPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String type=request.getParameter("type");
			DAO dao=new DAO();
			byte photo[]=dao.getPhoto(email,type);
			if(photo==null) {
				photo=new byte[3500];
				ServletContext ctx=getServletContext();
				InputStream i=ctx.getResourceAsStream("images/SamplePhoto.jpeg");
				i.read(photo);
			}
			response.getOutputStream().write(photo);
		}catch(Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("ExceptionPage.jsp");
		}
	}

}
