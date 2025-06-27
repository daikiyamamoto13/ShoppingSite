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

/**
 * Servlet implementation class CartAddServlet
 */
@WebServlet("/CartAddServlet")
public class CartAddServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    try {
	        HttpSession session = request.getSession();

	        // セッションから CartItem を取得
	        CartItem item = (CartItem) session.getAttribute("cartItemToAdd");
	        if (item == null) {
	            // 商品情報がない → ホームへ
	            response.sendRedirect("views/home.jsp");
	            return;
	        }

	        // カート取得（なければ作成）
	        @SuppressWarnings("unchecked")
	        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
	        if (cart == null) {
	            cart = new ArrayList<>();
	        }

	        // 重複チェック
	        boolean alreadyInCart = false;
	        for (CartItem ci : cart) {
	            if (ci.getProductId() == item.getProductId()) {
	                alreadyInCart = true;
	                break;
	            }
	        }

	        if (!alreadyInCart) {
	            cart.add(item);
	        }

	        // カート保存 & セッションから一時的なitem削除
	        session.setAttribute("cart", cart);
	        session.removeAttribute("cartItemToAdd");

	        // 完了ページへ
	        response.sendRedirect("views/cart-AddComplete.jsp");

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("views/error.jsp");
	    }
	}
}