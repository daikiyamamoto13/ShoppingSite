<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入完了</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
  body {
    padding-top: 80px;
    text-align: center;
  }
</style>
</head>
<body class="container">

  <h2 class="text-success mb-4">✅ ご購入ありがとうございます！</h2>
  <p class="mb-5">ご注文が正常に完了しました。<br>商品の到着をお楽しみに！</p>

  <a href="${pageContext.request.contextPath}/ProductListServlet" class="btn btn-primary">
    ホームに戻る
  </a>

</body>
</html>
