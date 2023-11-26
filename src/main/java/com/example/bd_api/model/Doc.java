package com.example.bd_api.model;

import com.example.bd_api.entity.DocEntity;

import java.time.ZonedDateTime;

public class Doc {
    private Long id;
    private Integer number;
    private String note;
    private ZonedDateTime created_at;
    private ZonedDateTime updated_at;
    public Doc() {}

    public static Doc toModel(DocEntity entity) {
        Doc model = new Doc();
        model.setId(entity.getId());
        model.setNumber(entity.getNumber());
        model.setNote(entity.getNote());
        model.setCreated_at(entity.getCreated_at());
        model.setUpdated_at(entity.getUpdated_at());
        return model;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getNumber() { return number; }

    public void setNumber(Integer number) { this.number = number; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public ZonedDateTime getCreated_at() { return created_at; }

    public void setCreated_at(ZonedDateTime created_at) { this.created_at = created_at; }

    public ZonedDateTime getUpdated_at() { return updated_at; }

    public void setUpdated_at(ZonedDateTime updated_at) { this.updated_at = updated_at; }
}