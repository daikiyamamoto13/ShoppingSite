package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.UserBean;

@WebServlet("/user-EditConfirmation")
public class EditConfirmationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// セッション取得
    	HttpSession session = request.getSession();
    	UserBean user = (UserBean) session.getAttribute("user");

    	// フォームから値を受け取る
    	String lastName = request.getParameter("lastName");     
    	String firstName = request.getParameter("firstName");
    	String address = request.getParameter("address");
    	String mailAddress = request.getParameter("mailAddress");


    	// 変更用インスタンス作成
    	UserBean editUser = new UserBean();
    	editUser.setMemberId(user.getMemberId());
    	editUser.setPassword(user.getPassword());
    	editUser.setLastName(lastName != null ? lastName : user.getLastName());
    	editUser.setFirstName(firstName != null ? firstName : user.getFirstName());
    	editUser.setAddress(address != null ? address : user.getAddress());
    	editUser.setMailAddress(mailAddress != null ? mailAddress : user.getMailAddress());

    	// セッションに保存
    	session.setAttribute("editUser", editUser);

    	// 確認画面へ遷移
    	request.getRequestDispatcher("views/user-EditConfirmation.jsp").forward(request, response);
    }
}
