package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.dao.UserDAO;

@WebServlet("/AdminUsersDeleteServlet")
public class AdminUsersDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String memberId = request.getParameter("memberId");

        if (memberId == null || memberId.isEmpty()) {
            request.setAttribute("errorMessage", "削除対象のユーザーIDが指定されていません。");
            request.getRequestDispatcher("views/manageUsers.jsp").forward(request, response);
            return;
        }

        try {
            UserDAO dao = new UserDAO();
            boolean success = dao.deleteUser(memberId);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/views/adminUsersDeleteComplete.jsp");
            } else {
                request.setAttribute("errorMessage", "ユーザーの削除に失敗しました。");
                request.getRequestDispatcher("views/manageUsers.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "システムエラーが発生しました。");
            request.getRequestDispatcher("views/manageUsers.jsp").forward(request, response);
        }
    }
}
