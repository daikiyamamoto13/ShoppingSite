<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>登録エラー</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/user-Added.css">
</head>
<body>

  <div class="error-wrapper">
    <div class="error-card">
      <h2>既に登録されている情報です</h2>

      <a href="login-in.jsp" class="error-btn">ログイン画面に戻る</a>
      <a href="user-Add.jsp" class="error-btn">新規登録画面に戻る</a>
    </div>
  </div>

</body>
</html>
