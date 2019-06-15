/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.util.Date;
import java.util.List;

import com.onda.personnel.model.Day;
import com.onda.personnel.model.DayDetail;
import com.onda.personnel.model.Vacation;

/**
 *
 * @author AMINE
 */
public interface DayService {

    int createDay(Integer matricule, List<Day> days);

    List<Day> findDaysOfWorkByEmployeeMatriculeAndYearAndMonth(Integer matricule, int year, int month);

    Day findByEmployeeMatriculeAndDateOfTheDay(Integer matricule, Date dayDate);

    List<Day> findByDayDate(Date dayDate);

    List<Day> findByDateOfTheWork(Date dateOfTheDay);

    int createVacation(Vacation vacation, Integer matricule);

    int updateVacation(Vacation vacation, Integer matricule);

    Day setDayInfos(List<DayDetail> dayDetails, Date toDate);

    List<Day> findByVacationId(Long id);
}
