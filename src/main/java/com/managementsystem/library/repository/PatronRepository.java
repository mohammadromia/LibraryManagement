package com.managementsystem.library.repository;

import com.managementsystem.library.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatronRepository extends JpaRepository<Patron, Integer> {
}