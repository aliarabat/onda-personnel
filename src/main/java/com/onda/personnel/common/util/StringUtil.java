/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.common.util;

import com.onda.personnel.bean.Timing;

/**
 *
 * @author Xrio
 */
public class StringUtil {

    public static boolean isNull(String string) {
        return ObjectUtil.isNull(string);
    }

    public static boolean isEmpty(String string) {
        return string.isEmpty() || string.equals(" ");
    }

    public static String format(double d) {
        try {
            return String.valueOf(d);
        } catch (Exception e) {
            System.err.println("Error while converting of String to Double");
            return "Error while converting";
        }
    }

    public static String format(int i) {
        try {
            return String.valueOf(i);
        } catch (Exception e) {
            System.err.println("Error while converting of String to int");
            return "Error while converting";
        }
    }


     
    public static String format(Timing timing) {
        try {
            return timing.getHour()+":"+timing.getMinute();
        } catch (Exception e) {
            System.err.println("Error while converting of String to Timing");
            return "Error while converting";
        }
    }
    
    public static Timing format(String string) {
        try {
            String[] pieces = string.split(":");
            String hours = pieces[0];
            String minutes = pieces[1];
            return new Timing(Integer.valueOf(hours), Integer.valueOf(minutes));
        } catch (Exception e) {
            System.err.println("Error while converting of Timing to String");
            return null;
        }
    }
}
