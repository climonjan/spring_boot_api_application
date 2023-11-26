package com.example.bd_api.model;

import com.example.bd_api.entity.LogEntity;

import java.time.ZonedDateTime;

public class Log {
    private Long id;
    private int doc_num;
    private String err_msg;
    private ZonedDateTime created_at;
    public Log() {}

    public static Log toModel(LogEntity entity) {
        Log model = new Log();
        model.setId(entity.getId());
        model.setDoc_num(entity.getDoc().getNumber());
        model.setErr_msg(entity.getErr_msg());
        model.setCreated_at(entity.getCreated_at());
        return model;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public int getDoc_num() { return doc_num; }

    public void setDoc_num(int doc_num) { this.doc_num = doc_num; }

    public String getErr_msg() { return err_msg; }

    public void setErr_msg(String err_msg) { this.err_msg = err_msg; }

    public ZonedDateTime getCreated_at() { return created_at; }

    public void setCreated_at(ZonedDateTime created_at) { this.created_at = created_at; }
}
