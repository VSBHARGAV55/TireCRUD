package com.example.TireCRUD.controller;

import com.example.TireCRUD.model.Tire;
import com.example.TireCRUD.repo.TireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TireController {

    @Autowired
    TireRepository tireRepository;

    @GetMapping("/getAllTires")
    public ResponseEntity<List<Tire>> getAllTires() {
        try {
            List<Tire> tireList = new ArrayList<>();
            tireRepository.findAll().forEach(tireList::add);

            if (tireList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tireList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTireById/{id}")
    public ResponseEntity<Tire> getTireById(@PathVariable Long id) {
        Optional<Tire>  TireObj = tireRepository.findById(id);
        if (TireObj.isPresent()) {
            return new ResponseEntity<>(TireObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addTire")
    public ResponseEntity<Tire> addTire(@RequestBody Tire tire) {
        try {
            Tire tireObj = tireRepository.save(tire);
            return new ResponseEntity<>(tireObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateTire/{id}")
    public ResponseEntity<Tire> updateTire(@PathVariable Long id, @RequestBody Tire tire) {
        try {
            Optional<Tire> TireData = tireRepository.findById(id);
            if (TireData.isPresent()) {
                Tire updatedTireData = TireData.get();
                updatedTireData.setType(tire.getType());
                updatedTireData.setName(tire.getName());

                Tire tireObj = tireRepository.save(updatedTireData);
                return new ResponseEntity<>(tireObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteTyreById/{id}")
    public ResponseEntity<HttpStatus> deleteTyre(@PathVariable Long id) {
        try {
            tireRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllTires")
    public ResponseEntity<HttpStatus> deleteAllTires() {
        try {
            tireRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
