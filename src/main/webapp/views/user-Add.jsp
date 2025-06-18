<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>新規登録</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/user-Add.css">
</head>
<body>

  <div class="user-add-wrapper">
    <div class="user-add-card">
      <h2>新規登録</h2>

      <form action="ConfirmationServlet" method="post">
        <label for="memberId">会員ID：</label>
        <input type="text" name="memberId" id="memberId" required>

        <label for="password">パスワード：</label>
        <input type="password" name="password" id="password" required>

        <label for="lastName">名前（姓）：</label>
        <input type="text" name="lastName" id="lastName" required>

        <label for="firstName">名前（名）：</label>
        <input type="text" name="firstName" id="firstName" required>

        <label for="address">住所：</label>
        <input type="text" name="address" id="address" required>

        <label for="mailAddress">メールアドレス：</label>
        <input type="email" name="mailAddress" id="mailAddress" required>

        <button type="submit" class="user-add-btn">確認</button>
      </form>

      <div class="back-to-login">
        <a href="login-in.jsp">ログイン画面に戻る</a>
      </div>
    </div>
  </div>

</body>
</html>
