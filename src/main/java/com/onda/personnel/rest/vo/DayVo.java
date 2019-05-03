/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.vo;

import java.util.List;
import javax.persistence.OneToOne;

import com.onda.personnel.model.Mission;
import com.onda.personnel.model.Vacation;

/**
 *
 * @author AMINE
 */
public class DayVo {

    private Long id;
    private String pan;
    private TimingVo hn;
    private TimingVo he;
    private List<DayDetailVo> dayDetailsVo;
    private String dayDate;
    private VacationVo vacationVo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VacationVo getVacationVo() {
        return vacationVo;
    }

    public void setVacationVo(VacationVo vacationVo) {
        this.vacationVo = vacationVo;
    }

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }

    public List<DayDetailVo> getDayDetailsVo() {
        return dayDetailsVo;
    }

    public void setDayDetailsVo(List<DayDetailVo> dayDetailsVo) {
        this.dayDetailsVo = dayDetailsVo;
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

    public TimingVo getHe() {
        return he;
    }

    public void setHe(TimingVo he) {
        this.he = he;
    }
}
