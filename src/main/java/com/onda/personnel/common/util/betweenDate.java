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


    public static List<LocalDate> between(LocalDate startdate, LocalDate enddate) {

        List<LocalDate> dates = new ArrayList<>();

        while (!startdate.equals(enddate.plusDays(1))) {
            dates.add(startdate);
            startdate = startdate.plusDays(1);
        }
        return dates;
    }

    public static List<LocalDate> withoutSunday(LocalDate startdate, LocalDate enddate) {
        List<LocalDate> daysWithoutSunday = new ArrayList<>();
        List<LocalDate> daysWithSunday = Sundays(startdate, enddate);
        int x = daysWithSunday.size();

        do {
            if (!startdate.getDayOfWeek().name().equals("SUNDAY")) {
                daysWithoutSunday.add(startdate);
            }
            startdate = startdate.plusDays(1);

        } while (!startdate.equals(enddate.plusDays(1+x)));

        return daysWithoutSunday;

    }

    public static List<LocalDate> Sundays(LocalDate startdate, LocalDate enddate) {
        List<LocalDate> daysWithSunday = new ArrayList<>();
        do {
            if (startdate.getDayOfWeek().name().equals("SUNDAY")) {
                daysWithSunday.add(startdate);
            }
            startdate = startdate.plusDays(1);

        } while (!startdate.equals(enddate.plusDays(1)));

        return daysWithSunday;

    }


    public static void main(String[] args) {

        List<LocalDate> days = withoutSunday(LocalDate.of(2019, 05, 10), LocalDate.of(2019, 05, 20));
        for (LocalDate day : days) {

            // LocalDate LD = LocalDate.of(2019, 05, 10);
            System.out.println(day);

        }
    }

}
