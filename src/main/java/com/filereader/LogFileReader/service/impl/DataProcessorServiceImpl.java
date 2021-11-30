package com.filereader.LogFileReader.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filereader.LogFileReader.Constant.CommonConstant;
import com.filereader.LogFileReader.Utils.DataProcessorUtility;
import com.filereader.LogFileReader.model.Logproperties;
import com.filereader.LogFileReader.service.DataProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataProcessorServiceImpl implements DataProcessorService {

    final Logger log = LoggerFactory.getLogger(DataProcessorServiceImpl.class);
    @Override
    public void processInputData(String data) {
        List<String> logStringList= Arrays.asList(data.split("\n"));
        log.info(logStringList.toString());
        List<Logproperties> logsList= new ArrayList<Logproperties>();
        logsList=getLogList(logStringList);
        log.info(logsList.toString());
        if(!logsList.isEmpty())
        {
            processLogsList(logsList);
        }else
        {
            log.error("Data Processing failed due to non");
        }

    }

    private void processLogsList(List<Logproperties> logsList) {
        List<String> distinctLogIdList=logsList.stream()
                .filter(l-> StringUtils.hasText(l.getId()))
                .map(Logproperties::getId)
                .distinct()
                .collect(Collectors.toList());

        log.info(distinctLogIdList.toString());

        for(String logId:distinctLogIdList)
        {
            calculateAndInsertData(logsList,logId);
        }


    }

    private void calculateAndInsertData(List<Logproperties> logsList, String logId) {
        Map<String,Long> timestampMap=new HashMap<String,Long>();
        for (Logproperties lp:logsList)
        {
            if(StringUtils.hasText(lp.getId())&&lp.getId().equalsIgnoreCase(logId))
            {
                timestampMap.put(lp.getState(), lp.getTimestamp());
            }
        }
        Long eventDuration= DataProcessorUtility.getEventDuration(timestampMap);

        log.info("MV: "+timestampMap.toString());
        log.info("Event Id: "+logId+" | Duration: "+eventDuration+" |EventDurationLongerFlag: "+DataProcessorUtility.isEventLongerThanSLA(eventDuration));

    }



    private List<Logproperties> getLogList(List<String> logStringList) {
        ObjectMapper mapper = new ObjectMapper();
        List<Logproperties> logsList= new ArrayList<Logproperties>();
        for(String eachLog:logStringList)
        {
            try
            {
                Logproperties readValue = mapper.readValue(eachLog, Logproperties.class);
                logsList.add(readValue);
            }
            catch(JsonProcessingException je)
            {
                log.error("Json Parsing failed due to "+je.getMessage());
            }

        }
        return logsList;
    }
}
