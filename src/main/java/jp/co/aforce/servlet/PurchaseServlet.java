package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.dao.SalesDAO;

@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

		// カートが空 or null の場合はリダイレクト
		if (cart == null || cart.isEmpty()) {
			response.sendRedirect("views/cart-empty.jsp");
			return;
		}

		// ログインユーザーの確認
		String buyerMemberId = (String) session.getAttribute("memberId");
		if (buyerMemberId == null) {
			response.sendRedirect("views/login.jsp");
			return;
		}

		try {
			SalesDAO salesDao = new SalesDAO();
			ProductDAO productDao = new ProductDAO();

			for (CartItem item : cart) {
				// CartItemから必要なデータを取得して売上登録
				salesDao.insertSale(item.getProductId(), item.getName(), item.getPrice(), item.getImagePath(), buyerMemberId);

				// 商品を売り切れ状態にする
				productDao.markAsSoldOut(item.getProductId());
			}

			// カートを空にする
			session.removeAttribute("cart");

			// 完了画面に遷移
			response.sendRedirect("views/productPurchaseComplete.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "購入処理に失敗しました。");
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}
	}
}
