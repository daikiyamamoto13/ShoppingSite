package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCheckServlet
 */
@WebServlet("/LoginCheckServlet")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションからmemberIdを取得
		HttpSession session = request.getSession(false); // セッションがなければnullを返す
		if (session == null || session.getAttribute("memberId") == null) {
		    // ログインしていない場合の処理（例：ログイン画面にリダイレクト）
		    response.sendRedirect("login.jsp");
		    return;
		}
		 session.getAttribute("memberId");
	}

}
