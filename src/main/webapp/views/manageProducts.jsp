<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="jp.co.aforce.beans.AdminBean"%>
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.ProductBean"%>

<%
AdminBean admin = (AdminBean) session.getAttribute("admin");
if (admin == null) {
	response.sendRedirect(request.getContextPath() + "/adminLogin.jsp");
	return;
}
String username = admin.getUsername();
List<ProductBean> products = (List<ProductBean>) request.getAttribute("products");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
	<h2 class="mb-4">商品管理</h2>

	<table class="table table-bordered">
		<thead class="table-light">
			<tr>
				<th>ID</th>
				<th>商品名</th>
				<th>価格</th>
				<th>画像</th>
				<th>販売状態(〇=販売中,×=売り切れ)</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<%
                if (products != null && !products.isEmpty()) {
                    for (ProductBean product : products) {
            %>
			<tr>
				<td><%= product.getProductId() %></td>
				<td><%= product.getName() %></td>
				<td><%= product.getPrice() %>円</td>
				<td><img src="<%= product.getImagePath() %>" alt="商品画像"
					width="80"></td>
				<td><%= product.isSoldOut() ? "×" : "〇" %></td>
				<td><a
					href="ProductDeleteServlet?productId=<%= product.getProductId() %>"
					class="btn btn-sm btn-danger">削除</a></td>
			</tr>
			<%
                    }
                } else {
            %>
			<tr>
				<td colspan="6">商品がありません</td>
			</tr>
			<%
                }
            %>
		</tbody>
	</table>

	<a href="views/adminDashBoard.jsp" class="btn btn-secondary">ダッシュボードに戻る</a>
</body>
</html>
