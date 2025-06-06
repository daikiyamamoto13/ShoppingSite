package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.UserBean;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/userDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		UserBean user = (UserBean) session.getAttribute("user");
		if(user == null) {
			response.sendRedirect("views/sessionError.jsp");
			return;
		}
		
		try {
			jp.co.aforce.dao.UserDAO dao = new jp.co.aforce.dao.UserDAO();
			boolean success = dao.deleteUser(user.getMemberId());
			
			if(success) {
				session.invalidate();
				response.sendRedirect("views/delete-success.jsp");
			} else {
				response.sendRedirect("views/delete-failure.jsp");
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("views/error.jsp");
		}
		
		
	}

}
