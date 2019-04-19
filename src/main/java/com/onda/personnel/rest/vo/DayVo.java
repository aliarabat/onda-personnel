/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.vo;

import java.util.List;

/**
 *
 * @author AMINE
 */
public class DayVo {
     private Long id;
    private List<DayDetailVo> dayDetails;
    private String pan;
    private String hn;
    private String he;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DayDetailVo> getDayDetails() {
        return dayDetails;
    }

    public void setDayDetails(List<DayDetailVo> dayDetails) {
        this.dayDetails = dayDetails;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getHn() {
        return hn;
    }

    public void setHn(String hn) {
        this.hn = hn;
    }

    public String getHe() {
        return he;
    }

    public void setHe(String he) {
        this.he = he;
    }
    
    
}

