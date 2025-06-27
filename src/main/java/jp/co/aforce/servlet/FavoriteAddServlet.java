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

@WebServlet("/FavoriteAddServlet")
public class FavoriteAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("views/login2.jsp");
			return;
		}

		String memberId = user.getMemberId();
		String productIdStr = (String) session.getAttribute("favoriteProductId");

		if (productIdStr == null || productIdStr.isEmpty()) {
			request.setAttribute("errorMessage", "商品IDが取得できませんでした。");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
			return;
		}

		try {
			int productId = Integer.parseInt(productIdStr);

			FavoriteDAO dao = new FavoriteDAO();
			boolean added = dao.addFavorite(memberId, productId);

			session.removeAttribute("favoriteProductId"); // ← 忘れずに

			if (added) {
				response.sendRedirect("views/favoriteAddComplete.jsp?productId=" + productId);
			} else {
				request.setAttribute("errorMessage", "すでにお気に入りに追加されています。");
				request.getRequestDispatcher("/views/productDetail.jsp").forward(request, response);
			}

		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "商品IDが不正です。");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "お気に入り追加中にエラーが発生しました。");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
	}
}
