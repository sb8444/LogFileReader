package com.filereader.LogFileReader.service.impl;

import com.filereader.LogFileReader.service.DataProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DataProcessorServiceImpl implements DataProcessorService {

    final Logger log = LoggerFactory.getLogger(DataProcessorServiceImpl.class);
    @Override
    public void processInputData(String data) {
        List<String> logStringList= Arrays.asList(data.split("\n"));
        log.info(logStringList.toString());
        //System.out.println(logStringList.toString());

    }
}
