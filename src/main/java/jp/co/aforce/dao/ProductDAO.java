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

	//ProductBeanに入っている商品の情報をitemsテーブルにINSERT(出品）

	public boolean insertItem(ProductBean item) throws Exception {
		Connection con = getConnection();

		String sql = "INSERT INTO items "
				+ "(member_id ,name, description, price,category_id,product_condition,image_path) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, item.getMemberId());
		ps.setString(2, item.getName());
		ps.setString(3, item.getDescription());
		ps.setInt(4, item.getPrice());
		ps.setInt(5, item.getCategoryId());
		ps.setInt(6, item.getProductCondition());
		ps.setString(7, item.getImagePath());


		int result = ps.executeUpdate();

		ps.close();
		con.close();

		return result > 0;
	}
	
	// memberIdから商品を取得する（ユーザーごとの出品商品）(削除された商品は表示しない)
	public List<ProductBean> getItemsByMemberId(String memberId) throws Exception {
	    Connection con = getConnection();
	    String sql = "SELECT * FROM items WHERE member_id = ? AND is_deleted = FALSE";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, memberId);
	    ResultSet rs = ps.executeQuery();

	    List<ProductBean> list = new ArrayList<>();

	    while (rs.next()) {
	        ProductBean item = new ProductBean();
	        item.setProductId(rs.getInt("product_id"));
	        item.setName(rs.getString("name"));
	        item.setDescription(rs.getString("description"));
	        item.setPrice(rs.getInt("price"));
	        item.setImagePath(rs.getString("image_path"));
	        item.setSoldOut(rs.getBoolean("is_sold_out"));
	        list.add(item);
	    }

	    rs.close();
	    ps.close();
	    con.close();

	    return list;
	}



	//商品情報データベースから取得して一覧リストにまとめる処理

	public List<ProductBean> findAll() throws Exception {
		List<ProductBean> itemList = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con
					.prepareStatement("SELECT product_id, name, price, image_path, is_sold_out "
							+ "FROM items "
							+ "WHERE is_deleted = FALSE "
							+ "ORDER BY created_at DESC"
							);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductBean item = new ProductBean();
				item.setProductId(rs.getInt("product_id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setImagePath(rs.getString("image_path"));
				item.setSoldOut(rs.getBoolean("is_sold_out"));
				itemList.add(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// エラーが起きた場合は空のリストを返す
		}

		return itemList;
	}

	// 指定されたカテゴリーの商品情報のみを取得
	public List<ProductBean> findByCategory(int categoryId) throws Exception {
		List<ProductBean> itemList = new ArrayList<>();

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"SELECT product_id, name, price, image_path,is_sold_out FROM items WHERE category_id = ? AND is_deleted = FALSE ORDER BY created_at DESC")) {

			ps.setInt(1, categoryId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ProductBean item = new ProductBean();
					item.setProductId(rs.getInt("product_id"));
					item.setName(rs.getString("name"));
					item.setPrice(rs.getInt("price"));
					item.setImagePath(rs.getString("image_path"));
					item.setSoldOut(rs.getBoolean("is_sold_out"));
					itemList.add(item);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			// エラーが起きた場合は空のリストを返す
		}

		return itemList;
	}
	// 商品詳細画面に必要な情報取得（カテゴリ名・状態名含む）
	public ProductBean findById(int productId) {
	    ProductBean product = null;

	    String sql = "SELECT i.*, c.category_name, pc.condition_name "
	               + "FROM items i "
	               + "JOIN category c ON i.category_id = c.category_id "
	               + "JOIN product_condition pc ON i.product_condition = pc.condition_id "
	               + "WHERE i.product_id = ? AND i.is_deleted = FALSE";

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, productId);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                product = new ProductBean();
	                product.setProductId(rs.getInt("product_id"));
	                product.setName(rs.getString("name"));
	                product.setPrice(rs.getInt("price"));
	                product.setImagePath(rs.getString("image_path"));
	                product.setDescription(rs.getString("description"));
	                product.setCreatedAt(rs.getTimestamp("created_at"));
	                product.setSoldOut(rs.getBoolean("is_sold_out"));
	                product.setCategoryId(rs.getInt("category_id"));
	                product.setProductCondition(rs.getInt("product_condition"));
	                product.setCategoryName(rs.getString("category_name"));
	                product.setProductConditionName(rs.getString("condition_name"));
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return product;
	}


	// 新着順で商品を取得
	public List<ProductBean> findNewest(int limit) {
		List<ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM items WHERE is_deleted = FALSE ORDER BY created_at DESC LIMIT ?";

		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, limit);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ProductBean product = new ProductBean();
					product.setProductId(rs.getInt("product_id"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getInt("price"));
					product.setImagePath(rs.getString("image_path"));
					product.setDescription(rs.getString("description"));
					product.setCreatedAt(rs.getTimestamp("created_at"));
					product.setSoldOut(rs.getBoolean("is_sold_out"));
					productList.add(product);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productList;
	}

	// カテゴリ別の新着商品（created_at順）
	public List<ProductBean> findNewestByCategory(int categoryId, int limit) throws Exception {
		List<ProductBean> productList = new ArrayList<>();
		String sql = "SELECT * FROM items WHERE category_id = ? AND is_deleted = FALSE ORDER BY created_at DESC LIMIT ?";

		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, categoryId);
			ps.setInt(2, limit);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ProductBean product = new ProductBean();
					product.setProductId(rs.getInt("product_id"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getInt("price"));
					product.setImagePath(rs.getString("image_path"));
					product.setDescription(rs.getString("description"));
					product.setCreatedAt(rs.getTimestamp("created_at"));
					product.setSoldOut(rs.getBoolean("is_sold_out"));
					productList.add(product);
				}
			}
		}
		return productList;
	}

	//価格ソート（昇順・降順）
	public List<ProductBean> findAllSorted(String sort) throws Exception {
		List<ProductBean> list = new ArrayList<>();

		String order = "ASC";
		if ("desc".equalsIgnoreCase(sort)) {
			order = "DESC";
		}

		String sql = "SELECT * FROM items WHERE is_deleted = FALSE ORDER BY price " + order;

		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ProductBean p = new ProductBean();
				// 各項目をset
				p.setProductId(rs.getInt("product_id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setImagePath(rs.getString("image_path"));
				p.setDescription(rs.getString("description"));
				p.setCreatedAt(rs.getTimestamp("created_at"));
				p.setSoldOut(rs.getBoolean("is_sold_out"));
				list.add(p);

			}
		}

		return list;
	}

	//カテゴリー別の価格ソート
	public List<ProductBean> findByCategorySorted(int categoryId, String sort) throws Exception {
		List<ProductBean> list = new ArrayList<>();
		String sql = "SELECT * FROM items WHERE category_id = ? AND is_deleted = FALSE ORDER BY price "
				+ ("desc".equalsIgnoreCase(sort) ? "DESC" : "ASC");

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, categoryId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ProductBean p = new ProductBean();
					p.setProductId(rs.getInt("product_id"));
					p.setName(rs.getString("name"));
					p.setPrice(rs.getInt("price"));
					p.setImagePath(rs.getString("image_path"));
					p.setDescription(rs.getString("description"));
					p.setCreatedAt(rs.getTimestamp("created_at"));
					p.setSoldOut(rs.getBoolean("is_sold_out"));
					list.add(p);
				}
			}
		}
		return list;
	}

	// キーワードによる検索 + 並び替え対応
	public List<ProductBean> searchByKeywordSorted(String keyword, String sort) throws Exception {
		List<ProductBean> list = new ArrayList<>();

		// SQLベース文（nameまたはdescriptionにキーワードが含まれる）
		String sql = "SELECT * FROM items WHERE is_deleted = FALSE AND( name LIKE ? OR description LIKE ?)";

		// 並び替えの追加
		if ("asc".equals(sort)) {
			sql += " ORDER BY price ASC";
		} else if ("desc".equals(sort)) {
			sql += " ORDER BY price DESC";
		} else {
			sql += " ORDER BY created_at DESC"; // デフォルト（新着順）
		}

		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			String like = "%" + keyword + "%";
			ps.setString(1, like);
			ps.setString(2, like); // description用

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ProductBean p = new ProductBean();
					p.setProductId(rs.getInt("product_id"));
					p.setName(rs.getString("name"));
					p.setPrice(rs.getInt("price"));
					p.setImagePath(rs.getString("image_path"));
					p.setDescription(rs.getString("description"));
					p.setCreatedAt(rs.getTimestamp("created_at"));
					p.setSoldOut(rs.getBoolean("is_sold_out"));
					list.add(p);
				}
			}
		}
		return list;
	}

	// カテゴリ + キーワード + 並び替え
	public List<ProductBean> searchByCategoryAndKeywordSorted(int categoryId, String keyword, String sort)
			throws Exception {
		List<ProductBean> list = new ArrayList<>();

		String baseSql = "SELECT * FROM items WHERE category_id = ? AND is_deleted = FALSE AND (name LIKE ? OR description LIKE ?)";
		String orderClause = "";

		if ("asc".equals(sort)) {
			orderClause = " ORDER BY price ASC";
		} else if ("desc".equals(sort)) {
			orderClause = " ORDER BY price DESC";
		} else {
			orderClause = " ORDER BY created_at DESC"; // デフォルト
		}

		String sql = baseSql + orderClause;

		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			String like = "%" + keyword + "%";
			ps.setInt(1, categoryId);
			ps.setString(2, like);
			ps.setString(3, like);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ProductBean item = new ProductBean();
					item.setProductId(rs.getInt("product_id"));
					item.setName(rs.getString("name"));
					item.setPrice(rs.getInt("price"));
					item.setImagePath(rs.getString("image_path"));
					item.setDescription(rs.getString("description"));
					item.setCreatedAt(rs.getTimestamp("created_at"));
					item.setSoldOut(rs.getBoolean("is_sold_out"));
					list.add(item);
				}
			}
		}
		return list;
	}


	//売り切れ表示
	public void markAsSoldOut(int productId) throws Exception {
		try (Connection con = getConnection();
		     PreparedStatement st = con.prepareStatement(
		         "UPDATE items SET is_sold_out = TRUE WHERE product_id = ?")) {

			st.setInt(1, productId);
			st.executeUpdate();
		}
	}
	
	//販売中のみ表示
	public List<ProductBean> findAvailableOnly() throws Exception {
		List<ProductBean> list = new ArrayList<>();

		String sql = "SELECT * FROM items WHERE is_sold_out = false AND is_deleted = FALSE ORDER BY created_at DESC";

		try (Connection con = getConnection();
		     PreparedStatement ps = con.prepareStatement(sql);
		     ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ProductBean p = new ProductBean();
				p.setProductId(rs.getInt("product_id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setImagePath(rs.getString("image_path"));
				p.setDescription(rs.getString("description"));
				p.setCreatedAt(rs.getTimestamp("created_at"));
				p.setSoldOut(rs.getBoolean("is_sold_out"));
				list.add(p);
			}
		}

		return list;
	}
	
	//価格で並び替え + 販売中の商品のみ取得
	public List<ProductBean> findAvailableSorted(String sort) throws Exception {
	    List<ProductBean> list = new ArrayList<>();

	    String order = "ASC";
	    if ("desc".equalsIgnoreCase(sort)) {
	        order = "DESC";
	    }

	    String sql = "SELECT * FROM items WHERE is_sold_out = false AND is_deleted = FALSE ORDER BY price " + order;

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            ProductBean p = new ProductBean();
	            p.setProductId(rs.getInt("product_id"));
	            p.setName(rs.getString("name"));
	            p.setPrice(rs.getInt("price"));
	            p.setImagePath(rs.getString("image_path"));
	            p.setDescription(rs.getString("description"));
	            p.setCreatedAt(rs.getTimestamp("created_at"));
	            p.setSoldOut(rs.getBoolean("is_sold_out"));
	            list.add(p);
	        }
	    }

	    return list;
	}

	//カテゴリー + 販売中の商品のみ取得
	public List<ProductBean> findAvailableByCategory(int categoryId) throws Exception {
	    List<ProductBean> itemList = new ArrayList<>();

	    String sql = "SELECT * FROM items WHERE category_id = ? AND is_sold_out = false AND is_deleted = FALSE ORDER BY created_at DESC";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, categoryId);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                ProductBean item = new ProductBean();
	                item.setProductId(rs.getInt("product_id"));
	                item.setName(rs.getString("name"));
	                item.setPrice(rs.getInt("price"));
	                item.setImagePath(rs.getString("image_path"));
	                item.setDescription(rs.getString("description"));
	                item.setCreatedAt(rs.getTimestamp("created_at"));
	                item.setSoldOut(rs.getBoolean("is_sold_out"));
	                itemList.add(item);
	            }
	        }
	    }

	    return itemList;
	}

	//価格で並び替え + 販売中の商品のみ取得 + カテゴリー
	public List<ProductBean> findAvailableByCategorySorted(int categoryId, String sort) throws Exception {
	    List<ProductBean> list = new ArrayList<>();
	    String sql = "SELECT * FROM items WHERE category_id = ? AND is_sold_out = false AND is_deleted = FALSE ORDER BY price "
	            + ("desc".equalsIgnoreCase(sort) ? "DESC" : "ASC");

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, categoryId);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                ProductBean p = new ProductBean();
	                p.setProductId(rs.getInt("product_id"));
	                p.setName(rs.getString("name"));
	                p.setPrice(rs.getInt("price"));
	                p.setImagePath(rs.getString("image_path"));
	                p.setDescription(rs.getString("description"));
	                p.setCreatedAt(rs.getTimestamp("created_at"));
	                p.setSoldOut(rs.getBoolean("is_sold_out"));
	                list.add(p);
	            }
	        }
	    }

	    return list;
	}

	//価格で並び替え + 販売中の商品のみ取得 + 検索
	public List<ProductBean> searchAvailableByKeywordSorted(String keyword, String sort) throws Exception {
	    List<ProductBean> list = new ArrayList<>();

	    String sql =  "SELECT * FROM items " +
	             "WHERE is_sold_out = FALSE " +
	             "AND is_deleted = FALSE " +
	             "AND category_id = ? " +
	             "AND (name LIKE ? OR description LIKE ?)";

	    if ("asc".equals(sort)) {
	        sql += " ORDER BY price ASC";
	    } else if ("desc".equals(sort)) {
	        sql += " ORDER BY price DESC";
	    } else {
	        sql += " ORDER BY created_at DESC";
	    }

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        String like = "%" + keyword + "%";
	        ps.setString(1, like);
	        ps.setString(2, like);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                ProductBean p = new ProductBean();
	                p.setProductId(rs.getInt("product_id"));
	                p.setName(rs.getString("name"));
	                p.setPrice(rs.getInt("price"));
	                p.setImagePath(rs.getString("image_path"));
	                p.setDescription(rs.getString("description"));
	                p.setCreatedAt(rs.getTimestamp("created_at"));
	                p.setSoldOut(rs.getBoolean("is_sold_out"));
	                list.add(p);
	            }
	        }
	    }
	    return list;
	}

	//価格で並び替え + 販売中の商品のみ取得 + 検索 + カテゴリー
	public List<ProductBean> searchAvailableByCategoryAndKeywordSorted(int categoryId, String keyword, String sort)
	        throws Exception {
	    List<ProductBean> list = new ArrayList<>();

	    String sql = "SELECT * FROM items WHERE is_sold_out = false AND is_deleted = FALSE AND category_id = ? AND (name LIKE ? OR description LIKE ?)";

	    if ("asc".equals(sort)) {
	        sql += " ORDER BY price ASC";
	    } else if ("desc".equals(sort)) {
	        sql += " ORDER BY price DESC";
	    } else {
	        sql += " ORDER BY created_at DESC";
	    }

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        String like = "%" + keyword + "%";
	        ps.setInt(1, categoryId);
	        ps.setString(2, like);
	        ps.setString(3, like);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                ProductBean p = new ProductBean();
	                p.setProductId(rs.getInt("product_id"));
	                p.setName(rs.getString("name"));
	                p.setPrice(rs.getInt("price"));
	                p.setImagePath(rs.getString("image_path"));
	                p.setDescription(rs.getString("description"));
	                p.setCreatedAt(rs.getTimestamp("created_at"));
	                p.setSoldOut(rs.getBoolean("is_sold_out"));
	                list.add(p);
	            }
	        }
	    }

	    return list;
	}

	//商品更新前の情報を取得
	public ProductBean getProductById(int productId) throws Exception {
	    Connection con = getConnection();
	    String sql = "SELECT * FROM items WHERE product_id = ?";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setInt(1, productId);
	    ResultSet rs = ps.executeQuery();

	    ProductBean product = null;
	    if (rs.next()) {
	        product = new ProductBean();
	        product.setProductId(rs.getInt("product_id"));
	        product.setName(rs.getString("name"));
	        product.setDescription(rs.getString("description"));
	        product.setPrice(rs.getInt("price"));
	        product.setCategoryId(rs.getInt("category_id"));
	        product.setProductCondition(rs.getInt("product_condition"));
	        product.setImagePath(rs.getString("image_path"));
	    }

	    rs.close();
	    ps.close();
	    con.close();

	    return product;
	}

	
	//商品情報の更新
	public boolean updateProduct(ProductBean product) throws Exception {
	    Connection con = null;
	    PreparedStatement stmt = null;

	    try {
	        con = getConnection();

	        String sql = "UPDATE items SET name = ?, description = ?, price = ?, category_id = ?, product_condition = ?, image_path = ? WHERE product_id = ?";

	        stmt = con.prepareStatement(sql);

	        stmt.setString(1, product.getName());
	        stmt.setString(2, product.getDescription());
	        stmt.setInt(3, product.getPrice());
	        stmt.setInt(4, product.getCategoryId());
	        stmt.setInt(5, product.getProductCondition());
	        stmt.setString(6, product.getImagePath());
	        stmt.setInt(7, product.getProductId());

	        int result = stmt.executeUpdate();
	        return result > 0;

	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	
	//出品商品の削除(論理削除)
	public boolean deleteProduct(int productId) throws Exception {
	    Connection con = null;
	    PreparedStatement stmt = null;

	    try {
	        con = getConnection();
	        String sql = "UPDATE items SET is_deleted = TRUE WHERE product_id = ?";

	        stmt = con.prepareStatement(sql);
	        stmt.setInt(1, productId);

	        int result = stmt.executeUpdate();
	        return result > 0;

	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}


}
