package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jp.co.aforce.beans.ProductBean;

public class ProductDAO {

    static DataSource ds;

    public Connection getConnection() throws Exception {
        if (ds == null) {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/shoppingsite_yamamoto");
        }
        return ds.getConnection();
    }
    
    //ProductBeanに入っている商品の情報をitemsテーブルにINSERT
    
    public boolean insertItem(ProductBean item) throws Exception {
        Connection con = getConnection();

        String sql = "INSERT INTO items "
                   + "(name, description, price, category_id, product_condition, image_path, member_id) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, item.getName());
        ps.setString(2, item.getDescription());
        ps.setInt(3, item.getPrice());
        ps.setInt(4, item.getCategory_id());
        ps.setInt(5, item.getProduct_condition());
        ps.setString(6, item.getImage_path());
        ps.setInt(7, item.getMember_id());

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result > 0;
    }
    
    //商品情報データベースから取得して一覧リストにまとめる処理
    
    public  List<ProductBean> findAll() throws Exception {
        List<ProductBean> itemList = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT product_id, name, price, image_path FROM items ORDER BY created_at DESC");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductBean item = new ProductBean();
                item.setProduct_id(rs.getInt("product_id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getInt("price"));
                item.setImage_path(rs.getString("image_path"));
                itemList.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // エラーが起きた場合は空のリストを返す
        }

        return itemList;
    }

}



	
	




