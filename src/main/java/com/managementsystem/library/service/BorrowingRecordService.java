package com.managementsystem.library.service;

import com.managementsystem.library.entities.Book;
import com.managementsystem.library.entities.BorrowingRecord;
import com.managementsystem.library.entities.Patron;
import com.managementsystem.library.exceptions.ApiBaseException;
import com.managementsystem.library.repository.BookRepository;
import com.managementsystem.library.repository.BorrowingRecordRepository;
import com.managementsystem.library.repository.PatronRepository;
import com.managementsystem.library.utils.ResponseObject;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository BorrowingRecordRepo;

    @Autowired
    private BookRepository BookRepo;

    @Autowired
    private PatronRepository PatronRepo;

    @Transactional
    public ResponseEntity<Object> borrow(Integer bookId, Integer patronId) {
        Book book = BookRepo.findById(bookId).orElse(null);
        Patron patron = PatronRepo.findById(patronId).orElse(null);
        if (book == null || patron == null) {
            return ResponseObject.FAILED_RESPONSE("Book or patron or both not exist, check the id ", HttpStatus.NOT_FOUND);
        }
        BorrowingRecord borrowingRecord = BorrowingRecordRepo.findByBookAndPatronAndAndReturnDateIsNull(book, patron);
        if (borrowingRecord != null) {
            return ResponseObject.FAILED_RESPONSE("You can't borrow this book for this patron because it is already borrowed to him", HttpStatus.FORBIDDEN);
        }
        borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(LocalDate.now());
        BorrowingRecordRepo.save(borrowingRecord);
        return ResponseObject.SUCCESS_RESPONSE("Book with id :" + bookId + " reserved to patron : " + patronId);
    }
    @Transactional
    public  ResponseEntity<Object>  returnBook(Integer bookId, Integer patronId) {
        Book book = BookRepo.findById(bookId).orElse(null);
        Patron patron = PatronRepo.findById(patronId).orElse(null);
        if (book == null || patron == null) {
            throw new ApiBaseException("You can't borrow this book for this patron because it is already borrowed to him",HttpStatus.FORBIDDEN);
       //     return ResponseObject.FAILED_RESPONSE("You can't borrow this book for this patron because it is already borrowed to him", HttpStatus.FORBIDDEN);
        }
        BorrowingRecord borrowingRecord = BorrowingRecordRepo.findByBookAndPatronAndAndReturnDateIsNull(book, patron);
        if (borrowingRecord == null) {

            return ResponseObject.FAILED_RESPONSE("There is no borrowing record for this book and patron has to be returned", HttpStatus.NOT_FOUND);
        }
        borrowingRecord.setReturnDate(LocalDate.now());
        BorrowingRecordRepo.save(borrowingRecord);
        return ResponseObject.SUCCESS_RESPONSE("Borrowing record for Book with id :" + bookId + " and  patron with id : " + patronId + " has been returned Successfully");

    }

}
