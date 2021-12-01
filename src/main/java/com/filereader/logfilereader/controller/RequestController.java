package com.filereader.logfilereader.controller;

import com.filereader.logfilereader.model.LogDbModel;
import com.filereader.logfilereader.service.DataProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController {
    @Autowired
    DataProcessorService dataProcessorService;
    final Logger log = LoggerFactory.getLogger(RequestController.class);

    /**
     *
     * @return List of all Logs from DB
     */
    @GetMapping("/viewAllLogs")
    public List<LogDbModel> getAllLogs()
    {
        List<LogDbModel> allLogs=dataProcessorService.getAllLogs();
        log.info("Successfully retrieved "+allLogs.size()+" List of LOGS From DB");
        return allLogs;

    }
}
