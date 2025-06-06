<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>

<h2>ログイン</h2>

<form action="../LoginServlet" method="post">
	
	<label for="id">ID：</label>
	<input type="text" name="memberId" id="memberId"><br>
	
	<label for="password">パスワード：</label>
	<input type="password" name="password" id="password"><br>

	<button>ログイン</button> 

</form>

<p>
	<a href="user-Add.jsp">
		<button >新規会員登録</button>
	</a>
</p>

</body>
</html>