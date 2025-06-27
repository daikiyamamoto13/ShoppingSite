//package jp.co.aforce.servlet;
//
//import java.io.IOException;
//import java.util.List;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import jp.co.aforce.beans.ProductBean;
//import jp.co.aforce.dao.ProductDAO;
//
///**
// * Servlet implementation class SearchServlet
// */
//@WebServlet("/SearchServlet")
//public class SearchServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String query = request.getParameter("query");
//        String categoryParam = request.getParameter("categoryId");
//        String sortParam = request.getParameter("sort");
//        
//        try {
//            ProductDAO dao = new ProductDAO();
//
//            List<ProductBean> results;
//            List<ProductBean> newItems;
//
//            if ((categoryParam == null || categoryParam.isEmpty()) &&
//                (query == null || query.trim().isEmpty())) {
//                // 条件なし → 全件取得 + 全体の新着
//                results = dao.findAllSorted(sortParam);
//                newItems = dao.findNewest(10);
//
//            } else if (categoryParam == null || categoryParam.isEmpty()) {
//                // キーワードのみ → キーワード検索 + 全体の新着
//                results = dao.searchByKeywordSorted(query,sortParam);
//                newItems = dao.findNewest(10);
//
//            } else {
//                // カテゴリ + キーワード（またはカテゴリのみ）
//                int categoryId = Integer.parseInt(categoryParam);
//
//                if (query == null || query.trim().isEmpty()) {
//                    // カテゴリのみ
//                    results = dao.findByCategory(categoryId);  
//                } else {
//                    // カテゴリ + キーワード + 並び替え
//                    results = dao.searchByCategoryAndKeywordSorted(categoryId, query,sortParam);
//                }
//
//                // カテゴリに絞った新着商品
//                newItems = dao.findNewestByCategory(categoryId, 10);
//            }
//
//            request.setAttribute("itemList", results);
//            request.setAttribute("newItems", newItems);
//            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("errorMessage", "検索エラー");
//            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
//        }
//    }
//}
//
