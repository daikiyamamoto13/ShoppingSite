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
<title>ログイン完了</title>
</head>
<body>

<h2>ようこそ、<%= user.getFirstName() + user.getLastName() %> さん！</h2>
<p>ログインに成功しました。</p>

<!-- 編集フォーム画面へ遷移 -->
<form action="userEdit.jsp" method="post">
	<input type="submit" value="情報の変更">
</form>

<!-- ログアウト -->
<form action="../LogoutServlet" method="post">
  <input type="submit" value="ログアウト">
</form>

</body>
</html>
