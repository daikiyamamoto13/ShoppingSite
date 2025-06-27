<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出品商品一覧</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light py-5">

<div class="container">
	<h3 class="text-center mb-4">あなたの出品商品</h3>

	<div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
		<c:forEach var="item" items="${myItems}">
			<div class="col">
				<div class="card h-100 shadow-sm">
					<img src="${pageContext.request.contextPath}/${item.imagePath}"
						 class="card-img-top" alt="${item.name}"
						 style="object-fit: contain; height: 200px; background-color: #f8f9fa;">

					<div class="card-body">
						<h5 class="card-title">${item.name}</h5>
						<p class="card-text">価格：${item.price}円</p>
					</div>

					<div class="card-footer d-flex justify-content-between bg-white border-0">
						<form action="ProductEditFormServlet" method="get" class="me-1">
							<input type="hidden" name="productId" value="${item.productId}" />
							<button type="submit" class="btn btn-outline-primary btn-sm w-100">編集</button>
						</form>

						<form action="ProductDeleteServlet" method="post"
							  onsubmit="return confirm('本当に削除しますか？');" class="ms-1">
							<input type="hidden" name="productId" value="${item.productId}" />
							<button type="submit" class="btn btn-outline-danger btn-sm w-100">削除</button>
						</form>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

</body>
</html>