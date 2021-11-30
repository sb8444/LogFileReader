package com.filereader.LogFileReader.Utils;

import com.filereader.LogFileReader.Constant.CommonConstant;

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
}
