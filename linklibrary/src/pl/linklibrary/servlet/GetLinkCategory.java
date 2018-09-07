package pl.linklibrary.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.linklibrary.controller.LibraryController;
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
		
		LibraryController lc = new LibraryController();
		Set<Category> categories = lc.getAllCategories();
		Set<Integer> linkCategories = lc.getCategoriesIdForLink(linkId);

		request.setAttribute("categories", categories);
		request.setAttribute("linkCategories", linkCategories);
		request.setAttribute("linkUrl", linkUrl);
		request.setAttribute("linkId", linkId);

		request.getRequestDispatcher("setlinkcategory.jsp").forward(request, response);		
	}
	

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
