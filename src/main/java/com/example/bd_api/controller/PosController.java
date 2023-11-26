package com.example.bd_api.controller;

import com.example.bd_api.entity.PosEntity;
import com.example.bd_api.exceptions.*;
import com.example.bd_api.service.PosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/positions")
public class PosController {

    @Autowired
    private PosService posService;

    @PostMapping("/add")
    public ResponseEntity createPos(@RequestBody PosEntity pos,
                                    @RequestParam Integer doc_num) {
        try {
            posService.createPos(pos, doc_num);
            return ResponseEntity.ok("position created!");
        } catch (InvalidNum | DocNotFound | PosAlreadyExists ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @GetMapping("/{doc_num}/")
    public ResponseEntity getPosByDoc(@PathVariable Integer doc_num) {
        try {
            return ResponseEntity.ok(posService.getPosByDoc(doc_num));
        } catch (DocNotFound | PosNotFound ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @GetMapping("/{doc_num}/{pos_num}")
    public ResponseEntity getPosByDocAndNum(@PathVariable Integer doc_num,
                                            @PathVariable Integer pos_num) {
        try {
            return ResponseEntity.ok(posService.getPosByDocAndNum(doc_num, pos_num));
        } catch (DocNotFound | PosNotFound ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @PutMapping("/update/{doc_num}")
    public ResponseEntity updatePos(@RequestBody PosEntity pos,
                                    @PathVariable Integer doc_num) {
        try {
            posService.updatePos(pos, doc_num);
            return ResponseEntity.ok("position updated!");
        } catch (InvalidNum | DocNotFound | PosNotFound ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @DeleteMapping("/delete/{doc_num}/{pos_num}")
    public ResponseEntity deletePos(@PathVariable Integer doc_num,
                                    @PathVariable Integer pos_num) {
        try {
            posService.deleteOnePos(doc_num, pos_num);
            return ResponseEntity.ok("deleted position num " + pos_num + " from document num " + doc_num);
        } catch (DocNotFound | PosNotFound ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @DeleteMapping("delete/{doc_num}/")
    public ResponseEntity deletePosByDoc(@PathVariable Integer doc_num) {
        try {
            posService.deletePosByDoc(doc_num);
            return ResponseEntity.ok("deleted all positions in document number " + doc_num);
        } catch (DocNotFound | PosNotFound ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity deleteAllPos() {
        try {
            posService.deleteAllPos();
            return ResponseEntity.ok("deleted all positions");
        } catch (PosNotFound ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("unknown error!");
        }
    }
}