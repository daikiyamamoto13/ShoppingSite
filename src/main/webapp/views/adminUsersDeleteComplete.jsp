<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="jp.co.aforce.beans.AdminBean" %>
<%
    AdminBean admin = (AdminBean) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/views/adminLogin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ユーザー削除完了</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

    <div class="card shadow p-5 text-center" style="max-width: 500px; width: 100%;">
        <h2 class="text-success mb-4">ユーザーの削除が完了しました</h2>
        <a href="<%= request.getContextPath() %>/ManageUsersServlet" class="btn btn-primary btn-lg">
            ユーザー一覧に戻る
        </a>
    </div>

</body>
