package model;

public class Book {
	
	
	private String id;
	private String name;
	private String author;
	private String series;
	private String ISBN;

	public Book(String id, String name,String author, String series, String ISBN) {
		this.id=id;
		this.name=name;
		this.author=author;
		this.series=series;
		this.ISBN=ISBN;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	

}
