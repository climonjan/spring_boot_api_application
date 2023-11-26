package com.example.bd_api.controller;

import com.example.bd_api.exceptions.*;
import com.example.bd_api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/")
    public ResponseEntity getLogs() {
        try {
            return ResponseEntity.ok(logService.getLogs());
        } catch (ListIsEmpty ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @GetMapping
    public ResponseEntity getLogByDocNum(@RequestParam Integer doc_num) {
        try {
            return ResponseEntity.ok(logService.getLogByDoc(doc_num));
        } catch (DocNotFound | LogNotFound ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity deleteLogs() {
        try {
            return ResponseEntity.ok(logService.deleteAllLogs());
        } catch (ListIsEmpty ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }
}
