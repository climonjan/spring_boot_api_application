package com.example.bd_api.service;

import com.example.bd_api.entity.DocEntity;
import com.example.bd_api.entity.LogEntity;
import com.example.bd_api.exceptions.DocNotFound;
import com.example.bd_api.exceptions.ListIsEmpty;
import com.example.bd_api.exceptions.LogNotFound;
import com.example.bd_api.exceptions.NumberAlreadyExistException;
import com.example.bd_api.model.Log;
import com.example.bd_api.repository.DocRepo;
import com.example.bd_api.repository.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogService {

    @Autowired
    private LogRepo logRepo;
    @Autowired
    private DocRepo docRepo;

    public LogEntity createLog(DocEntity doc, NumberAlreadyExistException ex) {
        LogEntity log = new LogEntity();
        log.setDoc(docRepo.findByNumber(doc.getNumber()));
        log.setErr_msg(ex.getMessage());
        return logRepo.save(log);
    }

    public List<Log> getLogs () throws ListIsEmpty {
        List<LogEntity> logs = logRepo.findAll();
        if (logs.isEmpty()) {
            throw new ListIsEmpty("log_list is empty!");
        }
        return logs.stream().map(Log::toModel).collect(Collectors.toList());
//        return logs;
    }

    public List<Log> getLogByDoc (Integer doc_num) throws DocNotFound, LogNotFound {
        DocEntity doc = docRepo.findByNumber(doc_num);
        if (doc == null) {
            throw new DocNotFound("document not found!");
        }

        List<LogEntity> logs = logRepo.findByDoc(doc);
        if (logs.isEmpty()) {
            throw new LogNotFound("logs not found!");
        }
        return logs.stream().map(Log::toModel).collect(Collectors.toList());
//        return logs;
    }

    public String deleteAllLogs() throws ListIsEmpty {
        if (logRepo.findAll().isEmpty()) {
            throw new ListIsEmpty("log_list is empty!");
        }
        logRepo.deleteAll();
        return "deleted all logs";
    }
}
