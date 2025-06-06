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
 * Servlet implementation class ConfirmationServlet
 */
@WebServlet("/views/ConfirmationServlet")
public class AddConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//フォームから値を受け取る

		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String address = request.getParameter("address");
		String mailAddress = request.getParameter("mailAddress");

		//インスタンス作成

		UserBean user = new UserBean();

		//セッターで値を詰める
		user.setMemberId(memberId);
		user.setPassword(password);
		user.setLastName(lastName);
		user.setFirstName(firstName);
		user.setAddress(address);
		user.setMailAddress(mailAddress);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		request.getRequestDispatcher("/views/user-AddConfirmation.jsp").forward(request,response);
		
	
	}

}
