<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.aforce.beans.AdminBean"%>
<%@ page import="jp.co.aforce.beans.UserBean" %>

<%
    AdminBean admin = (AdminBean) session.getAttribute("admin");
    UserBean user = (UserBean) session.getAttribute("user");

    // 戻り先リンクを設定
    String linkBack = (admin != null)
        ? request.getContextPath() + "/ManageProductsServlet"
        : request.getContextPath() + "/MyPageServlet";

    String linkHome = (admin != null)
        ? request.getContextPath() + "/views/adminDashBoard.jsp"
        : request.getContextPath() + "/ProductListServlet";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>削除完了</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body class="d-flex justify-content-center align-items-center vh-100">

    <div class="bg-white p-5 rounded shadow text-center" style="max-width: 500px; width: 100%;">
        <h2 class="mb-4 text-success">✅ 削除が完了しました</h2>

        <div class="d-grid gap-3">
            <a href="<%= linkBack %>" class="btn btn-outline-primary">他の商品も削除する</a>
            <a href="<%= linkHome %>" class="btn btn-secondary">ホーム画面に戻る</a>
        </div>
    </div>

</body>
</html>