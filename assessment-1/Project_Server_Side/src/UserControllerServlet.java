

import java.io.IOException;
import java.util.ArrayList;

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
		
		ArrayList<House> houses = new ArrayList<House>();
		
		int bedrooms = 0;
		String b = request.getParameter("bedrooms");
		String owner = request.getParameter("name");
		String address = request.getParameter("address");
		
		// PART C
		// Sets name,address to unknown and bedrooms to 0 if they are empty
		if(b == "")
		{
			bedrooms = 0;
		}
		else
		{
			bedrooms = Integer.parseInt(b);
		}
		
		if(owner == null || owner == "")
		{
			owner = "name unknown";
		}
		if(address == null || address == "")
		{
			address = "address unknown";
		}
		
		// PART B adding in new House Owners
		House u = new House(owner, address ,bedrooms);
		House u2 = new House("John", "Galway", 7);
		House u3 = new House("Mary", "Dublin", 4);
		
		houses.add(u);
		houses.add(u2);
		houses.add(u3);
		
		request.setAttribute("houses", houses);
		request.getRequestDispatcher("ViewUser.jsp").forward(request, response);	

	}

}
