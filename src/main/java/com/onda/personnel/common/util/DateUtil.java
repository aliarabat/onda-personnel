/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.common.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hp
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
}
