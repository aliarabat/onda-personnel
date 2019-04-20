/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author AMINE
 */
public class DateUtil {

    public static String toString(LocalTime lt) {
        if (lt == null) {
            return null;
        } else {
            return lt.toString();
        }
    }

    public static LocalTime fromString(String string) {
        if (string == null) {
            return null;
        } else {
            LocalTime lt = LocalTime.parse(string);
            return lt;
        }
    }

    public static Date toDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        } else {
            Instant instant = Instant.from(localDate
                    .atStartOfDay(ZoneId.of("GMT")));
            return Date.from(instant);
        }
    }
    
    public static LocalDate fromDate(java.util.Date date) {
        return asLocalDate(date, ZoneId.systemDefault());
    }
    public static LocalDate asLocalDate(java.util.Date date, ZoneId zone) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Date)
            return ((java.sql.Date) date).toLocalDate();
        else
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
    }
    
    public static String toString(LocalDate localDate){
        return localDate.toString();
    }
    
    public static LocalDate fromDateString(String date){
        return LocalDate.parse(date);
    }
}
