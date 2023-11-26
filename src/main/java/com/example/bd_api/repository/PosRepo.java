package com.example.bd_api.repository;

import com.example.bd_api.entity.DocEntity;
import com.example.bd_api.entity.PosEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosRepo extends JpaRepository<PosEntity, Long> {

    @Query("SELECT p FROM PosEntity p WHERE p.doc = :doc AND p.pos_num = :pos_num")
    PosEntity findByDocAndPosNum (DocEntity doc, Integer pos_num);

    List<PosEntity> findByDoc (DocEntity doc);

    @Modifying
    @Transactional
    void deleteAllByDoc (DocEntity doc);

    @Modifying
    @Transactional
    @Query("DELETE FROM PosEntity p WHERE p.doc = :doc AND p.pos_num = :pos_num")
    void deleteByDocAndPosNum (DocEntity doc, Integer pos_num);
}