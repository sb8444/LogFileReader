package com.filereader.logfilereader.utils;

import com.filereader.logfilereader.constant.CommonConstant;
import com.filereader.logfilereader.model.LogDbModel;
import com.filereader.logfilereader.model.Logproperties;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

public class DataProcessorUtility {
    public static Long getEventDuration(Map<String, Long> timestampMap) {
        Long duration=0L;
        duration=timestampMap.get(CommonConstant.FINISHED)-timestampMap.get(CommonConstant.STARTED);
        return duration;
    }

    public static boolean isEventLongerThanSLA(Long eventDuration)
    {
        if (eventDuration>CommonConstant.eventSLA)
        {
            return true;
        }
        return false;
    }

    public static String getHostname(List<Logproperties> logsList, String logId) {
        String hostname=null;
        for (Logproperties lp:logsList)
        {
            if(StringUtils.hasText(lp.getId())&&lp.getId().equalsIgnoreCase(logId))
            {
                if(StringUtils.hasText(lp.getHost()))
                {
                    hostname=lp.getHost();
                }
            }

        }
        return hostname;
    }


    public static String getLogType(List<Logproperties> logsList, String logId) {
        String logType=null;
        for (Logproperties lp:logsList)
        {
            if(StringUtils.hasText(lp.getId())&&lp.getId().equalsIgnoreCase(logId))
            {
                if(StringUtils.hasText(lp.getType()))
                {
                    logType=lp.getType();
                }
            }

        }
        return logType;
    }

    public static LogDbModel setDbObjects(String logId, Long eventDuration, String hostname, Boolean alertFlag, String logType) {
        LogDbModel logDbModel=new LogDbModel();
        logDbModel.setId(logId);
        logDbModel.setType(logType);
        logDbModel.setHost(hostname);
        logDbModel.setDuration(eventDuration);
        logDbModel.setAlertFlag(alertFlag);
        return logDbModel;

    }
}
