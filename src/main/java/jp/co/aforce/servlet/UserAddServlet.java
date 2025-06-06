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
 * Servlet implementation class SignUp
 */
@WebServlet("/views/user-add-confirmation")
public class UserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");

		try {
		    UserDAO dao = new UserDAO();
		    boolean success = dao.insertUser(user);

		    if (success) {
		        response.sendRedirect("add-complete.jsp");
		    } else {
		        response.sendRedirect("user-Added.jsp"); // 登録済み
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    response.sendRedirect("login-error.jsp");
		}


	}

}


