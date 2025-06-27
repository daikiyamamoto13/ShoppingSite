package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.dao.ProductDAO;

/**
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet("/ProductDeleteServlet")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request, response); // GETもPOSTも同じ処理を使う
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            int productId = Integer.parseInt(request.getParameter("productId"));

            ProductDAO dao = new ProductDAO();
            boolean deleted = dao.deleteProduct(productId);

            if (deleted) {
                response.sendRedirect("views/productDeleteComplete.jsp");
            } else {
                request.setAttribute("errorMessage", "商品削除に失敗しました。");
                request.getRequestDispatcher("views/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "システムエラーが発生しました。");
            request.getRequestDispatcher("views/error.jsp").forward(request, response);
        }
	}

}
