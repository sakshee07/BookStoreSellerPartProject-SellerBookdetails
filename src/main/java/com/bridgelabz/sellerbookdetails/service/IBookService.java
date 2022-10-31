package com.bridgelabz.sellerbookdetails.service;

import com.bridgelabz.sellerbookdetails.dto.BookDTO;
import com.bridgelabz.sellerbookdetails.dto.ResponseDTO;
import com.bridgelabz.sellerbookdetails.dto.UpdateDTO;
import com.bridgelabz.sellerbookdetails.model.Book;

import javax.validation.Valid;
import java.util.List;

public interface IBookService {
    ResponseDTO addBookDetails(BookDTO bookDTO);
    List<Book> allBookDetails(BookDTO bookDTO);
    Book getBookDataById(Long id);
    Book deleteData(Long sellerId, Long bookId);
    List<Book> getBookDataByBookName(String bookName);
    Book updateDataById(UpdateDTO updateDTO, Long sellerId, Long bookId);
    List<Book> sortAscendingByPrice();
    List<Book> sortDescendingByPrice();
    Book getBookDetailsById(Long id);
    List<Book> sellerBookList(Long sellerId);
}