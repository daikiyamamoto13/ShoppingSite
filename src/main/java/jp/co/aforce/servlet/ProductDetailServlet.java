package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List; // ← 追加！

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.beans.UserBean;
import jp.co.aforce.dao.FavoriteDAO;
import jp.co.aforce.dao.ProductDAO;

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("productId");

		try {
			int productId = Integer.parseInt(idParam);
			ProductDAO productDAO = new ProductDAO();
			ProductBean product = productDAO.findById(productId);

			if (product != null) {
				request.setAttribute("product", product);

				// ログインユーザーとセッション取得
				HttpSession session = request.getSession(false);
				boolean alreadyFavorited = false;
				boolean alreadyInCart = false;

				if (session != null) {
					UserBean user = (UserBean) session.getAttribute("user");
					if (user != null) {
						String memberId = user.getMemberId();
						FavoriteDAO favoriteDAO = new FavoriteDAO();
						alreadyFavorited = favoriteDAO.alreadyFavorited(memberId, productId);
					}

					// カートにすでに入っているかチェック
					List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
					if (cart != null) {
						for (CartItem item : cart) {
							if (item.getProductId() == productId) {
								alreadyInCart = true;
								break;
							}
						}
					}
				}

				request.setAttribute("alreadyFavorited", alreadyFavorited);
				request.setAttribute("alreadyInCart", alreadyInCart); 
				request.getRequestDispatcher("/views/productDetail.jsp").forward(request, response);
			} else {
				request.setAttribute("errorMessage", "指定された商品が見つかりません");
				request.getRequestDispatcher("/views/error.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "不正な商品IDです");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "商品詳細の取得中にエラーが発生しました");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
	}
}
