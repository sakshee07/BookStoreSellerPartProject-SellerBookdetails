package com.bridgelabz.sellerbookdetails.controller;

import com.bridgelabz.sellerbookdetails.dto.BookDTO;
import com.bridgelabz.sellerbookdetails.dto.ResponseDTO;
import com.bridgelabz.sellerbookdetails.dto.UpdateDTO;
import com.bridgelabz.sellerbookdetails.model.Book;
import com.bridgelabz.sellerbookdetails.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    IBookService bookService;

    //Inserting Data
    @PostMapping("/insertBook")
    public ResponseEntity<ResponseDTO> addBookDetails(@Valid @RequestBody BookDTO bookDTO){
        ResponseDTO response = bookService.addBookDetails(bookDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //Get all Book Details
    @GetMapping("/getAllBooks")
    public ResponseEntity<ResponseDTO> getAllBookDetails(BookDTO bookDTO){
        List<Book> bookList = bookService.allBookDetails(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("All Book Details, total count: "+bookList.size(), bookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Get the book details by Book ID
    @GetMapping("/getByBookId/{id}")
    public Book getBookDataById(@PathVariable Long id) {
        Book bookDetails = bookService.getBookDataById(id);
        return bookDetails;
    }
    //Search by Book
    @GetMapping("/getBookData/{bookName}")
    public ResponseEntity<ResponseDTO> getBookDataById(@PathVariable String bookName) {
        List<Book> bookDetails = bookService.getBookDataByBookName(bookName);
        ResponseDTO responseDTO = new ResponseDTO("Book details: ",bookDetails);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Update Details or Quantity by Book
    @PutMapping("/updateBookData/{sellerId}/{bookId}")
    public ResponseEntity<ResponseDTO> updateDataById(@PathVariable Long bookId,@PathVariable Long sellerId,@Valid @RequestBody UpdateDTO updateDTO) {
        Book bookData = bookService.updateDataById(updateDTO, sellerId, bookId);
        ResponseDTO respDTO= new ResponseDTO("Data Update info", bookData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    //Sorting in Ascending order by price
    @GetMapping("/sortByPrice/ascending")
    public ResponseEntity<ResponseDTO> sortingAscending(){
        List<Book> bookList = bookService.sortAscendingByPrice();
        ResponseDTO responseDTO = new ResponseDTO("Sorted by Price in Ascending order", bookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Sorting in Descending order by price
    @GetMapping("/sortByPrice/descending")
    public ResponseEntity<ResponseDTO> sortingDescending(){
        List<Book> bookList = bookService.sortDescendingByPrice();
        ResponseDTO responseDTO = new ResponseDTO("Sorted by Price in Descending order", bookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Get all book list of seller
    @GetMapping("/bookListOfSeller/sellerId/{sellerId}")
    public ResponseEntity<ResponseDTO> sellerBookList(@PathVariable Long sellerId){
        List<Book> bookList = bookService.sellerBookList(sellerId);
        ResponseDTO responseDTO = new ResponseDTO("All Book List of seller Id: "+sellerId, bookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Delete book details by ID
    @DeleteMapping("/deleteBook/{sellerId}/{bookId}")
    public ResponseEntity <ResponseDTO> deleteBookDataByID(@PathVariable Long sellerId,@PathVariable Long bookId) {
        Book deletedData = bookService.deleteData(sellerId, bookId);
        ResponseDTO respDTO= new ResponseDTO("Deleted Successfully, Below Data is deleted", deletedData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}
