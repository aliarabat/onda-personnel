/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.common.util;

import com.onda.personnel.bean.Timing;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Xrio
 */
public class PeriodUtil {

    public static Timing getHoursBetween(int startingHour, int startingMinute, int endingHour, int endingMinute, boolean isNight) {
        List<Timing> model = new LinkedList<>(Arrays.asList(new Timing(1), new Timing(2), new Timing(3), new Timing(4), new Timing(5), new Timing(6), new Timing(7), new Timing(8), new Timing(9), new Timing(10), new Timing(11), new Timing(12), new Timing(13), new Timing(14), new Timing(15), new Timing(16), new Timing(17), new Timing(18), new Timing(19), new Timing(20), new Timing(21), new Timing(22), new Timing(23), new Timing(00)));
        int nightModel[] = {1, 2, 3, 4, 5, 6, 22, 23, 0};

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
            sumMinute = endingMinute - startingHour;
        } else {
            sumMinute = (60 - startingMinute) + endingMinute;
        }

        for (Timing int1 : model) {
            if (isNight) {
                if (isIn(int1.getHour(), nightModel)) {
                    sumHour++;
                }
            } else {
                sumHour++;
            }
        }
        return new Timing(sumHour, sumMinute);
    }
    
  public static void main(String[] args) {
        Timing t = getHoursBetween(17, 0, 20, 00, false) ;
        Timing t2 = getHoursBetween(17, 0, 20, 00, true) ;
        System.out.println(t);
        System.out.println(t2);

      
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
