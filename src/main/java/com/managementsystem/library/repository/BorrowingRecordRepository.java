package com.managementsystem.library.repository;

import com.managementsystem.library.entities.Book;
import com.managementsystem.library.entities.BorrowingRecord;
import com.managementsystem.library.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {
    BorrowingRecord findByBookAndPatronAndAndReturnDateIsNull(Book book, Patron patron);
}