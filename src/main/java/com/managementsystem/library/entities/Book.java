package com.managementsystem.library.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title must not be null")
    @Column(name ="Title",nullable = false)
    private String title;

    @NotBlank(message = "Author must not be null")
    @Column(name = "Author", nullable = false)
    private String author;

    @Min(value = 1800,message = "publicationYear must be more than 1800")
    @Column(name = "Publication_Year", nullable = false)
    private Integer publicationYear;

    @Length(message = "ISBN cannot be more than 14 character",max = 14)
    @Column(name = "ISBN", nullable = false, unique = true, length = 14)
    private String isbn;

}