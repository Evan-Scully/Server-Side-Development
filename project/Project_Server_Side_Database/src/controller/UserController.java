package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Book;
import model.BookDAO;
import model.User;
import model.UserDAO;
import model.Loan;
import model.LoanDAO;


@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean logged_in;
    
    public UserController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		
		String email = request.getParameter("userEmail");
		String username = request.getParameter("userName");
		String password = request.getParameter("userPassword");
		String option = request.getParameter("submit");
		
		User me = (User) session.getAttribute("user_me");
		
		if(!logged_in)
		{
			if(option !=null && option.equals("register"))
			{
				try {
					User u = new User("null",username,email,password);
					UserDAO.instance.save(u);
					boolean created = true;
					request.setAttribute("created", created);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(option !=null && option.equals("log-in"))
			{
			try {
				me = UserDAO.instance.selectOne(email);
				boolean login = UserDAO.instance.login(email, password);
				
				if(login == false)
				{
					request.setAttribute("login", login);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else
				{
					logged_in = true;
					session.setAttribute("user", me.getId());
					session.setAttribute("user_me", me);
				}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if(logged_in == true)
		{
			if (option !=null && option.equals("change"))
			{
				try {
					String id = me.getId();
					me = UserDAO.instance.selectId(id);
					
					request.setAttribute("user_me", me);
					
					request.getRequestDispatcher("editUser.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(option !=null && option.equals("Save"))
			{
				try {
					String id = me.getId();
					User u = new User(id, email, username, password);	
					
					UserDAO.instance.update(u,id);
					
					me = UserDAO.instance.selectOne(email);
					request.setAttribute("user_me", me);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(option !=null && option.equals("logout"))
			{
				try {
					logged_in = false;
					request.getRequestDispatcher("index.jsp").forward(request, response);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				me = (User) session.getAttribute("user_me");
				ArrayList<Loan> listOfLoans = LoanDAO.instance.list(me.getId());
				ArrayList<Book> listOfBooks = BookDAO.instance.list();
				ArrayList<Book> list = new ArrayList<Book>();
				ArrayList<Book> not_loaned = new ArrayList<Book>();
				
				for(int i = 0; i < listOfLoans.size();i++)
				{
					list.add(BookDAO.instance.selectOne(listOfLoans.get(i).getBook_id()));
				}
				
				
				for(int i = 0; i < list.size();i++)
				{
					for(int j = 0; j < listOfBooks.size();j++)
					{
						if(listOfBooks.get(j).getId().equals(list.get(i).getId()))
						{
							listOfBooks.remove(j);
						}
					}
				}
					
				request.setAttribute("books", list);			
				request.setAttribute("loans", listOfLoans);
				request.setAttribute("list_of_books", listOfBooks);
				
				request.getRequestDispatcher("loan.jsp").forward(request, response);
			} 
			catch (Exception e1) 
			{
				
			}
		}
	}
}
