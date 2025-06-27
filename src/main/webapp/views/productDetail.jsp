<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	body {
		background-color: #f8f9fa;
	}
</style>
</head>
<body class="d-flex justify-content-center align-items-center vh-100">

<div class="card shadow-lg" style="max-width: 1000px; width: 100%;">
	<div class="row g-0">
		<div class="col-md-5 d-flex align-items-center justify-content-center p-4">
			<img src="${pageContext.request.contextPath}/${product.imagePath}"
				 alt="${product.name}" class="img-fluid rounded"
				 style="object-fit: contain; max-height: 400px;">
		</div>
		<div class="col-md-7 p-5">
			<h2 class="card-title mb-4">${product.name}</h2>

			<p class="fs-4 mb-2">💰 価格: <strong>${product.price}円</strong></p>
			<p class="mb-3 lead">📝 説明: ${product.description}</p>
			<p class="mb-2">📂 カテゴリー: ${product.categoryName}</p>
			<p class="mb-2">📦 商品状態: ${product.productConditionName}</p>
			<p class="mb-4">
				🕒 出品日時: 
				<fmt:formatDate value="${product.createdAt}" pattern="yyyy/MM/dd HH:mm" />
			</p>

			<!-- カート状態 -->
			<c:choose>
				<c:when test="${product.soldOut}">
					<p class="text-danger fw-bold fs-5">※この商品は売り切れました</p>
				</c:when>
				<c:when test="${alreadyInCart}">
					<p class="text-success fw-bold fs-5">※すでにカートに入っています</p>
				</c:when>
				<c:otherwise>
					<form action="${pageContext.request.contextPath}/CartAddLoginCheckServlet" method="post" class="mb-3">
						<input type="hidden" name="productId" value="${product.productId}">
						<input type="hidden" name="name" value="${product.name}">
						<input type="hidden" name="price" value="${product.price}">
						<input type="hidden" name="imagePath" value="${product.imagePath}">
						<button type="submit" class="btn btn-success btn-lg w-100">🛒 カートに入れる</button>
					</form>
				</c:otherwise>
			</c:choose>

			<!-- お気に入りボタン -->
			<c:choose>
				<c:when test="${alreadyFavorited}">
					<button class="btn btn-secondary btn-lg w-100 mb-3" disabled>♥ 登録済み</button>
				</c:when>
				<c:otherwise>
					<form action="${pageContext.request.contextPath}/FavoriteAddLoginCheckServlet" method="get" class="mb-3">
						<input type="hidden" name="productId" value="${product.productId}">
						<button type="submit" class="btn btn-outline-danger btn-lg w-100">♥ お気に入り追加</button>
					</form>
				</c:otherwise>
			</c:choose>

			<div class="text-center">
				<a href="${pageContext.request.contextPath}/ProductListServlet" class="btn btn-outline-secondary btn-lg">← 商品一覧に戻る</a>
			</div>
		</div>
	</div>
</div>

</body>
</html>
