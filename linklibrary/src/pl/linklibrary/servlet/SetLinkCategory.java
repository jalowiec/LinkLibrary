package pl.linklibrary.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.linklibrary.dao.LinkCategoryDAO;

/**
 * Servlet implementation class SetLinkCategory
 */
@WebServlet("/SetLinkCategory")
public class SetLinkCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetLinkCategory() {
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

		LinkCategoryDAO dao = new LinkCategoryDAO();
		int linkId = Integer.parseInt(request.getParameter("link_id"));
		dao.delete(linkId);

		String[] categoriesList = request.getParameterValues("categories");
		if (categoriesList != null) {
			int[] intCategoriesList = new int[categoriesList.length];
			int i = 0;
			for (String category : categoriesList) {
				intCategoriesList[i++] = Integer.parseInt(category);
			}

			dao.create(linkId, intCategoriesList);
		}
		response.sendRedirect("LinkList");
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

}
