/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.bean.DayDetail;
import com.onda.personnel.bean.Detail;
import com.onda.personnel.bean.Mission;
import java.util.Date;

/**
 *
 * @author AMINE
 */
public interface DayDetailService {
    
    public DayDetail createDayDetail ( DayDetail dayDetail);
        public int updateDayDetailMission(Integer matricule, String wordingDetail,Mission mission);
        //public int updateDayDetailMission(Integer matricule, Date dateMission, String wordingDetail,Mission mission);


}
