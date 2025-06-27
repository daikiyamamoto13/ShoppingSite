package jp.co.aforce.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.beans.UserBean;
import jp.co.aforce.dao.ProductDAO;

/**
 * Servlet implementation class ItemAddServlet
 */
@WebServlet("/ProductAddServlet")
@MultipartConfig
public class ProductAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//セッションからmemberIdを取得
		HttpSession session = request.getSession(); // セッションがなければnullを返す
		UserBean user = (UserBean) session.getAttribute("user");
		String memberId = user.getMemberId();
		
		//入力値を一旦文字列で取得
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String priceStr = request.getParameter("price");
		String categoryStr = request.getParameter("categoryId");
		String conditionStr = request.getParameter("conditionId");
			
		
		// バリデーション（null や 空文字 のチェック）
		if (priceStr == null || priceStr.isEmpty() ||
		    categoryStr == null || categoryStr.isEmpty() ||
		    conditionStr == null || conditionStr.isEmpty())
		{

		    request.setAttribute("errorMessage", "価格、カテゴリ、商品状態をすべて入力してください。");
		    request.getRequestDispatcher("productAdd.jsp").forward(request, response);
		    return;
		}

		// 数値変換
		int price, categoryId, productCondition;
		try {
		    price = Integer.parseInt(priceStr);
		    categoryId = Integer.parseInt(categoryStr);
		    productCondition = Integer.parseInt(conditionStr);
		} catch (NumberFormatException e) {
		    request.setAttribute("errorMessage", "数値項目の入力が不正です。");
		    request.getRequestDispatcher("/productAdd.jsp").forward(request, response);
		    return;
		}


		
		
		
		
		
//		//ファイル名取得
//		String fileName = Path.of(imagePart.getSubmittedFileName()).getFileName().toString();
//
//		// 環境によって保存先を切り替え
//		String serverName = request.getServerName();
//		String uploadDir = getServletContext().getRealPath("/images");
//
//		// ディレクトリを作成（なければ）
//		File dir = new File(uploadDir);
//		if (!dir.exists()) {
//		    dir.mkdirs();
//		}
//
//		// 保存処理
//		File saveFile = new File(dir, fileName);
//		imagePart.write(saveFile.getAbsolutePath());
//
//		// Webで表示するための相対パスを組み立てて保存（必要に応じてDBに）
//		String imagePath = "images/" + fileName;
//		request.setAttribute("imagePath", imagePath);

		// アップロード画像を取得
				Part imagePart = request.getPart("imagePath");
				String FileName = imagePart.getSubmittedFileName(); 
		//フォルダが存在しない場合は作成
		// 1. 保存先のディレクトリ（/webapp/images の絶対パス）
		String uploadDirPath = getServletContext().getRealPath("/images");
		InputStream input = imagePart.getInputStream();
		File uploadDir = new File(uploadDirPath);
		if (!uploadDir.exists()) {
		    uploadDir.mkdirs();
		    
		}

		
		
		String fileName = uploadDirPath + File.separator + FileName;
		Files.copy(input, new File(fileName).toPath(),StandardCopyOption.REPLACE_EXISTING);
		String imagePath = "../images/" + FileName;
		
		ProductBean product = new ProductBean();
		product.setImagePath(imagePath);
//				

//		// 3. 保存ファイル（Fileオブジェクト）を生成
//		File imageFile = new File(uploadDir, fileName);
//
//		// 4. アップロードファイルを保存
//		try (InputStream input = imagePart.getInputStream()) {
//		    Files.copy(input, imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//		}
//
//		// 5. DBに保存する画像パス（URLとして使用）
//		
//
//		// 6. ProductBean に画像パスをセット
//		




		
	    //Beanにデータをセット	
	    ProductBean item = new ProductBean();
	    item.setName(name);
	    item.setDescription(description);
	    item.setPrice(price);
	    item.setCategoryId(categoryId);
	    item.setProductCondition(productCondition);
	    item.setImagePath("images/" + imagePath);
	    item.setMemberId(memberId);
	    

	    
	    //DAOを使ってDBに登録
	    try {
	        ProductDAO dao = new ProductDAO();
	        dao.insertItem(item);
	        
	     // 登録完了後に完了ページへ遷移
	        request.getRequestDispatcher("views/productAdd-Complete.jsp").forward(request, response);
	        return;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "商品登録に失敗しました。");
	        request.getRequestDispatcher("error.jsp").forward(request, response);
	        return;
	    }

	    

//		//ホーム画面に商品一覧を表示する
//		
//		try {
//			ProductDAO dao = new ProductDAO();
//
//			String categoryParam = request.getParameter("category_id");
//			List<ProductBean> itemList;
//			if (categoryParam == null || categoryParam.isEmpty()) {
//				itemList = dao.findAll();
//			} else {
//				int categoryId = Integer.parseInt(categoryParam);
//				itemList = dao.findByCategory(categoryId);
//			}
//
//			request.setAttribute("itemList", itemList);
//			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("errorMessage", "商品一覧の取得中にエラーが発生しました。");
//			request.getRequestDispatcher("/error.jsp").forward(request, response);
//		}
	

	    
	  }
}
