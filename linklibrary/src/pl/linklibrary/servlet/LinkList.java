package pl.linklibrary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.linklibrary.dao.LinkDAO;
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

		List<Link> linkList = getLinks();
		request.setAttribute("linkList", linkList);
		request.getRequestDispatcher("linklist.jsp").forward(request, response);

	}	

	List<Link> getLinks() {
		List<Link> linkList = new ArrayList<>();
		LinkDAO dao = new LinkDAO();
		linkList = dao.readAll(0);
		return linkList;
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
