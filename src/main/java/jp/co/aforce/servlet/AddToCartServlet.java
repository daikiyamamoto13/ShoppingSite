package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 商品情報を受け取る）
		int productId = Integer.parseInt(request.getParameter("productId"));
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		String imagePath = request.getParameter("imagePath");

		// カートアイテムの作成
		CartItem item = new CartItem();
		item.setProductId(productId);
		item.setName(name);
		item.setPrice(price);
		item.setImagePath(imagePath);

		// セッションから取得（なければ新規作成）
		HttpSession session = request.getSession();
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}

		// カートに追加
		cart.add(item);
		session.setAttribute("cart", cart);

		// カートページへ
		response.sendRedirect(request.getContextPath() + "/views/cart.jsp");
		
		System.out.println("=== カート中身 ===");
		for (CartItem c : cart) {
		    System.out.println("ID: " + c.getProductId() + ", Name: " + c.getName() + ", Price: " + c.getPrice() + ", Image: " + c.getImagePath());
		}


	}
}
