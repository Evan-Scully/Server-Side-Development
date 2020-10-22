

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserControllerServlet
 */
@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int a = Integer.parseInt(request.getParameter("age")); 
		
		//User u = new User(null, null, 0);
		User u = new User(request.getParameter("name"),request.getParameter("address") ,a);
//		u.addToArray(u);
//		User u2 = new User("john", "Galway", 100);
//		User u3 = new User("Mary", "Dublin", 46);
//		u.addToArray(u2);
//		u.addToArray(u3);
		request.setAttribute("user", u);

		request.getRequestDispatcher("ViewUser.jsp").forward(request, response);	

	}

}
