<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/edit-complete.css">

<title>変更完了</title>
</head>
<body>

	<div class="complete-wrapper">

		<h2>会員情報の更新しました</h2>
		<%
		jp.co.aforce.beans.UserBean editUser = (jp.co.aforce.beans.UserBean) session.getAttribute("editUser");
		%>

		<p>
			名前(姓):
			<%=editUser.getLastName()%></p>
		<p>
			名前(名):
			<%=editUser.getFirstName()%></p>
		<p>
			住所:
			<%=editUser.getAddress()%></p>
		<p>
			メールアドレス:
			<%=editUser.getMailAddress()%></p>

		<a href="views/user-menu.jsp">会員メニューに戻る</a>

	</div>

</body>
</html>