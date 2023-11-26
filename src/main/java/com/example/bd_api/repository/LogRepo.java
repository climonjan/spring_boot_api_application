package com.example.bd_api.repository;

import com.example.bd_api.entity.DocEntity;
import com.example.bd_api.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepo extends JpaRepository<LogEntity, Long> {

    List<LogEntity> findByDoc(DocEntity doc);
}