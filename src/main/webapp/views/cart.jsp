<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="jp.co.aforce.beans.CartItem"%>


<%
List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
if (cart == null) {
	cart = new ArrayList<>();
}

int total = 0;
for (CartItem item : cart) {
	total += item.getPrice();
}
int fee = (int) (total * 0.10); // 手数料10%
int totalWithFee = total + fee;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート一覧</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
img.product-image {
	width: 100px;
	height: auto;
	object-fit: cover;
}

.total-row {
	font-size: 1.2rem;
	font-weight: bold;
}
</style>
</head>
<body class="container mt-5">

	<h2 class="mb-4 text-center">🛒 カート内容</h2>

	<%
	if (cart.isEmpty()) {
	%>
	<div class="alert alert-info text-center">カートに商品は入っていません。</div>
	<div class="text-center">
		<a href="${pageContext.request.contextPath}/ProductListServlet"
			class="btn btn-secondary">買い物を続ける</a>
	</div>
	<%
	} else {
	%>
	<div class="table-responsive">
		<table class="table table-bordered align-middle text-center">
			<thead class="table-light">
				<tr>
					<th>画像</th>
					<th>商品名</th>
					<th>価格</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (CartItem item : cart) {
				%>
				<tr>
					<td><img
						src="<%=request.getContextPath() + "/" + item.getImagePath()%>"
						class="product-image"></td>
					<td><%=item.getName()%></td>
					<td>￥<%=item.getPrice()%></td>
					<td><a
						href="${pageContext.request.contextPath}/CartRemoveServlet?productId=<%= item.getProductId() %>"
						class="btn btn-sm btn-danger">削除</a></td>
				</tr>
				<%
				}
				%>
				<tr>
					<td colspan="2" class="text-end">小計</td>
					<td colspan="2">￥<%=total%></td>
				</tr>
				<tr>
					<td colspan="2" class="text-end">手数料（10%）</td>
					<td colspan="2">￥<%=fee%></td>
				</tr>
				<tr class="total-row">
					<td colspan="2" class="text-end">合計金額</td>
					<td colspan="2">￥<%=totalWithFee%></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="d-flex justify-content-between mt-4">
		<a href="${pageContext.request.contextPath}/ProductListServlet"
			class="btn btn-secondary">🛍 買い物を続ける</a>
		<form action="${pageContext.request.contextPath}/CartClearServlet"
			method="get">
			<button type="submit" class="btn btn-danger">🧹 カートを空にする</button>
		</form>

		<form action="${pageContext.request.contextPath}/PurchaseServlet"
			method="post">
			<button type="submit" class="btn btn-primary">💳 購入する</button>
		</form>
	</div>
	<%
	}
	%>

</body>
</html>
