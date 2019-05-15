/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.vo;

import java.util.Date;
import java.util.List;

import com.onda.personnel.model.Timing;

/**
 * @author AMINE
 */
public class WorkDetailVo {

    private Long id;
    private String workDetailDate;
    private String pan;
    private TimingVo hn;
    private TimingVo hjf;
    private List<DayVo> daysVo;
    private String holidayZero = "";
    private String holidayHundered = "";
    private String threeTeams = "";
    private String adm = "";
    private String cm = "";
    private String at = "";
    private String cex = "";

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

    public TimingVo getHn() {
        return hn;
    }

    public void setHn(TimingVo hn) {
        this.hn = hn;
    }

    public TimingVo getHjf() {
        return hjf;
    }

    public void setHjf(TimingVo hjf) {
        this.hjf = hjf;
    }

    public List<DayVo> getDaysVo() {
        return daysVo;
    }

    public void setDaysVo(List<DayVo> daysVo) {
        this.daysVo = daysVo;
    }

    public String getHolidayZero() {
        return holidayZero;
    }

    public void setHolidayZero(String holidayZero) {
        this.holidayZero = holidayZero;
    }

    public String getHolidayHundered() {
        return holidayHundered;
    }

    public void setHolidayHundered(String holidayHundered) {
        this.holidayHundered = holidayHundered;
    }

    public String getThreeTeams() {
        return threeTeams;
    }

    public void setThreeTeams(String threeTeams) {
        this.threeTeams = threeTeams;
    }

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        this.adm = adm;
    }

    public String getCm() {
        return cm;
    }

    public void setCm(String cm) {
        this.cm = cm;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getCex() {
        return cex;
    }

    public void setCex(String cex) {
        this.cex = cex;
    }
}
