package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;

@WebServlet("/CartAddLoginCheckServlet")
public class CartAddLoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");

		try {
			// 商品情報をパラメータから取得
			String productIdStr = request.getParameter("productId");
			String name = request.getParameter("name");
			String priceStr = request.getParameter("price");
			String imagePath = request.getParameter("imagePath");

			int productId = Integer.parseInt(productIdStr);
			int price = Integer.parseInt(priceStr);

			// カートアイテムを作成してセッションに保存
			CartItem item = new CartItem();
			item.setProductId(productId);
			item.setName(name);
			item.setPrice(price);
			item.setImagePath(imagePath);

			session.setAttribute("cartItemToAdd", item);

			if (memberId != null) {
				// ログイン済 → カート追加処理へ
				response.sendRedirect("CartAddServlet");
			} else {
				// 未ログイン → ログイン後にCartAddServletにリダイレクトさせるために保存
				session.setAttribute("afterLoginFromCartAdd", "CartAddServlet");
				response.sendRedirect("views/login2.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("views/error.jsp");
		}
	}
}
