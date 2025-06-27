package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MyPageLoginCheckServlet
 */
@WebServlet("/MyPageLoginCheckServlet")
public class MyPageLoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		if(memberId != null) {
			response.sendRedirect("views/user-menu.jsp");	
		} else {
			session.setAttribute("afrterLoginFormMyPage", "MyPageServlet");
			response.sendRedirect("views/login-in.jsp");
		}
	}


	

}
