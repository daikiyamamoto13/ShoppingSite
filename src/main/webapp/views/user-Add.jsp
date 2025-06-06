<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>

<h2>新規登録ページ</h2>


<form action = "ConfirmationServlet" method="post">

	<label for="memberId">会員ID：</label>
	<input type="text" name="memberId" id="memberId" required><br>
	
	<label for="password">パスワード:</label>
	<input type="password" name="password" id="password" required><br>
	
	<label for="lastName">名前（姓）:</label>
	<input for="text" name="lastName" id="lastName" required><br>
	
	<label for="firstname">名前（名）:</label>
	<input type="text" name="firstName" id="firstName" required><br>
	
	<label for="address">住所:</label>
	<input type="text" name="address" id="address" required><br>
	
	<label for="mailAddress">メールアドレス:</label>
	<input type="email" name="mailAddress" id="mailAddress" required><br>



<p>
	<!--確認画面へ-->
	
	
	<input type="submit" value="確認">
	
	
</p>

</form>
<p>
	<!--ログイン画面に戻る-->
	<a href="login-in.jsp">
	
		<button>ログイン画面に戻る</button>
	
	</a>
	
</p>

</body>
</html>