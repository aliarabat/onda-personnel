/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.onda.personnel.model.Timing;

/**
 *
 * @author Xrio
 */
public class PeriodUtil {

    public static Timing getHoursBetween(int startingHour, int startingMinute, int endingHour, int endingMinute, boolean isNight) {
        List<Timing> model = new LinkedList<>(Arrays.asList(new Timing(1), new Timing(2), new Timing(3), new Timing(4), new Timing(5), new Timing(6), new Timing(7), new Timing(8), new Timing(9), new Timing(10), new Timing(11), new Timing(12), new Timing(13), new Timing(14), new Timing(15), new Timing(16), new Timing(17), new Timing(18), new Timing(19), new Timing(20), new Timing(21), new Timing(22), new Timing(23), new Timing(00)));
        int nightModel[] = {1, 2, 3, 4, 5, 6, 22, 23, 00};

        if (startingHour > endingHour) {
            model.subList(endingHour, startingHour).clear();
        } else if (startingHour < endingHour) {
            model = model.subList(startingHour, endingHour);
        } else {
            return new Timing(0, 0);
        }

        int sumHour = 0;
        int sumMinute = 0;

        if (startingMinute < endingMinute) {
            sumMinute = endingMinute - startingMinute;
        } else if (startingMinute > endingMinute) {
            sumMinute = (60 - startingMinute) + endingMinute;
        }

        for (Timing int1 : model) {
            if (isNight) {
                sumMinute = 0;
                if (isIn(int1.getHour(), nightModel)) {
                    sumHour++;
                    sumMinute = 0;

                }
            } else if (startingHour == 15 && startingMinute == 30 && endingHour == 00 && endingMinute == 00) {
                sumHour = 8;
                sumMinute = 30;

            } else if (startingHour == 14 && startingMinute == 30 && endingHour == 00 && endingMinute == 00) {
                sumHour = 9;
                sumMinute = 30;

            } else {
                sumHour++;

            }
        }
        return new Timing(sumHour, sumMinute);
    }

    public static boolean isIn(int var, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == var) {
                return true;
            }
        }
        return false;
    }

    public static void minutesToHour(int hoursHnWorked, int minutesHnWorked, int hoursHeWorked, int minutesHeWorked) {
        if (minutesHnWorked >= 60) {
            hoursHnWorked++;
            minutesHnWorked -= 60;
        }
        if (minutesHeWorked >= 60) {
            hoursHeWorked++;
            minutesHeWorked = minutesHeWorked - 60;
        }
    }
}
