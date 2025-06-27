package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/FavoriteAddLoginCheckServlet")
public class FavoriteAddLoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  // POSTと処理統一
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");

		// 商品IDを取得
		String productId = request.getParameter("productId");

		// 商品IDが null の場合はエラーページへ
		if (productId == null || productId.isEmpty()) {
			request.setAttribute("errorMessage", "商品IDが取得できませんでした。");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
			return;
		}

		// 商品IDを一時保存（ログインしていてもいなくても）
		session.setAttribute("favoriteProductId", productId);

		if (memberId != null) {
			// ログイン済 → 直接お気に入り追加へ
			response.sendRedirect("FavoriteAddServlet");
		} else {
			// 未ログイン → ログイン後の遷移先を指定
			session.setAttribute("afterLoginFromFavoriteAdd", "FavoriteAddServlet");
			response.sendRedirect("views/login2.jsp");
		}
	}
}
