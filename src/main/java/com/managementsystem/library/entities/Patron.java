package com.managementsystem.library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "patrons")
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name must not be null")
    @Column(name = "Name", nullable = false)
    private String name;

    @NotBlank(message = "email must not be null")
    @Email
    @Column(name = "Email", nullable = false)
    private String email;

    @NotBlank(message = "phone number must not be null")
    @Column(name = "Phone_Number", nullable = false)
    private String phoneNumber;


}