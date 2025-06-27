<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カートに追加完了</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

  <div class="card shadow p-5 text-center" style="max-width: 500px; width: 100%;">
    <h2 class="text-success display-6 mb-4">カートに追加しました</h2>

    <div class="d-grid gap-3">
      <a href="${pageContext.request.contextPath}/ProductListServlet" class="btn btn-outline-secondary btn-lg">🛍 買い物を続ける</a>
      <a href="${pageContext.request.contextPath}/views/cart.jsp" class="btn btn-primary btn-lg">🛒 カートに進む</a>
    </div>
  </div>

</body>
</html>
