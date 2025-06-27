<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="jp.co.aforce.beans.ProductBean"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
// nullでも空リストで処理
List<ProductBean> itemList = (List<ProductBean>) request.getAttribute("itemList");
if (itemList == null) {
	itemList = new ArrayList<>();
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home - 商品一覧</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- jQuery-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Slick CSS -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />

<!-- Slick JS -->
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>


</head>
<body class="container mt-4">

	<!-- ヘッダー -->
	<div class="row align-items-center mb-3">
		<!-- ロゴ + カテゴリまとめて横並び -->
		<div class="col-md-8">
			<div class="d-flex align-items-center gap-3">

				<!-- ロゴ -->
				<h2 class="mb-0">
					<a href="<%=request.getContextPath()%>/ProductListServlet"
						class="text-decoration-none text-reset"> セカンドマーケット </a>
				</h2>

				
				<!-- 現在のcategoryIdパラメータを取得 -->
				<c:set var="selectedCategoryId" value="${param.categoryId}" />

				<!-- カテゴリボタン表示 -->
				<div class="d-flex gap-2 flex-wrap" style="margin-left: 5.5rem;">

					<!-- すべて -->
					<a href="${pageContext.request.contextPath}/ProductListServlet"
						class="btn btn-sm text-nowrap ${empty selectedCategoryId ? 'btn-primary' : 'btn-outline-secondary'}">
						すべて </a>

					<!-- アート -->
					<a
						href="${pageContext.request.contextPath}/ProductListServlet?categoryId=100&sort=${param.sort}"
						class="btn btn-sm text-nowrap ${selectedCategoryId == '100' ? 'btn-primary' : 'btn-outline-primary'}">
						アート </a>

					<!-- フィギュア -->
					<a
						href="${pageContext.request.contextPath}/ProductListServlet?categoryId=200&sort=${param.sort}"
						class="btn btn-sm text-nowrap ${selectedCategoryId == '200' ? 'btn-primary' : 'btn-outline-primary'}">
						フィギュア </a>

					<!-- トレーディングカード -->
					<a
						href="${pageContext.request.contextPath}/ProductListServlet?categoryId=300&sort=${param.sort}"
						class="btn btn-sm text-nowrap ${selectedCategoryId == '300' ? 'btn-primary' : 'btn-outline-primary'}">
						トレーディングカード </a>

					<!-- ホビー -->
					<a
						href="${pageContext.request.contextPath}/ProductListServlet?categoryId=400&sort=${param.sort}"
						class="btn btn-sm text-nowrap ${selectedCategoryId == '400' ? 'btn-primary' : 'btn-outline-primary'}">
						ホビー </a>

					<!-- スニーカー -->
					<a
						href="${pageContext.request.contextPath}/ProductListServlet?categoryId=500&sort=${param.sort}"
						class="btn btn-sm text-nowrap ${selectedCategoryId == '500' ? 'btn-primary' : 'btn-outline-primary'}">
						スニーカー </a>

					<!-- 電子機器 -->
					<a
						href="${pageContext.request.contextPath}/ProductListServlet?categoryId=600&sort=${param.sort}"
						class="btn btn-sm text-nowrap ${selectedCategoryId == '600' ? 'btn-primary' : 'btn-outline-primary'}">
						電子機器 </a>
				</div>

			</div>
		</div>

		<!-- 右カラム：検索・ログイン・出品・カート -->
		<div class="col-md-4">
			<div class="d-flex align-items-center gap-2">

				<!-- 検索フォーム -->
				<form action="ProductListServlet" method="get"
					class="d-flex flex-grow-1" role="search">
					<input type="hidden" name="categoryId" value="${param.categoryId}" />
					<!-- 追加 -->
					<div class="input-group input-group-sm w-100">
						<input type="text" name="query" class="form-control"
							placeholder="検索" aria-label="検索">
						<button class="btn btn-outline-secondary" type="submit">
							<i class="bi bi-search"></i>
						</button>
					</div>
				</form>



				<!-- マイページ -->
				<a href="${pageContext.request.contextPath}/MyPageLoginCheckServlet"
					class="btn btn-primary btn-sm text-nowrap">マイページ</a>

				<!-- 出品 -->
				<a
					href="${pageContext.request.contextPath}/ProductAddLoginCheckServlet"
					class="btn btn-warning btn-sm text-nowrap">出品する</a>

				<!-- カート -->
				<%
				List<ProductBean> cart = (List<ProductBean>) session.getAttribute("cart");
				int cartCount = (cart != null) ? cart.size() : 0;
				%>
				<a href="${pageContext.request.contextPath}/views/cart.jsp"
					class="btn btn-outline-dark btn-sm text-nowrap">カート (<%=cartCount%>)
				</a>

			</div>
		</div>

	</div>

	<!-- 新着商品スライダー -->

	<h3>新着商品</h3>
	<div class="slider">
		<c:forEach var="product" items="${newItems}">
			<div class="text-center px-2  d-flex flex-column align-items-center justify-content-center">
				<a href="ProductDetailServlet?productId=${product.productId}"> <img
					src="${product.imagePath}"
					alt="${product.name}" width="150" height="150"
					style="object-fit: contain;"><br> <span>${product.name}</span><br>
					<span>${product.price}円</span> <!-- ✅ 売り切れ表示 --> <c:if
						test="${product.soldOut}">
						<span class="badge bg-secondary">売り切れ</span>
					</c:if>
				</a>
			</div>
		</c:forEach>
	</div>


	<!-- 商品一覧 -->
	<h3>商品一覧</h3>

	<!-- 並び替え -->
	<form method="get" action="ProductListServlet">
		<input type="hidden" name="query" value="${param.query}" /> <input
			type="hidden" name="categoryId" value="${param.categoryId}" /> <select
			name="sort" onchange="this.form.submit()">
			<option value="">並び替え</option>
			<option value="asc" ${param.sort == 'asc' ? 'selected' : ''}>価格が安い順</option>
			<option value="desc" ${param.sort == 'desc' ? 'selected' : ''}>価格が高い順</option>
		</select> <label class="ms-3"> <input type="checkbox"
			name="availableOnly" value="true"
			${param.availableOnly == 'true' ? 'checked' : ''}
			onchange="this.form.submit()"> 販売中のみ
		</label>
	</form>


	<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4 mt-3">
		<c:forEach var="product" items="${itemList}">
			<div class="col">
				<div class="card h-100 position-relative">

					<!-- 画像 -->
					<img
						src="${product.imagePath}"
						class="card-img-top" alt="${product.name}"
						style="object-fit: contain; height: 150px; width: 100%; background-color: #f8f9fa;">

					<div class="card-body">
						<h5 class="card-title">${product.name}</h5>
						<p class="card-text">${product.price}円</p>

						<!-- ✅ 売り切れ表示 -->
						<c:if test="${product.soldOut}">
							<span class="badge bg-secondary">売り切れ</span>
						</c:if>

						<!-- カード全体をクリック可能にするリンク -->
						<a href="ProductDetailServlet?productId=${product.productId}"
							class="stretched-link"></a>
					</div>

				</div>
			</div>
		</c:forEach>
	</div>


	<script>
		$(document).ready(function() {
			$('.slider').slick({
				slidesToShow : 4,
				slidesToScroll : 1,
				autoplay : true,
				autoplaySpeed : 3000,
				arrows : true,
				dots : true
			});
		});
	</script>

</body>

</html>