package com.filereader.logfilereader.controller;

import com.filereader.logfilereader.model.LogDbModel;
import com.filereader.logfilereader.service.DataProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController {
    @Autowired
    DataProcessorService dataProcessorService;

    @GetMapping("/viewAllLogs")
    public List<LogDbModel> getAllLogs()
    {
        return dataProcessorService.getAllLogs();

    }
}
