package com.onda.personnel.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onda.personnel.model.Holiday;

public interface HolidayDao extends JpaRepository<Holiday, Long> {

    Holiday findByReference(String reference);

    Holiday findByStartingDateLessThanEqualAndEndingDateGreaterThanEqual(Date date1, Date date2);
}
