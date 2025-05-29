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
	
	static DataSource ds ;
	
	public Connection getConnection() throws Exception {
		
		if (ds==null) {
			InitialContext ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/jdbc/shoppingsite_yamamoto");
		}
		return ds.getConnection();	}
	
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
			
			UserBean user = new UserBean(memberId,password,lastName,firstName,address,mailAddress);
			users.add(user);
		}

		st.close();
		con.close();
		return users;
	}

    public String findUser(String memberId, String password) throws Exception {

    	String user = null;
    	
         Connection con = getConnection();
            String sql = "SELECT CONCAT(LAST_NAME, FIRST_NAME) AS full_name FROM users WHERE MEMBER_ID = ? AND PASSWORD = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, memberId);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            
            
            if (rs.next()) {
                
            user = rs.getString("full_name");	
    		
            }
        

        return user;
    }
    
     
}
