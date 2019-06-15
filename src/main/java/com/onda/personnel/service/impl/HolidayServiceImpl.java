package com.onda.personnel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.onda.personnel.dao.HolidayDao;
import com.onda.personnel.model.Holiday;
import com.onda.personnel.service.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayDao holidayDao;

    @Override
    public int createHoliday(List<Holiday> holidays) {

        if (holidays == null || holidays.isEmpty()) {
            return -1;
        } else {
            holidays.forEach(hol -> {
                Holiday holidayCheck = holidayDao.findByReference(hol.getReference());
                if (holidayCheck == null) {
                    holidayDao.save(hol);
                }
            });
            return 1;
        }
    }

    @Override
    public Holiday findByStartingDateLessThanEqualAndEndingDateGreaterThanEqual(Date date1, Date date2) {
        return holidayDao.findTopByStartingDateLessThanEqualAndEndingDateGreaterThanEqualOrderByIdAsc(date1, date2);
    }

    @Override
    public List<Holiday> findAll() {
        return holidayDao.findAll(Sort.by("startingDate"));
    }

    @Override
    public int update(Holiday holiday) {
        Holiday holCheck = holidayDao.getOne(holiday.getId());
        if (holCheck == null) {
            return -1;
        } else {
            holCheck.setReference(holiday.getReference());
            holCheck.setStartingDate(holiday.getStartingDate());
            holCheck.setEndingDate(holiday.getEndingDate());
            holidayDao.save(holCheck);
            return 1;
        }
    }

    @Override
    public void delete(Long id) {
        holidayDao.deleteById(id);
    }

    @Override
    public Holiday findByReference(String reference) {
        return holidayDao.findByReference(reference);
    }

    @Override
    public long count() {
        return holidayDao.count();
    }

    public HolidayDao getHolidayDao() {
        return holidayDao;
    }

    public void setHolidayDao(HolidayDao holidayDao) {
        this.holidayDao = holidayDao;
    }

}
