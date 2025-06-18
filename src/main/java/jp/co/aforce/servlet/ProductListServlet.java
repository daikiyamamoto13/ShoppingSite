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
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    doPost(request, response); // GETでもPOSTと同じ処理をする
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                 try {
                    ProductDAO dao = new ProductDAO();

                    String categoryParam = request.getParameter("category");
                    List<ProductBean> itemList;
                    if (categoryParam == null || categoryParam.isEmpty()) {
                        itemList = dao.findAll();
                    } else {
                        int categoryId = Integer.parseInt(categoryParam);
                        itemList = dao.findByCategory(categoryId);
                    }

                    request.setAttribute("itemList", itemList);
                    request.getRequestDispatcher("/views2/home.jsp").forward(request, response);

	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "商品一覧の取得中にエラーが発生しました。");
	            request.getRequestDispatcher("/error.jsp").forward(request, response);
	        }
	    }
		
	}


