package com.filereader.logfilereader.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filereader.logfilereader.utils.DataProcessorUtility;
import com.filereader.logfilereader.model.LogDbModel;
import com.filereader.logfilereader.model.Logproperties;
import com.filereader.logfilereader.repository.DataRepository;
import com.filereader.logfilereader.service.DataProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataProcessorServiceImpl implements DataProcessorService {

    @Autowired
    DataRepository dataRepository;

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

        log.info("Unique Event Names: "+distinctLogIdList.toString());

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
        String hostname=DataProcessorUtility.getHostname(logsList,logId);
        String logType=DataProcessorUtility.getLogType(logsList,logId);
        Boolean alertFlag=DataProcessorUtility.isEventLongerThanSLA(eventDuration);
        LogDbModel dbobj=DataProcessorUtility.setDbObjects(logId,eventDuration,hostname,alertFlag,logType);

        log.info("Event Id: "+logId+" | Duration: "+eventDuration+"ms |EventDurationLongerFlag: "+alertFlag+" |Hostname: "+hostname+" |LogType: "+logType);
        dataRepository.save(dbobj);
        log.info("Event Id: "+logId+" Insertion Successful");

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

    @Override
    public List<LogDbModel> getAllLogs() {
        List<LogDbModel> logDbModelList = new ArrayList<LogDbModel>();
        dataRepository.findAll().forEach(logDbModelList::add);
        log.info("Retrieved DB List"+logDbModelList);
        return logDbModelList;
    }
}
