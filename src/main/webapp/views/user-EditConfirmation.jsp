<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
jp.co.aforce.beans.UserBean editUser = (jp.co.aforce.beans.UserBean) session.getAttribute("editUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報変更内容</title>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/user-EditConfirmation.css">

</head>
<body>

	<div class="confirm-wrapper">

		<h2>こちらの内容に変更します</h2>
		<p>
			名前(姓):
			<%=request.getParameter("lastName")%></p>
		<p>
			名前(名):
			<%=request.getParameter("firstName")%></p>
		<p>
			住所:
			<%=request.getParameter("address")%></p>
		<p>
			メールアドレス:
			<%=request.getParameter("mailAddress")%></p>


		<form action="userEdit" method="post">
			<button type="submit">登録</button>
		</form>

	</div>
</body>
</html>