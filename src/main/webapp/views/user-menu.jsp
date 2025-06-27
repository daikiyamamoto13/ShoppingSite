<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="jp.co.aforce.beans.UserBean"%>

<%
  UserBean user = (UserBean) session.getAttribute("user");
  if (user == null) {
    response.sendRedirect("login-in.jsp"); // 未ログインならログインページへ
    return;
  }
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ログイン完了</title>
  <link rel="stylesheet" type="text/css"
        href="<%=request.getContextPath()%>/css/login-success.css">
</head>
<body>

  <div class="login-success-wrapper">
    <div class="login-success-card">

      <h2>
        ようこそ、<%= user.getFirstName() + user.getLastName() %> さん！
      </h2>

      <p>
        <a href="<%=request.getContextPath()%>/ProductListServlet">ホーム画面に戻る</a>
      </p>

      <!-- ユーザーが出品している商品画面 -->
      <form action="../MyPageServlet" method="get">
        <input type="submit" class="login-btn edit-btn" value="出品商品一覧">
      </form>

      <!-- お気に入り一覧画面 -->
      <form action="../FavoriteListServlet" method="get">
        <input type="submit" class="login-btn edit-btn" value="お気に入り一覧">
      </form>

      <!-- 編集フォーム画面 -->
      <form action="userEdit.jsp" method="post">
        <input type="submit" class="login-btn btn-success" value="会員情報変更">
      </form>

      <!-- ログアウト -->
      <form action="../LogoutServlet" method="post">
        <input type="submit" class="login-btn logout-btn" value="ログアウト">
      </form>

      <!-- アカウント削除 -->
      <form action="../userDeleteServlet" method="post"
            onsubmit="return confirm('本当に退会しますか？')">
        <button type="submit" class="login-btn delete-btn">退会する</button>
      </form>

    </div>
  </div>

</body>
</html>
