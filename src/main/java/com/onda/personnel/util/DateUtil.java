/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.util;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
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
        if (string == null || string.equals("")) {
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
        if (date == null) {
            return null;
        }

        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else {
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
        }
    }

    public static String toString(LocalDate localDate) {
        if (localDate == null) {
            return null;
        } else {
return localDate.toString();
        }
        
    }

    public static LocalDate fromStringToLocalDate(String date) {
        if (date == null || date.equals("")) {
            return null;
        } else {
            return LocalDate.parse(date);
        }
    }

    public static LocalDate getFirstMonday(DayOfWeek dayOfWeek) {
        LocalDate d = LocalDate.now();
        LocalDate d2 = d.with(TemporalAdjusters.dayOfWeekInMonth(1, dayOfWeek));
        return d2;
    }

    public static LocalDate getFirstDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        System.out.println(cal.getTime());
        return DateUtil.fromDate(cal.getTime());
    }
    
    public static Date getFirstDayOfMonth(){
        LocalDate localDate=LocalDate.of(getFirstDayOfWeek().getYear(), getFirstDayOfWeek().getMonth(),1);
        return toDate(localDate);
    }

    //for tests

    /*public static LocalDate getFirstDayOfMonth(){
        LocalDate localDate=LocalDate.of(getFirstDayOfWeek().getYear(), Month.APRIL,1);
        return localDate;
    }*/


    public static int lenghtOfMonth(LocalDate date){
        return date.lengthOfMonth();
    }
}
