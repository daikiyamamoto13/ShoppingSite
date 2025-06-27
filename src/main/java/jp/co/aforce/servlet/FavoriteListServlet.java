package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.dao.FavoriteDAO;

/**
 * Servlet implementation class FavoriteListServlet
 */
@WebServlet("/FavoriteListServlet")
public class FavoriteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String memberId = (String) session.getAttribute("memberId");

		if (memberId == null) {
			response.sendRedirect("views/login2.jsp");
			return;
		}

		try {
			FavoriteDAO dao = new FavoriteDAO();
			List<ProductBean> favorites = dao.getFavoritesByMemberId(memberId);
			request.setAttribute("favorites", favorites);
			request.getRequestDispatcher("/views/favoriteList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("views/error.jsp");
		}
	}

}
