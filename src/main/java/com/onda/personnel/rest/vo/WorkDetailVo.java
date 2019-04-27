/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.vo;

import com.onda.personnel.bean.Timing;

import java.util.Date;
import java.util.List;

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

    public String getPan() { return pan; }

    public void setPan(String pan) { this.pan = pan; }

    public TimingVo getHn() { return hn; }

    public void setHn(TimingVo hn) { this.hn = hn; }

    public TimingVo getHjf() { return hjf; }

    public void setHjf(TimingVo hjf) { this.hjf = hjf; }

    public List<DayVo> getDaysVo() { return daysVo; }

    public void setDaysVo(List<DayVo> daysVo) { this.daysVo = daysVo; }
}
