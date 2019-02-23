package Team76.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProfessorController
 */
@WebServlet("/ProfessorController")
public class ProfessorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfessorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null || action.isEmpty()) {
			response.sendRedirect("login.jsp");
		}
		if(action.equals("CreateQuiz")) {
			response.sendRedirect("CreateQuiz.jsp");
		}
		if(action.equals("Questions")) {
			response.sendRedirect("Questions.jsp");
		}
		if(action.equals("ViewGrades")) {
			response.sendRedirect("ViewGrades.jsp");
		}
		if(action.equals("Statistics")) {
			response.sendRedirect("Statistics.jsp");
		}
	}
}
