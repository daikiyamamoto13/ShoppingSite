<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者ログイン</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body class="d-flex justify-content-center align-items-center vh-100">

    <div class="bg-white p-5 rounded shadow w-100" style="max-width: 400px;">
        <h2 class="mb-4 text-center">管理者ログイン</h2>

        <form action="../AdminLoginServlet" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">ユーザー名</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">パスワード</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">ログイン</button>
        </form>
    </div>

</body>
</html>
