<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出品完了</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	body {
		background-color: #f8f9fa;
	}
</style>
</head>
<body class="d-flex justify-content-center align-items-center vh-100">

	<div class="bg-white p-5 rounded shadow text-center" style="max-width: 400px; width: 100%;">
		<h2 class="mb-4 text-success">✅ 出品が完了しました！</h2>

		<div class="d-grid gap-3">
			<a href="${pageContext.request.contextPath}/views/productAdd.jsp" class="btn btn-outline-primary">続けて出品する</a>
			<a href="${pageContext.request.contextPath}/ProductListServlet" class="btn btn-secondary">ホーム画面に戻る</a>
		</div>
	</div>

</body>
</html>
