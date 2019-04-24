/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Xrio
 */
public class PeriodUtil {

    private int hours;

    public PeriodUtil(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public static int getHoursBetween(int startingHour, int endingHour, boolean isNight) {
        List<Int> model = new LinkedList<>(Arrays.asList(new Int(1), new Int(2), new Int(3), new Int(4), new Int(5), new Int(6), new Int(7), new Int(8), new Int(9), new Int(10), new Int(11), new Int(12), new Int(13), new Int(14), new Int(15), new Int(16), new Int(17), new Int(18), new Int(19), new Int(20), new Int(21), new Int(22), new Int(23), new Int(00)));
        int nightModel[] = {1, 2, 3, 4, 5, 6, 22, 23, 0};

        if (startingHour > endingHour) {
            model.subList(endingHour, startingHour).clear();
        } else if (startingHour < endingHour) {
            model = model.subList(startingHour, endingHour);
        } else {
            return 0;
        }

        int sum = 0;
        for (Int int1 : model) {
            if (isNight) {
                if (isIn(int1.getHour(), nightModel)) {
                    sum++;
                }
            } else {
                sum++;
            }
        }
        return sum;
    }

    public static boolean isIn(int var, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == var) {
                return true;
            }
        }
        return false;
    }
}
