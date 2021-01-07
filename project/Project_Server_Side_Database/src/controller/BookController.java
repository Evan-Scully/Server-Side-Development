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
import model.Loan;
import model.LoanDAO;
import model.User;


@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public BookController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User me = (User) session.getAttribute("user_me");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String series = request.getParameter("series");
		String ISBN = request.getParameter("isbn");
		
		
		String option = request.getParameter("submit");
		
		if(option !=null && option.equals("Save"))
		{
			Book b1 = new Book(id, name, author, series, ISBN);
			try {
				BookDAO.instance.update(b1,id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(option !=null && option.equals("Create"))
		{
			Book b = new Book("null",name,author,series,ISBN); 
			try {
				BookDAO.instance.save(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			ArrayList<Loan> listOfLoans = LoanDAO.instance.list(me.getId());
			ArrayList<Book> listOfBooks = BookDAO.instance.list();
			
			listOfLoans = LoanDAO.instance.list(me.getId());
			listOfBooks = BookDAO.instance.list();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
