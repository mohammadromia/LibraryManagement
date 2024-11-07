package com.managementsystem.library.controller;

import com.managementsystem.library.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<Object> borrow(@PathVariable Integer bookId,@PathVariable Integer patronId){
      return borrowingRecordService.borrow(bookId,patronId);
    }

    @PutMapping(value = "/return/{bookId}/patron/{patronId}")
    public ResponseEntity<Object> returnBook(@PathVariable Integer bookId,@PathVariable Integer patronId){
        return  borrowingRecordService.returnBook(bookId,patronId);
    }
}