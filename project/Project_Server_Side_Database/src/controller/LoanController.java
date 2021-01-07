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
import model.UserDAO;


@WebServlet("/LoanController")
public class LoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public LoanController() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String user_id = (String) session.getAttribute("user");
		String id = request.getParameter("id");
		User me = (User) session.getAttribute("user_me");
		
		String option = request.getParameter("submit");
		
		ArrayList<Loan> listOfLoans;
		ArrayList<Book> listOfBooks;
		
		try {
			listOfLoans = LoanDAO.instance.list(me.getId());
			listOfBooks = BookDAO.instance.list();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		ArrayList<Book> list = new ArrayList<Book>();
		ArrayList<Book> not_loaned = new ArrayList<Book>();
		
		if(option !=null && option.equals("Return"))
		{
			try {
				LoanDAO.instance.delete(id,user_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (option !=null && option.equals("Edit"))
		{
			try {
				String book_id = request.getParameter("id");
				Book book = BookDAO.instance.selectOne(book_id);
				request.setAttribute("book", book);
				request.getRequestDispatcher("editBook.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (option !=null && option.equals("Loan"))
		{
			try {
				LoanDAO.instance.save(new Loan("null",id,user_id));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			
		listOfLoans = LoanDAO.instance.list(me.getId());
		listOfBooks = BookDAO.instance.list();
		list = new ArrayList<Book>();
		not_loaned = new ArrayList<Book>();
		
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
			
		} catch (Exception e)
		{
			
		}
		

	}

}
