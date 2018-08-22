package pl.linklibrary.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.linklibrary.dao.CategoryDAO;
import pl.linklibrary.dao.LinkCategoryDAO;
import pl.linklibrary.model.Category;

/**
 * Servlet implementation class GetLinkCategory
 */
@WebServlet("/GetLinkCategory")
public class GetLinkCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLinkCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String linkUrl = request.getParameter("link_url");
		int linkId = Integer.parseInt(request.getParameter("link_id"));
		Set<Category> categories = getCategories();
		Set<Integer> linkCategories = getCategoriesIdForLink(linkId);

		request.setAttribute("categories", categories);
		request.setAttribute("linkCategories", linkCategories);
		request.setAttribute("linkUrl", linkUrl);
		request.setAttribute("linkId", linkId);

		request.getRequestDispatcher("setlinkcategory.jsp").forward(request, response);		
	}
	
	Set<Category> getCategories(){
		Set<Category> categories = new TreeSet<>();
		CategoryDAO dao = new CategoryDAO();
		categories = dao.readAll(0);
		return categories;
		
	}
	
	Set<Integer> getCategoriesIdForLink(int linkId){
		Set<Integer> linkCategories = new TreeSet<>();
		LinkCategoryDAO dao = new LinkCategoryDAO();
		linkCategories = dao.readLinkCategories(linkId);				
		return linkCategories;
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
