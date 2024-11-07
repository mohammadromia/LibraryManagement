package com.managementsystem.library.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "borrowing_records")
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patron_id", referencedColumnName = "id")
    private Patron patron;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="Borrowing_Date")
    private LocalDate borrowingDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Return_Date")
    private LocalDate returnDate;

}