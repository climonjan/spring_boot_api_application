package com.example.bd_api.model;

import com.example.bd_api.entity.PosEntity;

public class Pos {
    private Long id;
    private Integer doc_num;
    private Integer pos_num;
    private String pos_name;
    public Pos() {}

    public static Pos toModel(PosEntity entity) {
        Pos model = new Pos();
        model.setId(entity.getId());
        model.setDoc_num(entity.getDoc().getNumber());
        model.setPos_num(entity.getPos_num());
        model.setPos_name(entity.getPos_name());
        return model;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getDoc_num() { return doc_num; }

    public void setDoc_num(Integer doc_num) { this.doc_num = doc_num; }

    public Integer getPos_num() { return pos_num; }

    public void setPos_num(Integer pos_num) { this.pos_num = pos_num; }

    public String getPos_name() { return pos_name; }

    public void setPos_name(String pos_name) { this.pos_name = pos_name; }
}