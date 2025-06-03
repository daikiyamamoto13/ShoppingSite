package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.UserBean;
import jp.co.aforce.dao.UserDAO;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/user-Add")
public class UserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		try {
			//DAOのインスタンスを作成
			UserDAO dao = new UserDAO();
			
			//登録処理を呼び出す
			boolean success = dao.insertUser(user);
			
			if(success) {
				
				//セッションにユーザー情報を保存
				request.getSession().setAttribute("user", user);
				//登録成功→登録確認画面へ
				response.sendRedirect("views/RegistrationConfirmation");
			} else {	
				//登録済み→既に登録されてます画面へ
				response.sendRedirect("views/user-Added.jsp");
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("views/login-error.jsp");
		}
		
	}

}
