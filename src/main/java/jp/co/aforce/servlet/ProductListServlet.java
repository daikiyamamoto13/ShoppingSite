package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.dao.ProductDAO;

@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        ProductDAO dao = new ProductDAO();

	        // パラメータの取得
	        String query = request.getParameter("query");
	        String categoryParam = request.getParameter("categoryId");
	        String sortParam = request.getParameter("sort");
	        String availableOnlyParam = request.getParameter("availableOnly");
	        boolean availableOnly = "true".equals(availableOnlyParam);


	        List<ProductBean> newItems;
	        List<ProductBean> itemList;

	        // カテゴリIDの有無で分岐
	        if ((categoryParam == null || categoryParam.isEmpty()) &&
	        	    (query == null || query.trim().isEmpty())) {

	        	    // --- 条件なし ---
	        	    itemList = availableOnly ? dao.findAvailableSorted(sortParam) : dao.findAllSorted(sortParam);
	        	    newItems = dao.findNewest(10);

	        	} else if (categoryParam == null || categoryParam.isEmpty()) {

	        	    // --- キーワードのみ ---
	        	    itemList = availableOnly
	        	        ? dao.searchAvailableByKeywordSorted(query, sortParam)
	        	        : dao.searchByKeywordSorted(query, sortParam);
	        	    newItems = dao.findNewest(10);

	        	} else {
	        	    int categoryId = Integer.parseInt(categoryParam);

	        	    if (query == null || query.trim().isEmpty()) {

	        	        // --- カテゴリのみ ---
	        	        if (sortParam == null || sortParam.isEmpty()) {
	        	            itemList = availableOnly
	        	                ? dao.findAvailableByCategory(categoryId)
	        	                : dao.findByCategory(categoryId);
	        	        } else {
	        	            itemList = availableOnly
	        	                ? dao.findAvailableByCategorySorted(categoryId, sortParam)
	        	                : dao.findByCategorySorted(categoryId, sortParam);
	        	        }

	        	    } else {
	        	        // --- カテゴリ + キーワード + 並び替え ---
	        	        itemList = availableOnly
	        	            ? dao.searchAvailableByCategoryAndKeywordSorted(categoryId, query, sortParam)
	        	            : dao.searchByCategoryAndKeywordSorted(categoryId, query, sortParam);
	        	    }

	        	    newItems = dao.findNewestByCategory(categoryId, 10);
	        	}

	        

	        request.setAttribute("itemList", itemList);
	        request.setAttribute("newItems", newItems);

	        request.getRequestDispatcher("/views/home.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "商品一覧の取得中にエラーが発生しました。");
	        request.getRequestDispatcher("/views/error.jsp").forward(request, response);
	    }
	}
}
