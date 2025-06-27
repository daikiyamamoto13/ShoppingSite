package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.AdminBean;
import jp.co.aforce.beans.UserBean;
import jp.co.aforce.dao.UserDAO;

@WebServlet("/ManageUsersServlet")
public class ManageUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    HttpSession session = request.getSession();
	    AdminBean admin = (AdminBean) session.getAttribute("admin");

	    // セッションにadminがない場合はログイン画面へリダイレクト
	    if (admin == null) {
	        response.sendRedirect(request.getContextPath() + "/adminLogin.jsp");
	        return;
	    }

	    try {
	        UserDAO dao = new UserDAO();
	        List<UserBean> userList = dao.getAllUsers();

	        request.setAttribute("userList", userList);
	        request.getRequestDispatcher("views/manageUsers.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "ユーザー一覧の取得に失敗しました。");
	        request.getRequestDispatcher("views/adminDashBoard.jsp").forward(request, response);
	    }
	}
}
