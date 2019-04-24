/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.bean.Detail;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface DetailService {

    public Detail findByWording(String wording);

    public int createDetail(Detail dayDetail);

    public int updateDayDetail(String wording, Detail dayDetail);

    public int deleteDayDeail(String wording);

    public List<Detail> findAll();

    public List<Detail> findByMode(String mode);
}
