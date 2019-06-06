package com.onda.personnel.service;

import java.util.Date;
import java.util.List;

import com.onda.personnel.model.Holiday;

public interface HolidayService {

    int createHoliday(List<Holiday> holidays);

    Holiday findByStartingDateLessThanEqualAndEndingDateGreaterThanEqual(Date date1, Date date2);

    List<Holiday> findAll();

    int update(Holiday holiday);

    void delete(Long id);

    Holiday findByReference(String reference);

    long count();
}
