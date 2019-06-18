package com.onda.personnel.util;

import java.util.Comparator;

import com.onda.personnel.model.Work;

public class WorkComparator implements Comparator<Work> {

    @Override
    public int compare(Work o1, Work o2) {
        return Integer.compare(o1.getWorkDetail().getHjf().getHour(), o2.getWorkDetail().getHjf().getHour());
    }

}
