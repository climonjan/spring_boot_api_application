package com.example.bd_api.repository;

import com.example.bd_api.entity.DocEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface DocRepo extends JpaRepository<DocEntity, Long> {

    DocEntity findByNumber(Integer number);
    @Modifying
    @Transactional
    void deleteByNumber(Integer number);
}