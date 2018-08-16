package pl.linklibrary.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.linklibrary.dao.CategoryDAO;
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
		
		List<Category> categoriesList = getCategoriesList();
		request.setAttribute("categoriesList", categoriesList);
		String linkUrl = request.getParameter("link_url");
		request.setAttribute("linkUrl", linkUrl);
		int linkId = Integer.parseInt(request.getParameter("link_id"));
		request.setAttribute("linkId", linkId);
		request.getRequestDispatcher("setlinkcategory.jsp").forward(request, response);		
	}
	
	List<Category> getCategoriesList(){
		List<Category> categoriesList = new ArrayList<>();
		CategoryDAO dao = new CategoryDAO();
		categoriesList = dao.readAll(0);
		return categoriesList;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
