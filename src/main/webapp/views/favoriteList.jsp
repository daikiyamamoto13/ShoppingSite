<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>お気に入り一覧</title>
<!-- Bootstrap CSS 読み込み -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

	<h2 class="mb-4">お気に入り商品一覧</h2>

	<div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
		<c:forEach var="product" items="${favorites}">
			<div class="col">
				<div class="card h-100 shadow-sm">
					<img
						src="${pageContext.request.contextPath}/images${product.imagePath}"
						class="card-img-top"
						alt="${product.name}"
						style="object-fit: contain; height: 200px; background-color: #f8f9fa;">
					
					<div class="card-body">
						<h5 class="card-title">${product.name}</h5>
						<p class="card-text">${product.price}円</p>
					</div>

					<div class="card-footer bg-transparent d-flex justify-content-between">
						<a href="ProductDetailServlet?productId=${product.productId}" class="btn btn-outline-primary btn-sm">
							商品詳細
						</a>

						<form action="${pageContext.request.contextPath}/FavoriteDeleteServlet"
							method="post" class="mb-0">
							<input type="hidden" name="productId" value="${product.productId}">
							<button type="submit" class="btn btn-danger btn-sm">削除</button>
						</form>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="mt-4">
		<a href="${pageContext.request.contextPath}/views/user-menu.jsp" class="btn btn-secondary">戻る</a>
	</div>

</body>
</html>
