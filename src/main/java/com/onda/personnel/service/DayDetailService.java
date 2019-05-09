/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.util.Date;

import com.onda.personnel.model.DayDetail;
import com.onda.personnel.model.Detail;
import com.onda.personnel.model.Mission;
import com.onda.personnel.model.Replacement;
import com.onda.personnel.model.Skip;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface DayDetailService {

    public DayDetail createDayDetail(DayDetail dayDetail);

    public int updateDayDetailMission(Integer matricule, String wordingDetail, Mission mission);

    public int updateDayDetailSkip(Integer matricule, String wordingDetail, Skip skip);

    public int updateDayDetailReplacement(Integer matricule, Integer matricule1, String wordingDetail, Replacement replacement);

    public List<DayDetail> findBySkipIsNotNull();

    public List<DayDetail> findBySkipId(Long id);

}
