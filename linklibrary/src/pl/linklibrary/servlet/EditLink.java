package pl.linklibrary.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.linklibrary.controller.LibraryController;
import pl.linklibrary.dao.LinkDAO;
import pl.linklibrary.model.Link;


/**
 * Servlet implementation class EditLink
 */
@WebServlet("/EditLink")
public class EditLink extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditLink() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Link link = linkToEditFromRequest(request); 
		LibraryController lc = new LibraryController();
		lc.editLink(link);
		
		response.sendRedirect("LinkList");

	}	

	public static Link linkToEditFromRequest(HttpServletRequest request) {

		String linkName = request.getParameter("name");
		String description = request.getParameter("description");
		String url = request.getParameter("url");
		int url_id = Integer.parseInt(request.getParameter("link_id"));
		return  new Link(url_id, linkName, description, url);

	}    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
