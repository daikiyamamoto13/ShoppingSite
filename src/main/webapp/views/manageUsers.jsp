<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="jp.co.aforce.beans.UserBean" %>
<%@ page import="jp.co.aforce.beans.AdminBean" %>

<%
    // セッションから管理者情報を取得
    AdminBean admin = (AdminBean) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/adminLogin.jsp");
        return;
    }

    List<UserBean> userList = (List<UserBean>) request.getAttribute("userList");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ユーザー管理</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

    <h2 class="mb-4">ユーザー管理</h2>

    <table class="table table-bordered">
        <thead class="table-light">
            <tr>
                <th>ID</th>
                <th>姓</th>
                <th>名</th>
                <th>住所</th>
                <th>メールアドレス</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (userList != null && !userList.isEmpty()) {
                    for (UserBean user : userList) {
            %>
            <tr>
                <td><%= user.getMemberId() %></td>
                <td><%= user.getLastName() %></td>
                <td><%= user.getFirstName() %></td>
                <td><%= user.getAddress() %></td>
                <td><%= user.getMailAddress() %></td>
                <td>
                    <a href="AdminUsersDeleteServlet?memberId=<%= user.getMemberId() %>" class="btn btn-sm btn-danger">削除</a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr><td colspan="6">ユーザーが存在しません。</td></tr>
            <%
                }
            %>
        </tbody>
    </table>

    <a href="views/adminDashBoard.jsp" class="btn btn-secondary">ダッシュボードに戻る</a>

</body>
</html>
