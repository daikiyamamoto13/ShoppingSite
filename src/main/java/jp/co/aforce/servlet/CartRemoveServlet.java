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

/**
 * Servlet implementation class CartRemoveServlet
 */
@WebServlet("/CartRemoveServlet")
public class CartRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String productIdStr = request.getParameter("productId");
		
		try {
			int productId = Integer.parseInt(productIdStr);
			
			@SuppressWarnings("unchecked")
			List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
			if (cart != null) {
				cart.removeIf(p -> p.getProductId() == productId);
			}
			
			//セッション更新
			session.setAttribute("cart", cart);
			response.sendRedirect("views/cart.jsp");
			
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("views/error.jsp");
		}
	}



}
