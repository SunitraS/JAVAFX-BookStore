package JavaClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book_list")

public class Book {
	@Id
	
	@Column(name="No")
	private String No;

	@Column(name="BookName")
	private String BookName;
	
	@Column(name="Author")
	private String Author;
	
	@Column(name="Price")
	private int Price;
	
	public Book() {}
	
	public Book(String No,String Bookname,String Author,int Price) {
		this.No = No;
		this.BookName = Bookname;
		this.Author = Author;
		this.Price = Price;
	}

	public String getNo() {
		return No;
	}

	public String getBookName() {
		return BookName;
	}

	public String getAuthor() {
		return Author;
	}

	public int getPrice() {
		return Price;
	}

	public void setNo(String no) {
		No = no;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public void setPrice(int price) {
		Price = price;
	}
}
