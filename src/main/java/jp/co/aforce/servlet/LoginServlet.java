package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.UserBean;
import jp.co.aforce.dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		
		UserDAO dao = new UserDAO();
		
		try {
			
			UserBean user = dao.findUser(memberId,password);
		
		
		
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

		
			response.sendRedirect("views/user-menu.jsp");
		} else {
			response.sendRedirect("views/login-error.jsp");
		}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
