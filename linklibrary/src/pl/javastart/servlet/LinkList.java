package pl.javastart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			/* TODO output your page here. You may use following sample code. */

				List<Link> linkList = getLinks();
				request.setAttribute("linkList", linkList);
				request.getRequestDispatcher("linklist.jsp").forward(request, response);

			}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	List<Link> getLinks() {
		List<Link> listLink = new ArrayList<>();
		/*
		 * //final String dbPath =
		 * "jdbc:mysql://localhost:3306/sakila?useSSL=false&serverTimezone=UTC"; //final
		 * String sqlQuery = "SELECT first_name, last_name FROM actor";
		 * 
		 * // final String dbPath =
		 * "jdbc:mysql://localhost:3306/linklibrary?useSSL=false&serverTimezone=UTC"; //
		 * final String sqlQuery = "SELECT category_id, name FROM category";
		 * 
		 * Connection conn; try { conn = (Connection)
		 * DriverManager.getConnection(dbPath, "root", "root"); } catch (SQLException
		 * ex) { Logger.getLogger(SqlServlet.class.getName()).log(Level.SEVERE, null,
		 * ex); }
		 * 
		 */


		listLink.add(0, new Link("Uncle Bob Martin - The Future of Programming", "Historia i przysz³oœæ programowania", "https://www.youtube.com/watch?v=ecIWPzGEbFc"));
		listLink.add(1, new Link("Kurs HTML5", "Podstawy  HTML5", "http://how2html.pl/"));		
		listLink.add(2, new Link("THE WORLD'S LARGEST WEB DEVELOPER SITE", "HTML + CSS + frameworki", "https://www.w3schools.com/"));
		listLink.add(3, new Link("Christopher Okhravi - wzorce projektowe", "Opis wzorców na postawie HEAD FIRST", "https://www.youtube.com/watch?v=v9ejT8FO-7I&list=PLrhzvIcii6GNjpARdnO4ueTUAVR9eMBpc"));		
		listLink.add(4, new Link("Komponenty Boostrapa", "Glyphicons, headers, buttons ..", "https://getbootstrap.com/docs/3.3/components/"));		
		return listLink;

	}

}
