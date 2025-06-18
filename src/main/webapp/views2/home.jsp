<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="jp.co.aforce.beans.ProductBean" %>
<%@ page import="jp.co.aforce.dao.ProductDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>



<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Home - 商品一覧</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .category-btn { margin: 0 5px; }
    .product-card { width: 100%; max-width: 300px; }
  </style>
</head>
<body class="container mt-4">

  <!-- ヘッダー -->
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2>ショップロゴ</h2>
    <div class="d-flex flex-wrap gap-2">
      <a href="home.jsp?category=100" class="btn btn-outline-primary">アート</a>
      <a href="home.jsp?category=200" class="btn btn-outline-primary">フィギュア</a>
      <a href="home.jsp?category=300" class="btn btn-outline-primary">トレーディングカード</a>
      <a href="home.jsp?category=400" class="btn btn-outline-primary">ホビー</a>
      <a href="home.jsp?category=500" class="btn btn-outline-primary">スニーカー</a>
      <a href="home.jsp?category=600" class="btn btn-outline-primary">電子機器</a>
    </div>
    <form class="d-flex" role="search">
      <input class="form-control me-2" type="search" placeholder="検索">
      <button class="btn btn-outline-success" type="submit">検索</button>
    </form>
  </div>

  <!-- 商品一覧 -->
  <div class="row">
    <% if (itemList != null && !itemList.isEmpty()) { 
         for (ProductBean item : itemList) { %>
      <div class="col-md-4 mb-4 d-flex justify-content-center">
        <div class="card product-card h-100">
          <img src="<%= item.getImage_path() %>" class="card-img-top" alt="商品画像">
          <div class="card-body">
            <h5 class="card-title"><%= item.getName() %></h5>
            <p class="card-text">￥<%= item.getPrice() %></p>
            <a href="product-detail.jsp?id=<%= item.getProduct_id() %>" class="btn btn-primary">詳細</a>
          </div>
        </div>
      </div>
    <% } 
       } else { %>
      <p>現在、出品されている商品はありません。</p>
    <% } %>
  </div>

</body>
</html>
