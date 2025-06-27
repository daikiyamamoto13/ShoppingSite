package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jp.co.aforce.beans.AdminBean;

public class AdminDAO {

	static DataSource ds;

	// DB接続を取得
	public Connection getConnection() throws Exception {
		if (ds == null) {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/shoppingsite_yamamoto");
		}
		return ds.getConnection();
	}

	// 管理者ログイン認証（ユーザー名とパスワード）
	public AdminBean login(String username, String password) throws Exception {
		AdminBean admin = null;

		String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

		try (Connection con = getConnection();
		     PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					admin = new AdminBean();
					admin.setAdminId(rs.getInt("admin_id"));
					admin.setUsername(rs.getString("username"));
					admin.setPassword(rs.getString("password"));
				}
			}
		}
		return admin;
	}
}
