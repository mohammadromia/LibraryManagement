package com.managementsystem.library.service;

import com.managementsystem.library.exceptions.ApiBaseException;
import com.managementsystem.library.entities.Patron;
import com.managementsystem.library.repository.PatronRepository;
import com.managementsystem.library.utils.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Cacheable(cacheNames = "ElementPatron",key = "#id")
    public ResponseEntity<Object> getPatron(Integer id) {
        return patronRepository.findById(id)
                .map(patron -> ResponseObject.SUCCESS_RESPONSE("Fetched Successfully", patron))
                .orElse(ResponseObject.FAILED_RESPONSE("Entity Not Found", HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<Object> update(Integer id, Patron patron) {
        return patronRepository.findById(id)
                .map(savedPatron -> {
                    savedPatron.setName(patron.getName());
                    savedPatron.setEmail(patron.getEmail());
                    savedPatron.setPhoneNumber(patron.getPhoneNumber());
                    return patronRepository.save(savedPatron);
                })
                .map(data -> ResponseObject.SUCCESS_RESPONSE("Fetched Successfully", null))
                .orElse(ResponseObject.FAILED_RESPONSE("Error updating the book", HttpStatus.INTERNAL_SERVER_ERROR));

    }

    public ResponseEntity<Object> deletePatron(Integer id) {

        return patronRepository.findById(id)
                .map(patron -> {
                    try {
                        patronRepository.delete(patron);
                        return ResponseObject.SUCCESS_RESPONSE("Deleted Successfully", null);
                    } catch (Exception e) {
                        throw new ApiBaseException("Error deleting the entity", HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                })
                .orElse(ResponseObject.FAILED_RESPONSE("Entity Not Found", HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<Object> save(Patron newPatron) {

        try {
            return ResponseObject.SUCCESS_RESPONSE("Added Successfully", patronRepository.save(newPatron));
        } catch (Exception e) {
            throw new ApiBaseException("Error adding the book", HttpStatus.INTERNAL_SERVER_ERROR);

//            return ResponseObject.FAILED_RESPONSE("Error adding the book", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @Cacheable(cacheNames = "AllData",key = "getAll")
    public ResponseEntity<Object> getAllPatron() {
        try {
            List<Patron> books = patronRepository.findAll();
            return ResponseObject.SUCCESS_RESPONSE("Fetched Successfully", books);
        } catch (Exception e) {
            return ResponseObject.FAILED_RESPONSE("Error fetching books", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
