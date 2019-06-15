/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.util.List;

import com.onda.personnel.model.Detail;
import com.onda.personnel.model.Timing;

/**
 *
 * @author AMINE
 */
public interface DetailService {

    Detail findByWording(String wording);

    int createDetail(List<Detail> details);

    int updateDetail(Detail detail);

    int deleteDetail(String wording);

    List<Detail> findAll();

    List<Detail> findByMode(String mode);

    Timing getHoursBetween(int startingHour, int startingMinute, int endingHour, int endingMinute, boolean isNight);

    Detail getDetailById(Long id);

    Detail findTopByWording(String r);

    long count();
}
