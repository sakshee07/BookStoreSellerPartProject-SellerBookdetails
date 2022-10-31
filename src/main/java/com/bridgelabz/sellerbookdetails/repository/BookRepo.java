package com.bridgelabz.sellerbookdetails.repository;

import com.bridgelabz.sellerbookdetails.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM book WHERE book_name like :bookName%", nativeQuery = true)
    List<Book> findByBookName(String bookName);
    @Query(value = "SELECT * FROM book WHERE seller_id=:sellerId", nativeQuery = true)
    List<Book> findAllBySellerId(Long sellerId);
}