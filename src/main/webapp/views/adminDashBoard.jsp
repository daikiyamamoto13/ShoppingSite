<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="jp.co.aforce.beans.AdminBean" %>

<%
    AdminBean admin = (AdminBean) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/views/adminLogin.jsp");
        return;
    }
    String username = admin.getUsername();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者ダッシュボード</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body class="d-flex justify-content-center align-items-center vh-100">

    <div class="bg-white p-5 rounded shadow text-center" style="max-width: 500px; width: 100%;">
        <h2 class="mb-4">管理者ダッシュボード</h2>

        <p>おかえりなさい、<strong><%= username %></strong> さん。</p>

        <div class="mt-4">
            <a href="../ManageProductsServlet" class="btn btn-primary me-2">商品管理</a>
            <a href="../ManageUsersServlet" class="btn btn-secondary">ユーザー管理</a>
        </div>
    </div>

</body>
</html>
