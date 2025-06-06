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
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/userEdit")
public class UserEditServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		
		
		//セッション取得
		HttpSession session = request.getSession();
		UserBean editUser = (UserBean) session.getAttribute("editUser");
		
		//データがなければエラー画面に遷移する
		if (editUser == null) {

			response.sendRedirect("views/sessionError.jsp");
			return;

		}
		

		try {
			
			jp.co.aforce.dao.UserDAO dao = new jp.co.aforce.dao.UserDAO();
			boolean success = dao.updateUser(editUser);
			
			if(success) {
				
			//userの情報をeditUserに上書き	
			session.setAttribute("user", editUser);
			//成功したら完了画面に遷移
			request.getRequestDispatcher("views/edit-complete.jsp").forward(request,response);
			} else {
				
			//失敗したら失敗画面に遷移	
			response.sendRedirect("views/update-failure.jsp");
			}
		
			
		}catch (Exception e) {
			e.printStackTrace();
			
			//例外の場合はエラー画面に遷移
			response.sendRedirect("views/error.jsp");
		}
		
		
	}
}
