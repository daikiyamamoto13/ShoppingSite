<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>お気に入り追加完了</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

    <div class="card shadow p-5 text-center" style="max-width: 500px; width: 100%;">
        <h2 class="text-success display-6 mb-4">お気に入りに追加しました</h2>

        <!-- 商品詳細に戻る -->
        <form action="${pageContext.request.contextPath}/ProductDetailServlet" method="get" class="mb-3">
            <input type="hidden" name="productId" value="${param.productId}">
            <button type="submit" class="btn btn-outline-primary btn-lg w-100">🔙 商品詳細に戻る</button>
        </form>

        <!-- お気に入り一覧に進む -->
        <form action="${pageContext.request.contextPath}/FavoriteListServlet" method="get">
            <button type="submit" class="btn btn-outline-danger btn-lg w-100">♥ お気に入り一覧を見る</button>
        </form>
    </div>

</body>
</html>
