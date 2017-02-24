package com.ge.DAP.APItestscipts;

public class Book {
	
	private String title;
    private String author;
    private String price;
 
    public Book() {
    }
    
    public String toString() {
        return String.format("%s - %s - %s", title, author, price);
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
