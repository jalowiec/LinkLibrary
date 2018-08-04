package pl.javastart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.javastart.dao.LinkDAO;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Link link = createLinkFromRequest(request);
		LinkDAO dao = new LinkDAO();
		dao.create(link);
		request.getRequestDispatcher("LinkList").forward(request, response);

	}

	Link createLinkFromRequest(HttpServletRequest request) {
		String linkName = request.getParameter("name");
		String description = request.getParameter("description");
		String url = request.getParameter("url");
		Link link = new Link(0, linkName, description, url);
		return link;

	}

}
