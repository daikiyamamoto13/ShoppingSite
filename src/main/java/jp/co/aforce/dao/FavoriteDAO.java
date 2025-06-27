package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jp.co.aforce.beans.ProductBean;


public class FavoriteDAO {

    static DataSource ds;

    public Connection getConnection() throws Exception {
        if (ds == null) {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/shoppingsite_yamamoto");
        }
        return ds.getConnection();
    }

    // お気に入り追加
    public boolean addFavorite(String memberId, int productId) throws Exception {
        String sql = "INSERT INTO favorites (member_id, product_id) VALUES (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, memberId);
            stmt.setInt(2, productId);

            return stmt.executeUpdate() > 0;
        }
    }

    // お気に入り削除
    public boolean removeFavorite(String memberId, int productId) throws Exception {
        String sql = "DELETE FROM favorites WHERE member_id = ? AND product_id = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, memberId);
            stmt.setInt(2, productId);

            return stmt.executeUpdate() > 0;
        }
    }

 // お気に入り商品一覧（Productのリスト）を取得
    public List<ProductBean> getFavoritesByMemberId(String memberId) throws Exception {
        List<ProductBean> favorites = new ArrayList<>();
        String sql = "SELECT i.product_id, i.name, i.price, i.image_path " +
                     "FROM favorites f " +
                     "JOIN items i ON f.product_id = i.product_id " +
                     "WHERE f.member_id = ? AND i.is_deleted = 0";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, memberId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProductBean product = new ProductBean();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setImagePath(rs.getString("image_path"));
                favorites.add(product);
            }
        }

        return favorites;
    }

    
    // ユーザーのお気に入り商品ID一覧を取得
    public List<Integer> getFavoriteProductIds(String memberId) throws Exception {
        List<Integer> favorites = new ArrayList<>();
        String sql = "SELECT product_id FROM favorites WHERE member_id = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, memberId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                favorites.add(rs.getInt("product_id"));
            }
        }
        return favorites;
    }

    // 指定の商品がユーザーのお気に入りかどうかを確認
    public boolean alreadyFavorited(String memberId, int productId) throws Exception {
        String sql = "SELECT 1 FROM favorites WHERE member_id = ? AND product_id = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, memberId);
            stmt.setInt(2, productId);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
}
