package com.bridgelabz.sellerbookdetails.service;

import com.bridgelabz.sellerbookdetails.dto.BookDTO;
import com.bridgelabz.sellerbookdetails.dto.ResponseDTO;
import com.bridgelabz.sellerbookdetails.dto.SellerDTO;
import com.bridgelabz.sellerbookdetails.dto.UpdateDTO;
import com.bridgelabz.sellerbookdetails.exception.BookException;
import com.bridgelabz.sellerbookdetails.model.Book;
import com.bridgelabz.sellerbookdetails.repository.BookRepo;
import com.bridgelabz.sellerbookdetails.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BookService implements IBookService{
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private TokenUtility tokenUtility;
    @Autowired
    private RestTemplate restTemplate;
    private String SELLER_ID_URI = "http://localhost:7070/seller/sellerId/";
    //Adding book details
    @Override
    public ResponseDTO addBookDetails(BookDTO bookDTO) {
        //using rest template to get the seller details
        SellerDTO sellerDetails = restTemplate.getForObject(SELLER_ID_URI+bookDTO.getSellerId(), SellerDTO.class);
        System.out.println(sellerDetails);
        if(sellerDetails!=null && sellerDetails.isVerify()){
            Book bookDetails = new Book(bookDTO);
            bookRepo.save(bookDetails);
            String token = tokenUtility.createToken(bookDetails.getId());
            ResponseDTO respDTO = new ResponseDTO(token, bookDetails);
            return respDTO;
        }else
            throw new BookException("Invalid Seller ID or Seller does not exist or Seller is not verified");
    }
    //get all book details
    @Override
    public List<Book> allBookDetails(BookDTO bookDTO) {
        List<Book> bookList = bookRepo.findAll();
        if (bookList.isEmpty()) {
            throw new BookException("No Books Added yet!");
        } else
            return bookList;
    }
    //get book details by BookId
    @Override
    public Book getBookDataById(Long bookId) {
        Optional<Book> bookDetails = bookRepo.findById(bookId);
        if (bookDetails != null) {
            return bookDetails.get();
        } else
            throw new BookException("Book ID: " + bookId + " is not available");
    }
    //get book details by id(Microservice check)
    @Override
    public Book getBookDetailsById(Long id) {
        Book bookDetails = bookRepo.findById(id).orElse(null);
        if (bookDetails != null) {
            return bookDetails;
        } else
            return null;
    }
    //Get Book Data by Book Name
    @Override
    public List<Book> getBookDataByBookName(String bookName) {
        List<Book> bookDetails = bookRepo.findByBookName(bookName);
        if (bookDetails == null) {
            throw new BookException("Book Name: " + bookName + " is not available");
        } else
            return bookDetails;
    }
    //update book data by book ID and Seller ID
    @Override
    public Book updateDataById(UpdateDTO updateDTO, Long sellerId, Long bookId) {
        Book bookDetails = bookRepo.findById(bookId).orElse(null);
        SellerDTO sellerDetails = restTemplate.getForObject(SELLER_ID_URI + sellerId, SellerDTO.class);
        if (bookDetails != null && sellerDetails != null && bookDetails.getSellerId().equals(sellerId) && sellerDetails.isVerify()) {
            bookDetails.setBookName(updateDTO.getBookName());
            bookDetails.setAuthorName(updateDTO.getAuthorName());
            bookDetails.setBookDescription(updateDTO.getBookDescription());
            bookDetails.setBookImage(updateDTO.getBookImage());
            bookDetails.setPrice(updateDTO.getPrice());
            bookDetails.setQuantity(updateDTO.getQuantity());
            return bookRepo.save(bookDetails);
        } else
            throw new BookException("Invalid Book ID | Seller ID | Seller Is not verified");
    }
    //Sorting : Ascending
    @Override
    public List<Book> sortAscendingByPrice() {
        List<Book> listOfBooks = bookRepo.findAll();
        //sorting the list in ascending order by price
        List<Book> bookList = listOfBooks.stream()
                .sorted(Comparator.comparingDouble(Book::getPrice))
                .collect(Collectors.toList());
        if (bookList.isEmpty()) {
            throw new BookException("No Books added in the list yet!!!");
        } else
            return bookList;
    }
    //Sorting : Descending
    @Override
    public List<Book> sortDescendingByPrice() {
        List<Book> listOfBooks = bookRepo.findAll();
        //sorting the list in descending order by price
        List<Book> bookList = listOfBooks.stream()
                .sorted(Comparator.comparingDouble(Book::getPrice).reversed())
                .collect(Collectors.toList());
        if (bookList.isEmpty()) {
            throw new BookException("No Books added in the list yet!!!");
        } else
            return bookList;
    }
    //Get book list of a particular seller
    @Override
    public List<Book> sellerBookList(Long sellerId) {
        SellerDTO sellerDetails = restTemplate.getForObject(SELLER_ID_URI + sellerId, SellerDTO.class);
        if(sellerDetails != null){
            List<Book> bookList = bookRepo.findAllBySellerId(sellerId);
            return bookList;
        }else
            throw new BookException("Seller ID, does not exist");
    }
    //delete by SellerId/BookId
    @Override
    public Book deleteData(Long sellerId, Long bookId) {
        Book bookDetails = bookRepo.findById(bookId).orElse(null);
        SellerDTO sellerDetails = restTemplate.getForObject(SELLER_ID_URI + sellerId, SellerDTO.class);
        if (bookDetails != null && sellerDetails != null && bookDetails.getSellerId().equals(sellerId) && sellerDetails.isVerify()) {
            bookRepo.deleteById(bookId);
            return bookDetails;
        } else
            throw new BookException("Invalid Book ID | Seller ID | Seller is not verified");
    }
}