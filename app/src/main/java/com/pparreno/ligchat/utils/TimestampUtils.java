package com.pparreno.ligchat.utils;

import java.text.DateFormat;
import java.util.Date;

import static java.text.DateFormat.getDateTimeInstance;

public class TimestampUtils {

    public static String getTimeDate(long timestamp){
        try{
            DateFormat dateFormat = getDateTimeInstance();
            Date netDate = (new Date(timestamp));
            return dateFormat.format(netDate);
        } catch(Exception e) {
            return "date error";
        }
    }

}
