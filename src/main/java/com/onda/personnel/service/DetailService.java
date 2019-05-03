/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.time.LocalTime;
import java.util.List;

import com.onda.personnel.model.Detail;
import com.onda.personnel.model.Timing;

/**
 *
 * @author AMINE
 */
public interface DetailService {

    public Detail findByWording(String wording);

    public int createDetail(List<Detail> details);

    public int updateDetail(Detail detail);

    public int deleteDetail(String wording);

    public List<Detail> findAll();

    public List<Detail> findByMode(String mode);

    public  Timing getHoursBetween(int startingHour, int startingMinute, int endingHour, int endingMinute, boolean isNight); 

}
