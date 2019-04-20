/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.bean.DayDetail;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface DayDetailService {

    public DayDetail findByWording(String wording);

    public int createDayDetail(DayDetail dayDetail);

    public int updateDayDetail(String wording, DayDetail dayDetail);

    public int deleteDayDeail(String wording);

    public List<DayDetail> findAll();

    public List<DayDetail> findByMode(String mode);
}
