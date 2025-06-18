<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/sessionError.css">

<title>セッションエラー</title>
</head>
<body>

	<div class="error-wrapper">

		<h1>セッション切れです、再度アクセスし直してください。</h1>

		<a href="login-in.jsp">ログイン画面に戻る</a>

	</div>

</body>
</html>