<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="jp.co.aforce.beans.UserBean"%>

<%
UserBean user = (UserBean) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login-success.css">


<title>ログイン完了</title>
</head>
<body>

	<div class="login-success-wrapper">
		<div class="login-success-card">

			<h2>
				ようこそ、<%=user.getFirstName() + user.getLastName()%>
				さん！
			</h2>
			<p>ログインに成功しました。</p>

			<!-- 編集フォーム画面へ遷移 -->
			<form action="userEdit.jsp" method="post">
				<input type="submit" class="login-btn edit-btn" value="会員情報変更">
			</form>

			<!-- ログアウト -->
			<form action="../LogoutServlet" method="post">
				<input type="submit" class="login-btn logout-btn" value="ログアウト">
			</form>

			<!-- アカウント削除 -->
			<form action="../userDeleteServlet" method="post"
				onsubmit="retun confirm('本当に退会しますか？')">
				<button type="submit" class="login-btn delete-btn">退会する</button>
		</div>
	</div>
</body>
</html>
