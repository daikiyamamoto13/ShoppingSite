package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.dao.ProductDAO;

/**
 * Servlet implementation class ManegeProductsServlet
 */
@WebServlet("/ManageProductsServlet")
public class ManageProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ProductDAO dao = new ProductDAO();
			List<ProductBean> products = dao.findAll();
			
			request.setAttribute("products", products);
			request.getRequestDispatcher("views/manageProducts.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "商品一覧の取得に失敗しました。");
			request.getRequestDispatcher("views/adminDashBoard.jsp").forward(request, response);
		}
	}
}