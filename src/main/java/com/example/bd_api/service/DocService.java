package com.example.bd_api.service;

import com.example.bd_api.exceptions.DocNotFound;
import com.example.bd_api.exceptions.InvalidNum;
import com.example.bd_api.exceptions.ListIsEmpty;
import com.example.bd_api.exceptions.NumberAlreadyExistException;
import com.example.bd_api.entity.DocEntity;
import com.example.bd_api.model.Doc;
import com.example.bd_api.repository.DocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocService {

    @Autowired
    private DocRepo docRepo;
    public DocEntity createDoc (DocEntity doc) throws InvalidNum, NumberAlreadyExistException {
        if (doc.getNumber() < 1) {
            throw new InvalidNum("invalid documnet number!");
        }
        if (docRepo.findByNumber(doc.getNumber()) != null) {
            throw new NumberAlreadyExistException("document with this number already exists!");
        }
        doc.setHash_sum(doc.hashCode());
        return docRepo.save(doc);
    }

    public List<Doc> getDocs () throws ListIsEmpty {
        List<DocEntity> docs = docRepo.findAll();
        if (docs.isEmpty()) {
            throw new ListIsEmpty("doc_list is empty!");
        }
        return docs.stream().map(Doc::toModel).collect(Collectors.toList());
    }

    public Doc getDocByNum (Integer number) throws DocNotFound {
        DocEntity doc = docRepo.findByNumber(number);
        if (doc == null) {
            throw new DocNotFound("document not found!");
        }
        return Doc.toModel(doc);
    }

    public DocEntity updateDoc (DocEntity doc, Integer number) throws DocNotFound {
        DocEntity updateDoc = docRepo.findByNumber(number);
        if (updateDoc == null) {
            throw new DocNotFound("document not found!");
        }
        updateDoc.setNote(doc.getNote());
        updateDoc.setHash_sum(updateDoc.getPositions().hashCode());
        return docRepo.save(updateDoc);
    }

    public String deleteOneDoc(Integer number) throws DocNotFound {
        DocEntity doc = docRepo.findByNumber(number);
        if (doc == null) {
            throw new DocNotFound("document not found!");
        }
        docRepo.deleteByNumber(number);
        return "deleted document number " + number;
    }

    public String deleteAllDoc() throws DocNotFound {
        if (docRepo.findAll().isEmpty()) {
            throw new DocNotFound("doc_list is empty!");
        }
        docRepo.deleteAll();
        return "deleted all documents";
    }
}