<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ログインエラー</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login-Error.css">
</head>
<body>

  <div class="error-wrapper">
    <div class="error-card">
      <h2>ログインエラー</h2>
      <p>IDもしくはパスワードが違います</p>
      <a href="login-in.jsp" class="error-btn">ログイン画面へ戻る</a>
    </div>
  </div>

</body>
</html>
