<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jp.co.aforce.beans.UserBean" %>

<%
    String user = (String) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン完了</title>
</head>
<body>

    <h2>ようこそ、<%= user %> さん！</h2>
    <p>ログインに成功しました。</p>
    
	<form action="../LogoutServlet" method="post">
	
	<input type="submit" value="ログアウト">
	    
    

    </form>
</body>
</html>
