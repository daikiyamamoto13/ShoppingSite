package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SalesDAO {
	
	static DataSource ds;
	
	public Connection getConnection() throws Exception {
		if (ds == null) {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/shoppingsite_yamamoto");
		}
		return ds.getConnection();
	}

	public void insertSale(int productId, String name, int price, String imagePath, String buyerMemberId) throws Exception {
		try (Connection con = getConnection();
		     PreparedStatement st = con.prepareStatement(
		         "INSERT INTO sales (product_id, price, sold_at,member_id) VALUES (?, ?, ?, ?)")) {

			st.setInt(1, productId);
			st.setInt(2, price);
			st.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			st.setString(4, buyerMemberId);

			st.executeUpdate();
		}
	}
}
