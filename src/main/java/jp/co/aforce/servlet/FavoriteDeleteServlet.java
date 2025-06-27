package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.UserBean;
import jp.co.aforce.dao.FavoriteDAO;

@WebServlet("/FavoriteDeleteServlet")
public class FavoriteDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/views/login2.jsp");
			return;
		}

		String memberId = user.getMemberId();
		String productIdStr = request.getParameter("productId");

		try {
			int productId = Integer.parseInt(productIdStr);

			FavoriteDAO dao = new FavoriteDAO();
			dao.removeFavorite(memberId, productId);

			// 削除後にお気に入り一覧ページへ戻る
			response.sendRedirect(request.getContextPath() + "/FavoriteListServlet");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "お気に入り削除中にエラーが発生しました。");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
	}
}
