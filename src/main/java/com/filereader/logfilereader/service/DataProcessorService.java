package com.filereader.logfilereader.service;

import com.filereader.logfilereader.model.LogDbModel;

import java.util.List;

public interface DataProcessorService {
    public void processInputData(String data);

    public List<LogDbModel> getAllLogs();
}
