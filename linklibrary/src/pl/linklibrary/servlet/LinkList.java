package pl.linklibrary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.linklibrary.dao.CategoryDAO;
import pl.linklibrary.dao.LinkDAO;
import pl.linklibrary.model.Category;
import pl.linklibrary.model.Link;

/**
 * Servlet implementation class LinkList
 */
@WebServlet("/LinkList")
public class LinkList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LinkList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Set<Link> links = getLinks();
		request.setAttribute("links", links);
		
		Set<Category> categories = getCategories();
		request.setAttribute("categories", categories);
		
		String[] chosenFormsCategories = request.getParameterValues("chosenCategories");
		Set<Integer> chosenCategories = null;
		if(chosenFormsCategories != null) {
			chosenCategories = convertStringToIntSet(chosenFormsCategories); 
		}
		request.setAttribute("chosenCategories", chosenCategories);
		
			
		request.getRequestDispatcher("linklist.jsp").forward(request, response);

	}	

	private Set<Link> getLinks() {
		Set<Link> links = new LinkedHashSet<>();
		LinkDAO dao = new LinkDAO();
		links = dao.readAll(0);
		return links;
	}
	
	private Set<Category> getCategories() {
		Set<Category> categories = new TreeSet<>();
		CategoryDAO dao = new CategoryDAO();
		categories = dao.readAll(0);
   		return categories;	
	}
	
	Set<Integer> convertStringToIntSet(String[] table) {
		Set<Integer>result = new HashSet<>();
		for(String eachElement : table) {
			result.add(Integer.parseInt(eachElement));
		}
		
		return result;
	}
	
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);

	}



}
