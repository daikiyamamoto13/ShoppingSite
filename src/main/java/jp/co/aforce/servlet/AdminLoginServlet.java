package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.AdminBean;
import jp.co.aforce.dao.AdminDAO;

/**
 * Servlet implementation class AdiminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
		        String username = request.getParameter("username");
		        String password = request.getParameter("password");

		        AdminDAO dao = new AdminDAO();
		        AdminBean admin = dao.login(username, password);

		        if (admin != null) {
		            // ログイン成功
		            HttpSession session = request.getSession();
		            session.setAttribute("admin", admin);
		            response.sendRedirect("views/adminDashBoard.jsp");  // 管理者用TOPページに遷移
		        } else {
		            // ログイン失敗
		            request.setAttribute("errorMessage", "ユーザー名またはパスワードが違います。");
		            request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("errorMessage", "ログイン中にエラーが発生しました。");
		        request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
		    }
	}

}
