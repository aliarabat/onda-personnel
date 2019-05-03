/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.onda.personnel.model.Day;
import com.onda.personnel.model.DayDetail;
import com.onda.personnel.model.Vacation;
import com.onda.personnel.model.Work;

/**
 *
 * @author AMINE
 */
public interface DayService {

    public int createDay(Integer matricule, List<Day> days);

    public List<Day> findDaysOfWorkByEmployeeMatriculeAndYearAndMonth(Integer matricule, int year, int month);

    public Day findByEmployeeMatriculeAndDateOfTheDay(Integer matricule, Date dayDate);

    public List<Day> findByDayDate(Date dayDate);

    public List<Day> findByDateOfTheWork(Date dateOfTheDay);

    public int createVacation(Vacation vacation);

    Day setDayInfos(List<DayDetail> dayDetails, Date toDate);
}
