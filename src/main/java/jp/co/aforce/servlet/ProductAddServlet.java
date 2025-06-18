package jp.co.aforce.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import jp.co.aforce.beans.ProductBean;

/**
 * Servlet implementation class ItemAddServlet
 */
@WebServlet("/ProductAddServlet")
public class ProductAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//入力値の取得
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int price = Integer.parseInt(request.getParameter("price"));
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		int product_condition = Integer.parseInt(request.getParameter("product_condition"));
		
		//セッションからmemberIdを取得
		HttpSession session = request.getSession(false); // セッションがなければnullを返す
		if (session == null || session.getAttribute("memberId") == null) {
		    // ログインしていない場合の処理（例：ログイン画面にリダイレクト）
		    response.sendRedirect("login.jsp");
		    return;
		}
		int memberId = (int) session.getAttribute("memberId");

		
		//アップロード画像を取得
		Part imagePart = request.getPart("image");
		String image_path = Path.of(imagePart.getSubmittedFileName()).getFileName().toString();
		
		//画像の保存場所を取得(webbapp/imagesフォルダに保存)
	    String savePath = getServletContext().getRealPath("/images");
	    File uploadDir = new File(savePath);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdirs();
	    }
	    
	    //画像を保存
	    File imageFile = new File(uploadDir, image_path);
	    try (InputStream input = imagePart.getInputStream()) {
	        Files.copy(input, imageFile.toPath());
	    }

	    //Beanにデータをセット	
	    ProductBean item = new ProductBean();
	    item.setName(name);
	    item.setDescription(description);
	    item.setPrice(price);
	    item.setCategory_id(category_id);
	    item.setProduct_condition(product_condition);
	    item.setImage_path("images/" + image_path);
	    item.setMember_id(memberId);

	    

	

	    response.sendRedirect("home.jsp"); // 出品後に一覧へ
	  }
}
