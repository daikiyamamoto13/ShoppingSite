<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/login.css">
</head>
<body>
	<div class="login-wrapper">
		<div class="login-card">
			<h2>ログイン後に使える機能です<br>ログインしてください</h2>
			
			<form action="../LoginServlet" method="post">
				<label for="id">ID：</label> <input type="text" id="id" name="memberId">

				<label for="password">パスワード：</label> <input type="password"
					id="password" name="password">

				<button type="submit">ログイン</button>


				<div class="register-link">
					<a href="user-Add.jsp">新規会員登録はこちら</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
