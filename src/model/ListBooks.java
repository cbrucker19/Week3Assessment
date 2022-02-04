package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cyrusbrucker - cbrucker19
 * CIS175 - Spring 2022
 * Feb 3, 2022
 */
@Entity
@Table(name = "TextBooks")

public class ListBooks {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	@Column(name = "books")
	private String books;
	@Column(name = "classes")
	private String classes;
	
	//constructors
	public ListBooks() {
		super();
	}
	public ListBooks(String books, String classes) {
		super();
		this.books = books;
		this.classes = classes; 
	}
	public ListBooks(int id, String books, String classes) {
		super();
		this.id = id; 
		this.books = books; 
		this.classes = classes; 
	}
	//Getters and setters 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBooks() {
		return books;
	}
	public void setBooks(String books) {
		this.books = books;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	
	@Override
	public String toString() {
		return "ListBooks [id=" + id + ", books=" + books + ", classes=" + classes + "]";
	}
	public String returnBookDetails() {
		return this.books + ":" + this.classes; 
	}
}
