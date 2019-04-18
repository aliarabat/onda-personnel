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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.util.StringConverter;
import javafx.util.converter.DateTimeStringConverter;

/**
 *
 * @author hp
 */
public class DateUtil {

    public static String toString(LocalTime lt) {
        if (lt == null) {
            return null;
        } else { //Declare LocalDate variable to receive the formatted date.
            //Converting Date to a user specific format 1 - dd-MMM-yyyy
            String pattern = "HH:mm:ss";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            /*StringConverter converter= DateTimeStringConverter.getLocalTimeConverter(formatter, null);*/
            return "";
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
}
