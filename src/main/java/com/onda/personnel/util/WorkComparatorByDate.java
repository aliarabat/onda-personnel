/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.util;

import com.onda.personnel.model.Work;
import java.util.Comparator;

/**
 *
 * @author hp
 */
public class WorkComparatorByDate implements Comparator<Work> {

    @Override
    public int compare(Work o1, Work o2) {
        return o1.getWorkDetail().getWorkDetailDate().compareTo(o2.getWorkDetail().getWorkDetailDate());
    }

}
