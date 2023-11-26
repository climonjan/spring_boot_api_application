package com.example.bd_api.service;

import com.example.bd_api.entity.DocEntity;
import com.example.bd_api.entity.PosEntity;
import com.example.bd_api.exceptions.DocNotFound;
import com.example.bd_api.exceptions.InvalidNum;
import com.example.bd_api.exceptions.PosAlreadyExists;
import com.example.bd_api.exceptions.PosNotFound;
import com.example.bd_api.model.Pos;
import com.example.bd_api.repository.DocRepo;
import com.example.bd_api.repository.PosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PosService {

    @Autowired
    private PosRepo posRepo;
    @Autowired
    private DocRepo docRepo;

    public void createPos(PosEntity pos, Integer doc_num) throws InvalidNum, DocNotFound, PosAlreadyExists {
        Integer pos_num = pos.getPos_num();
        if (pos_num < 1) {
            throw new InvalidNum("invalid position number!");
        }

        DocEntity doc = docRepo.findByNumber(doc_num);
        if (doc == null) {
            throw new DocNotFound("document with this number not found!");
        }

        for (var el: doc.getPositions()) {
            if (Objects.equals(el.getPos_num(), pos.getPos_num())) {
                throw new PosAlreadyExists("position with this number already exists!");
            }
        }
        pos.setDoc(doc);
        pos.setHash_sum(pos.hashCode());
        posRepo.save(pos);
        doc.setHash_sum(doc.getPositions().hashCode());
        docRepo.save(doc);
    }

    public List<Pos> getPosByDoc(Integer doc_num) throws DocNotFound, PosNotFound {
        DocEntity doc = docRepo.findByNumber(doc_num);
        if (doc == null) {
            throw new DocNotFound("document not found!");
        }

        List<PosEntity> positions = posRepo.findByDoc(doc);
        if (positions == null) {
            throw new PosNotFound("positions not found!");
        }
        return positions.stream().map(Pos::toModel).collect(Collectors.toList());
    }

    public Pos getPosByDocAndNum(Integer doc_num, Integer pos_num) throws DocNotFound, PosNotFound {
        DocEntity doc = docRepo.findByNumber(doc_num);
        if (doc == null) {
            throw new DocNotFound("document not found!");
        }

        PosEntity pos = posRepo.findByDocAndPosNum(doc, pos_num);
        if (pos == null) {
            throw new PosNotFound("position not found!");
        }
        return Pos.toModel(pos);
    }



    public void updatePos(PosEntity pos, Integer doc_num) throws InvalidNum, DocNotFound, PosNotFound {
        Integer pos_num = pos.getPos_num();
        if (pos_num < 1) {
            throw new InvalidNum("invalid position number!");
        }

        DocEntity doc = docRepo.findByNumber(doc_num);
        if (doc == null) {
            throw new DocNotFound("document with this number not found!");
        }

        boolean f = false;
        for (var el: doc.getPositions()) {
            if (Objects.equals(el.getPos_num(), pos_num)) {
                f = true;
                break;
            }
        }
        if (!f) {
            throw new PosNotFound("position not found!");
        }

        PosEntity updatePos = posRepo.findByDocAndPosNum(doc, pos_num);
        updatePos.setPos_num(pos_num);
        updatePos.setPos_name(pos.getPos_name());
        updatePos.setHash_sum(updatePos.hashCode());
        posRepo.save(updatePos);
        doc.setHash_sum(doc.getPositions().hashCode());
        docRepo.save(doc);
    }

    public void deleteOnePos(Integer doc_num, Integer pos_num) throws DocNotFound, PosNotFound {
        DocEntity doc = docRepo.findByNumber(doc_num);
        if (doc == null) {
            throw new DocNotFound("document with this number not found!");
        }

        boolean f = false;
        for (var el: doc.getPositions()) {
            if (Objects.equals(el.getPos_num(), pos_num)) {
                f = true;
                break;
            }
        }
        if (!f) {
            throw new PosNotFound("position not found!");
        }

        posRepo.deleteByDocAndPosNum(doc, pos_num);
        doc.setHash_sum(doc.getPositions().hashCode());
        docRepo.save(doc);
    }

    public void deletePosByDoc(Integer doc_num) throws DocNotFound, PosNotFound {
        DocEntity doc = docRepo.findByNumber(doc_num);
        if (doc == null) {
            throw new DocNotFound("document not found!");
        }
        if (posRepo.findByDoc(doc).isEmpty()) {
            throw new PosNotFound("positions not found!");
        }
        posRepo.deleteAllByDoc(doc);
    }

    public void deleteAllPos() throws PosNotFound {
        if (posRepo.findAll().isEmpty()) {
            throw new PosNotFound("positions not found!");
        }
        posRepo.deleteAll();
        List<DocEntity> doc = docRepo.findAll();
        for (var el: doc) {
            el.setHash_sum(el.getPositions().hashCode());
            docRepo.save(el);
        }
    }
}
