/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.model.DayDetail;
import com.onda.personnel.model.Mission;
import com.onda.personnel.model.Replacement;
import com.onda.personnel.model.Skip;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface DayDetailService {

    DayDetail createDayDetail(DayDetail dayDetail);

    int updateDayDetailMission(Integer matricule, String wordingDetail, Mission mission);

    int updateDayDetailSkip(Integer matricule, String wordingDetail, Skip skip);

    int updateDayDetailReplacement(Integer matricule, Integer matricule1, String wordingDetail, Replacement replacement);

    List<DayDetail> findAll();

    List<DayDetail> findByMissionIsNotNull();

    int updateDayDetailByDeletingMission(DayDetail dayDetail);

    int updateDayDetailByDeletingReplacement(DayDetail dayDetail);

    DayDetail findById(Long id);

    List<DayDetail> findByReplacementIsNotNullAndDetailIsNotNull();

    List<DayDetail> findByDetailIsNullAndSkipIsNullAndRepalcementIsNullAndMissionIsNull();

    int deleteDayDetailWhereIsNull();

    DayDetail findByReplacementIdAndDetailIsNull(Long id);

    List<DayDetail> findBySkipIsNotNull();

    List<DayDetail> findBySkipId(Long id);

}
