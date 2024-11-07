package com.managementsystem.library.controller;

import com.managementsystem.library.entities.Patron;
import com.managementsystem.library.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/patrons")
@CrossOrigin(origins = "*")
public class PatronController {

    @Autowired
    private PatronService patronService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPatron(@PathVariable int id) {
        return patronService.getPatron(id);
    }
    @PostMapping
    public ResponseEntity<Object> savePatron(@RequestBody @Valid Patron patron) {
        return patronService.save(patron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePatron(@PathVariable int id, @RequestBody @Valid Patron patron) {
        return patronService.update(id, patron);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatron(@PathVariable int id) {
        return patronService.deletePatron(id);
    }

    @GetMapping
    public ResponseEntity<Object> getAllBooks() {
        return patronService.getAllPatron();
    }
}
