package com.onda.personnel.util;

import java.util.Comparator;

import com.onda.personnel.model.Day;

public class DayComparator implements Comparator<Day> {
    @Override
    public int compare(Day o1, Day o2) {
        return o1.getDayDate().compareTo(o2.getDayDate());
    }
}
