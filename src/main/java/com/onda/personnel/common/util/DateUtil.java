/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.common.util;

import java.time.LocalDate;
import java.time.LocalTime;

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
    public static LocalDate fromStringToLocalDate(String string) {
        if (string == null) {
            return null;
        } else {
            LocalDate lt = LocalDate.parse(string);
            return lt;
        }
    }
}
