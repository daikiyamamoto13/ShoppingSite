package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jp.co.aforce.beans.UserBean;

public class UserDAO {

	static DataSource ds;

	public Connection getConnection() throws Exception {

		if (ds == null) {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/shoppingsite_yamamoto");
		}
		return ds.getConnection();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserBean> getAllusers() throws Exception {
		List<UserBean> users = new ArrayList<>();
		Connection con = getConnection();
		String sql = "SELECT * from users ";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			String memberId = rs.getString("MEMBER_ID");
			String password = rs.getString("PASSWORD");
			String lastName = rs.getString("LAST_NAME");
			String firstName = rs.getString("FIRST_NAME");
			String address = rs.getString("ADDRESS");
			String mailAddress = rs.getString("MAIL_ADDRESS");

			UserBean user = new UserBean(memberId, password, lastName, firstName, address, mailAddress);
			users.add(user);
		}

		st.close();
		con.close();
		return users;
	}

	/**
	 * 
	 * @param memberId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public UserBean findUser(String memberId, String password) throws Exception {
	    UserBean user = null;

	    Connection con = getConnection();
	    String sql = "SELECT * FROM users WHERE MEMBER_ID = ? AND PASSWORD = ?";
	    PreparedStatement stmt = con.prepareStatement(sql);
	    stmt.setString(1, memberId);
	    stmt.setString(2, password);

	    ResultSet rs = stmt.executeQuery();

	    if (rs.next()) {
	        user = new UserBean();
	        user.setMemberId(rs.getString("MEMBER_ID"));
	        user.setPassword(rs.getString("PASSWORD"));
	        user.setLastName(rs.getString("LAST_NAME"));
	        user.setFirstName(rs.getString("FIRST_NAME"));
	        user.setAddress(rs.getString("ADDRESS"));
	        user.setMailAddress(rs.getString("MAIL_ADDRESS"));
	    }

	    rs.close();
	    stmt.close();
	    con.close();

	    return user;
	}

	
	public boolean insertUser(UserBean user) throws Exception {
		Connection con = getConnection();
		
		String sql = "INSERT INTO users(MEMBER_ID,PASSWORD,LAST_NAME,FIRST_NAME,ADDRESS,MAIL_ADDRESS) VALUES (?,?,?,?,?,?)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, user.getMemberId());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getLastName());
		stmt.setString(4, user.getFirstName());
		stmt.setString(5, user.getAddress());
		stmt.setString(6, user.getMailAddress());
		
		int result = stmt.executeUpdate();
		
		stmt.close();
		con.close();
		
		return result > 0;
		
	}
	
	public boolean updateUser(UserBean user) throws Exception {
	    Connection con = null;
	    PreparedStatement stmt = null;

	    try {
	        con = getConnection();

	        String sql = "UPDATE users SET LAST_NAME=?, FIRST_NAME=?, ADDRESS=?, MAIL_ADDRESS=? WHERE MEMBER_ID=?";

	        stmt = con.prepareStatement(sql);
	        
	        stmt.setString(1, user.getLastName());
	        stmt.setString(2, user.getFirstName());
	        stmt.setString(3, user.getAddress());
	        stmt.setString(4, user.getMailAddress());
	        stmt.setString(5, user.getMemberId());

	        int result = stmt.executeUpdate();
	        return result > 0;

	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	

}
