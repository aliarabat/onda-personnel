package com.onda.personnel.common.util;

import com.onda.personnel.bean.Day;

import java.util.Comparator;

public class DayComparator implements Comparator<Day> {
    @Override
    public int compare(Day o1, Day o2) {
        return o1.getDayDate().compareTo(o2.getDayDate());
    }
}
