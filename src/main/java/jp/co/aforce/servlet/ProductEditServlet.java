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
 * Servlet implementation class ProductEditServlet
 */
@WebServlet("/ProductEditServlet")
public class ProductEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            // リクエストパラメータ取得
            int productId = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int price = Integer.parseInt(request.getParameter("price"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int productCondition = Integer.parseInt(request.getParameter("productCondition"));
            String imagePath = request.getParameter("imagePath");

            // Beanにセット
            ProductBean product = new ProductBean();
            product.setProductId(productId);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategoryId(categoryId);
            product.setProductCondition(productCondition);
            product.setImagePath(imagePath);

            // DAO呼び出し
            ProductDAO dao = new ProductDAO();
            boolean success = dao.updateProduct(product);

            if (success) {
                response.sendRedirect("views/productEditComplete.jsp");
            } else {
                request.setAttribute("errorMessage", "商品情報の更新に失敗しました。");
                request.getRequestDispatcher("views/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "システムエラーが発生しました。");
            request.getRequestDispatcher("views/error.jsp").forward(request, response);
        }
    }

	}


