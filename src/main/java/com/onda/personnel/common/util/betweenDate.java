/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.common.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AMINE
 */
public class betweenDate {
     public static List<LocalDate> between(LocalDate date1, LocalDate date2) {

        int dayS = date1.getDayOfMonth();
        System.out.println(dayS);
        int dayE = date2.getDayOfMonth();

        List<LocalDate> days = new ArrayList<>();
        for (int i = dayS; i <= dayE; i++) {
            days.add(LocalDate.of(date1.getYear(), date1.getMonth(), i));
        }
        return days;
    }

    public static List<LocalDate> withoutSunday(LocalDate date1, LocalDate date2) {
        int dayS = date1.getDayOfMonth();
        int dayE = date2.getDayOfMonth();
        List<LocalDate> daysWithoutSunday = new ArrayList<>();
        List<LocalDate> daysWithSunday = new ArrayList<>();
        List<LocalDate> days = new ArrayList<>();
        for (int i = dayS; i <= dayE; i++) {
            LocalDate date = date1.of(date1.getYear(), date1.getMonth(), i);
            if (!date.getDayOfWeek().name().equals("SUNDAY")) {
                daysWithoutSunday.add(date);
            } else {
                daysWithSunday.add(date);

            }
        }
        int x = daysWithSunday.size();
        for (int i = dayS; i <= dayE + x; i++) {
            LocalDate date = date1.of(date1.getYear(), date1.getMonth(), i);
            days.add(date);
        }

        return days;
    }
}
