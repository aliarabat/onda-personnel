/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.vo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author AMINE
 */
public class WorkDetailVo {

    private Long id;
    private String workDetailDate;
    private String pan;
    private String hn;
    private String hjf;
    private List<DayVo> days;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkDetailDate() {
        return workDetailDate;
    }

    public void setWorkDetailDate(String workDetailDate) {
        this.workDetailDate = workDetailDate;
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

    public String getHjf() {
        return hjf;
    }

    public void setHjf(String hjf) {
        this.hjf = hjf;
    }

    public List<DayVo> getDays() {
        return days;
    }

    public void setDays(List<DayVo> days) {
        this.days = days;
    }

}
