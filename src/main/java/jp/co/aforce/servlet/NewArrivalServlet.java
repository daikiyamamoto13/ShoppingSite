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
 * Servlet implementation class NewArrivalServlet
 */
@WebServlet("/NewArrivalServlet")
public class NewArrivalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO dao = new ProductDAO();
        List<ProductBean> newestItems = dao.findNewest(10); // 最新10件を取得

        request.setAttribute("itemList", newestItems);
        request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}



}
