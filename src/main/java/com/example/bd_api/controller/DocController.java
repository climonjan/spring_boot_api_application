package com.example.bd_api.controller;

import com.example.bd_api.exceptions.DocNotFound;
import com.example.bd_api.exceptions.ListIsEmpty;
import com.example.bd_api.exceptions.NumberAlreadyExistException;
import com.example.bd_api.entity.DocEntity;
import com.example.bd_api.service.DocService;
import com.example.bd_api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/docs")
public class DocController {

    @Autowired
    private DocService docService;
    @Autowired
    private LogService logService;

    @PostMapping("/add")
    public ResponseEntity createDoc(@RequestBody DocEntity doc) {
        try {
            docService.createDoc(doc);
            return ResponseEntity.ok("document created!");
        } catch (NumberAlreadyExistException ex) {
            logService.createLog(doc, ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @GetMapping("/")
    public ResponseEntity getDocs() {
        try {
            return ResponseEntity.ok(docService.getDocs());
        } catch (ListIsEmpty ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @GetMapping
    public ResponseEntity getDocByNumber(@RequestParam Integer number) {
        try {
            return ResponseEntity.ok(docService.getDocByNum(number));
        } catch (DocNotFound ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @PutMapping("/update/{number}")
    public ResponseEntity updateDoc(@RequestBody DocEntity doc,
                                    @PathVariable Integer number) {
        try {
            docService.updateDoc(doc, number);
            return ResponseEntity.ok("document updated!");
        } catch (DocNotFound ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @DeleteMapping("delete/{number}")
    public ResponseEntity deleteDoc(@PathVariable Integer number) {
        try {
            return ResponseEntity.ok(docService.deleteOneDoc(number));
        } catch (DocNotFound ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity deleteDocs() {
        try {
            return ResponseEntity.ok(docService.deleteAllDoc());
        } catch (DocNotFound ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }
}