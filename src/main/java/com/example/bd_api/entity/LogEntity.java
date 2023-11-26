package com.example.bd_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "errors")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "err_msg", nullable = false)
    private String err_msg;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime created_at;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "doc_num", nullable = false, referencedColumnName = "number")
    private DocEntity doc;

    public LogEntity() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getErr_msg() { return err_msg; }

    public void setErr_msg(String err_msg) { this.err_msg = err_msg; }

    public ZonedDateTime getCreated_at() { return created_at; }

    public void setCreated_at(ZonedDateTime created_at) { this.created_at = created_at; }

    public DocEntity getDoc() { return doc; }

    public void setDoc(DocEntity doc) { this.doc = doc; }
}