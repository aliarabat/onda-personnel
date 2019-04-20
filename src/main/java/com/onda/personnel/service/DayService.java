/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.bean.Day;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface DayService {

    public int createDay(Integer matricule, String workDetailDate,List<Day> days);
}
