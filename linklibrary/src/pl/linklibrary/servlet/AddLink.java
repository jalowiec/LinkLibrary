package pl.linklibrary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.linklibrary.dao.LinkDAO;
import pl.linklibrary.model.Link;


/**
 * Servlet implementation class AddLink
 */
@WebServlet("/AddLink")
public class AddLink extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddLink() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Link link = linkToAddFromRequest(request);
		LinkDAO dao = new LinkDAO();
		dao.create(link);
		response.sendRedirect("LinkList");
	}

	
	public static Link linkToAddFromRequest(HttpServletRequest request) {

		String linkName = request.getParameter("name");
		String description = request.getParameter("description");
		String url = request.getParameter("url");
		Link link = new Link(linkName, description, url);

		return link;

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
