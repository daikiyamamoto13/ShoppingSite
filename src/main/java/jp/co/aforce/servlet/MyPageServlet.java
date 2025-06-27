package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.beans.UserBean;
import jp.co.aforce.dao.ProductDAO;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("views/login-in.jsp");
			return;
		}

		String memberId = user.getMemberId();

		try {
			ProductDAO dao = new ProductDAO();
			List<ProductBean> myItems = dao.getItemsByMemberId(memberId);

			request.setAttribute("myItems", myItems);
			request.getRequestDispatcher("views/productMyPage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "商品一覧の取得に失敗しました");
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}
	}
}
