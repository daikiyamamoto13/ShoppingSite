package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.dao.ProductDAO;

/**
 * Servlet implementation class ProductEditFormServlet
 */
@WebServlet("/ProductEditFormServlet")
public class ProductEditFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int productId = Integer.parseInt(request.getParameter("productId"));

			ProductDAO dao = new ProductDAO();
			ProductBean product = dao.getProductById(productId);

			request.setAttribute("product", product);
			request.getRequestDispatcher("views/ProductEdit.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "商品情報の取得に失敗しました。");
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}
	}
}