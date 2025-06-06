<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="jp.co.aforce.beans.UserBean"%>


<%
	UserBean user = (UserBean)session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録確認画面</title>
</head>
<body>

	<h2>会員登録確認</h2>

	<p>
		会員ID:<%=user.getMemberId()%></p>

	<p>
		パスワード:<%=user.getPassword()%></p>

	<p>
		名前(姓):<%=user.getLastName()%></p>

	<p>
		名前(名):<%=user.getFirstName()%></p>

	<p>
		住所:<%=user.getAddress()%></p>

	<p>
		メールアドレス:<%=user.getMailAddress()%></p>

	<form action="user-add-confirmation" method="post">

		<button type="submit">登録</button>

		</form>
</body>
</html>