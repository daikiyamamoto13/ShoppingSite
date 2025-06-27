package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.UserBean;
import jp.co.aforce.dao.UserDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");

		UserDAO dao = new UserDAO();

		try {
			UserBean user = dao.findUser(memberId, password);

			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setAttribute("memberId", user.getMemberId());

				// カートに一時保存されていた商品がある場合 → カート追加へ
				if (session.getAttribute("cartItemToAdd") != null) {
					response.sendRedirect("CartAddServlet");
					return;
				}

				// ログイン後の遷移先チェック
				String redirect;

				if ((redirect = (String) session.getAttribute("afterLoginFromProductAdd")) != null) {
					session.removeAttribute("afterLoginFromProductAdd");
					response.sendRedirect(redirect);
					return;
				} else if ((redirect = (String) session.getAttribute("afterLoginFromCartAdd")) != null) {
					session.removeAttribute("afterLoginFromCartAdd");
					response.sendRedirect(redirect);
					return;
				} else if ((redirect = (String) session.getAttribute("afterLoginFromMyPage")) != null) {
					session.removeAttribute("afterLoginFromMyPage");
					response.sendRedirect(redirect);
					return;
				} else if ((redirect = (String) session.getAttribute("afterLoginFromFavoriteAdd")) != null) {
					session.removeAttribute("afterLoginFromFavoriteAdd");
					session.removeAttribute("favoriteProductId");
					response.sendRedirect(redirect);
					return;
				}

				// 上記のいずれにも該当しない場合 → 通常のメニューへ
				response.sendRedirect("views/user-menu.jsp");
			} else {
				// ユーザー認証失敗
				response.sendRedirect("views/login-Error.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("views/error.jsp");
		}
	}
}
