package com.example.bd_api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "documents")
public class DocEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number", unique = true, nullable = false)
    private Integer number;

    @Column(name = "hash_sum", nullable = false)
    private Integer hash_sum;

    @Column(name = "note")
    private String note;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private ZonedDateTime updated_at;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doc")
    private List<PosEntity> positions;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doc")
    private List<LogEntity> logs;

    public DocEntity() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getNumber() { return number; }

    public void setNumber(Integer number) { this.number = number; }

    public Integer getHash_sum() { return hash_sum; }

    public void setHash_sum(Integer hash_sum) { this.hash_sum = hash_sum; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public ZonedDateTime getCreated_at() { return created_at; }

    public void setCreated_at(ZonedDateTime created_at) { this.created_at = created_at; }

    public ZonedDateTime getUpdated_at() { return updated_at; }

    public void setUpdated_at(ZonedDateTime updated_at) { this.updated_at = updated_at; }

    public List<PosEntity> getPositions() { return positions; }

    public void setPositions(List<PosEntity> positions) { this.positions = positions; }

    public List<LogEntity> getLogs() { return logs; }

    public void setLogs(List<LogEntity> logs) { this.logs = logs; }
}