package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ProductAddLoginCheckServlet
 */
@WebServlet("/ProductAddLoginCheckServlet")
public class ProductAddLoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session =request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		if(memberId != null) {
			response.sendRedirect("views/productAdd.jsp");	
		} else {
			session.setAttribute("afterLoginFromProductAdd", "views/productAdd.jsp");
			response.sendRedirect("views/login2.jsp");
		}
	}

	
	}


