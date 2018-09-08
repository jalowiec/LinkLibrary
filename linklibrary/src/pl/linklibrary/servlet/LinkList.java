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
		
		String[] chosenCategories = request.getParameterValues("chosenCategories");

		LibraryController lc = new LibraryController();
		Set<Category> allCategories = lc.getAllCategories();		
		Set<Link> linksToDisplay = lc.getLinksToDisplay(chosenCategories);
		Map<Integer, Integer> linksForCategoryCounter = lc.countLinksForAllCategories();
		
		request.setAttribute("chosenCategories", chosenCategories);
		request.setAttribute("allCategories", allCategories);
		request.setAttribute("linksToDisplay", linksToDisplay);
		request.setAttribute("linksForCategoryCounter", linksForCategoryCounter);	
		
		request.getRequestDispatcher("linklist.jsp").forward(request, response);

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
