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


<form action = "..//SignUpServlet", method="post">

	<label for="memberId">会員ID：</label>
	<input type="text" name="memberId" id="memberId" required</input><br>
	
	<label for="password">パスワード:</label>
	<input type="password" name="password" id="password" required</input><br>
	
	<label for="lastName">名前（姓）:</label>
	<input for="text" name="lastName" id="lastName" required</input><br>
	
	<label for="firstname">名前（名）:</label>
	<input type="text" name="firstName" id="firstName" required</input><br>
	
	<label for="address">住所:</label>
	<input type="text" name="address" id="address" required</input><br>
	
	<label for="mailAddress">メールアドレス:</label>
	<input type="email" name="mailAddress" id="mailAddress" required</input><br>

</form>

<p>
	<!--確認画面へ-->
	<a href="user-addConfirmation.jsp">
	
		<button>確認</button>
	
	</a>
	
</p>

<p>
	<!--ログイン画面に戻る-->
	<a href="login-in.jsp">
	
		<button>ログイン画面に戻る</button>
	
	</a>
	
</p>

</body>
</html>