<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
jp.co.aforce.beans.UserBean user = (jp.co.aforce.beans.UserBean) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/userEdit.css">


<title>会員情報の更新</title>
</head>
<body>

	<div class="edit-wrapper">

		<h2>会員情報の更新</h2>
		<form action="../user-EditConfirmation" method="post">
			<label for="lastName">名前（姓）:</label> <input type="text"
				name="lastName" id="lastName"
				value="<%=user != null ? user.getLastName() : ""%>" required><br>

			<label for="firstName">名前（名）:</label> <input type="text"
				name="firstName" id="firstName"
				value="<%=user != null ? user.getFirstName() : ""%>" required><br>

			<label for="address">住所:</label> <input type="text" name="address"
				id="address" value="<%=user != null ? user.getAddress() : ""%>"
				required><br> <label for="mailAddress">メールアドレス:</label>
			<input type="email" name="mailAddress" id="mailAddress"
				value="<%=user != null ? user.getMailAddress() : ""%>" required><br>

			<p>
				<input type="submit" value="確認">
			</p>
		</form>
		<p>
			<a href="login-in.jsp"> ログイン画面に戻る </a>
		</p>

	</div>

</body>
</html>