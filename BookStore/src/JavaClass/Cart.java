package JavaClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cart_list")

public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="No")
	private int No;

	@Column(name="bookNo")
	private String bookNo;
	
	@Column(name="bookName")
	private String bookName;
	
	@Column(name="Quantity")
	private String Quantity;
	
	@Column(name="Price")
	private String Price;

	public Cart() {
		
	}
	
	public Cart(String bno,String bn,String Q,String P) {
		this.bookNo = bno;
		this.bookName = bn;
		this.Quantity = Q;
		this.Price = P;
	}

	public int getNo() {
		return No;
	}

	public String getBookNo() {
		return bookNo;
	}

	public String getBookName() {
		return bookName;
	}

	public String getQuantity() {
		return Quantity;
	}

	public String getPrice() {
		return Price;
	}
	
	public int getTotal() {
		int q = Integer.parseInt(Quantity);
		int p = Integer.parseInt(Price);
		return (q*p);
	}
	
	public void setNo(int no) {
		No = no;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public void setPrice(String price) {
		Price = price;
	}
	
	
}
