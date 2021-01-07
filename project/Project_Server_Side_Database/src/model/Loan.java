package model;

public class Loan {

	private String loan_id;
	private String book_id;
	private String user_id;

	public Loan(String loan_id, String book_id, String user_id) {
		this.loan_id = loan_id;
		this.book_id = book_id;
		this.user_id = user_id;
	}
	public String getLoan_id() {
		return loan_id;
	}

	public String getBook_id() {
		return book_id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setLoan_id(String loan_id) {
		this.loan_id=loan_id;
	}

	public void setBook_id(String book_id) {
		this.book_id=book_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id=user_id;
	}

}
