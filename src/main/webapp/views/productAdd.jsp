<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品出品</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="vh-100 d-flex justify-content-center align-items-center">
	
	
	
	<div class="container" style="max-width: 600px;">
		<h2 class="text-center border-bottom pb-2 mb-4 fw-bold">商品を出品する</h2>
		
		<form action="<%= request.getContextPath() %>/ProductAddServlet" method="post"
			enctype="multipart/form-data">
			
			<div class="mb-3">
				<label for="title" class="form-label">商品名：</label> <input
					type="text" class="form-control" id="name" name="name" required>
			</div>
			<div class="mb-3">
				<label for="description" class="form-label">説明：</label>
				<textarea class="form-control" id="description" name="description"
					rows="3"></textarea>
			</div>
			<div class="mb-3">
				<label for="condition" class="form-label">商品状態：</label> <select
					class="form-select" id="conditionId" name="conditionId" required>
					<option value="">選択してください</option>
					<option value="1">新品</option>
					<option value="2">未使用に近い</option>
					<option value="3">目立った傷や汚れなし</option>
					<option value="4">やや傷や汚れあり</option>
					<option value="5">傷や汚れあり</option>
					<option value="6">全体的に状態が悪い</option>
				</select>
			</div>

			<div class="mb-3">
				<label for="category" class="form-label">カテゴリー：</label> <select
					class="form-select" id="categoryId" name="categoryId" required>
					<option value="">選択してください</option>
					<option value="100">アート</option>
					<option value="200">フィギュア</option>
					<option value="300">トレーディングカード</option>
					<option value="400">ホビー</option>
					<option value="500">スニーカー</option>
					<option value="600">電子機器</option>
				</select>
			</div>

			<div class="mb-3">
				<label for="price" class="form-label">価格：</label> <input
					type="number" class="form-control" id="price" name="price" required>
			</div>
			<div class="mb-3">
				<label for="image" class="form-label">商品画像：</label> <input type="file"
					class="form-control" id="imagePath" name="imagePath" required>
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-primary">出品</button>
			</div>
		</form>
	</div>

</body>
</html>
