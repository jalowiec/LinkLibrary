package pl.linklibrary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.linklibrary.util.Util;
import pl.linklibrary.controller.LibraryController;
import pl.linklibrary.dao.CategoryDAO;
import pl.linklibrary.dao.LinkCategoryDAO;
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
		
		String[] chosenFormsCategories = request.getParameterValues("chosenCategories");

		LibraryController lc = new LibraryController();
		Set<Link> allLinks = lc.getAllLinks();
		Set<Category> allCategories = lc.getAllCategories();
		Map<Integer, Integer> linksForCategoryCounter = lc.countLinksForAllCategories();
		int[] 
		Set<Integer> chosenCategories = null;
		if (chosenFormsCategories != null) {
			chosenCategories = convertStringToIntSet(chosenFormsCategories);
		}
		Set<Link> linksToDisplay = getLinksToDisplay(allLinks, chosenCategories);
		
		request.setAttribute("chosenCategories", chosenCategories);
		request.setAttribute("allCategories", allCategories);
		request.setAttribute("linksToDisplay", linksToDisplay);
		request.setAttribute("linksForCategoryCounter", linksForCategoryCounter);	
		
		request.getRequestDispatcher("linklist.jsp").forward(request, response);

	}

	private Set<Link> getLinksToDisplay(Set<Link> allLinks, Set<Integer> chosenCategories) {
		Set<Link> results = new LinkedHashSet<>();
		Util util = new Util();
		if (chosenCategories == null) {
			return allLinks;
		} else {
			for (Link eachLink : allLinks) {
				Set<Integer> linkCategories = util.getCategoriesIdForLink(eachLink.getId());
				if (linkCategories.containsAll(chosenCategories)) {
					results.add(eachLink);
				}

			}
			return results;
		}
	}

	private Set<Integer> convertStringToIntSet(String[] table) {
		Set<Integer> result = new HashSet<>();
		for (String eachElement : table) {
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
