package com.bridgelabz.sellerbookdetails.model;

import com.bridgelabz.sellerbookdetails.dto.BookDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BookId", nullable = false)
    private Long id;
    private Long sellerId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImage;
    private double price;
    private int quantity;
    public Book(BookDTO bookdto){
        this.bookName = bookdto.getBookName();
        this.sellerId = bookdto.getSellerId();
        this.authorName = bookdto.getAuthorName();
        this.bookDescription = bookdto.getBookDescription();
        this.bookImage = bookdto.getBookImage();
        this.price = bookdto.getPrice();
        this.quantity = bookdto.getQuantity();
    }
    Book(){
    	
    }
	public Long getId() {
		return id;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public String getBookName() {
		return bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public String getBookDescription() {
		return bookDescription;
	}
	public String getBookImage() {
		return bookImage;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}
	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    
}
