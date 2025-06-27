<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報の更新</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

	<h2 class="mb-4">商品情報の更新</h2>

	<form action="ProductEditServlet" method="post">
		<input type="hidden" name="productId" value="${product.productId}">

		<div class="mb-3">
			<label for="name" class="form-label">商品名：</label>
			<input type="text" class="form-control" id="name" name="name" value="${product.name}" required>
		</div>

		<div class="mb-3">
			<label for="description" class="form-label">説明：</label>
			<textarea class="form-control" id="description" name="description" rows="3">${product.description}</textarea>
		</div>

		<div class="mb-3">
			<label for="price" class="form-label">価格：</label>
			<input type="number" class="form-control" id="price" name="price" value="${product.price}" required>
		</div>

		<div class="mb-3">
			<label for="categoryId" class="form-label">カテゴリ：</label>
			<select class="form-select" id="categoryId" name="categoryId" required>
				<option value="">選択してください</option>
				<option value="100" ${product.categoryId == 100 ? 'selected' : ''}>アート</option>
				<option value="200" ${product.categoryId == 200 ? 'selected' : ''}>フィギュア</option>
				<option value="300" ${product.categoryId == 300 ? 'selected' : ''}>トレーディングカード</option>
				<option value="400" ${product.categoryId == 400 ? 'selected' : ''}>ホビー</option>
				<option value="500" ${product.categoryId == 500 ? 'selected' : ''}>スニーカー</option>
				<option value="600" ${product.categoryId == 600 ? 'selected' : ''}>電子機器</option>
			</select>
		</div>

		<div class="mb-3">
			<label for="productCondition" class="form-label">商品状態：</label>
			<select class="form-select" id="productCondition" name="productCondition" required>
				<option value="">選択してください</option>
				<option value="1" ${product.productCondition == 1 ? 'selected' : ''}>新品</option>
				<option value="2" ${product.productCondition == 2 ? 'selected' : ''}>未使用に近い</option>
				<option value="3" ${product.productCondition == 3 ? 'selected' : ''}>目立った傷や汚れなし</option>
				<option value="4" ${product.productCondition == 4 ? 'selected' : ''}>やや傷や汚れあり</option>
				<option value="5" ${product.productCondition == 5 ? 'selected' : ''}>傷や汚れあり</option>
				<option value="6" ${product.productCondition == 6 ? 'selected' : ''}>全体的に状態が悪い</option>
			</select>
		</div>

		<div class="mb-3">
			<label for="imagePath" class="form-label">画像パス：</label>
			<input type="text" class="form-control" id="imagePath" name="imagePath" value="${product.imagePath}">
		</div>

		<button type="submit" class="btn btn-primary">更新</button>
	</form>

</body>
</html>
