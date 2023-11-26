package com.example.bd_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "positions")
public class PosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pos_num", unique = true, nullable = false)
    private Integer pos_num;

    @Column(name = "pos_name", nullable = false)
    private String pos_name;

    @Column(name = "hash_sum", nullable = false)
    private Integer hash_sum;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "doc_num", nullable = false, referencedColumnName = "number")
    private DocEntity doc;

    public PosEntity() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getPos_num() { return pos_num; }

    public void setPos_num(Integer pos_num) { this.pos_num = pos_num; }

    public String getPos_name() { return pos_name; }

    public void setPos_name(String pos_name) { this.pos_name = pos_name; }

    public Integer getHash_sum() { return hash_sum; }

    public void setHash_sum(Integer hash_sum) { this.hash_sum = hash_sum; }

    public DocEntity getDoc() { return doc; }

    public void setDoc(DocEntity doc) { this.doc = doc; }
}
