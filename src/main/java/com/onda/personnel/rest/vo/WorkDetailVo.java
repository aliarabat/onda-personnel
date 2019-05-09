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
	private Integer holidayZero = 0;
	private Integer holidayHundered = 0;
	private Integer threeTeams = 0;
	private Integer adm = 0;
	private Integer cm = 0;
	private Integer at = 0;
	private Integer cex = 0;

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

	public Integer getHolidayZero() {
		return holidayZero;
	}

	public void setHolidayZero(Integer holidayZero) {
		this.holidayZero = holidayZero;
	}

	public Integer getHolidayHundered() {
		return holidayHundered;
	}

	public void setHolidayHundered(Integer holidayHundered) {
		this.holidayHundered = holidayHundered;
	}

	public Integer getThreeTeams() {
		return threeTeams;
	}

	public void setThreeTeams(Integer threeTeams) {
		this.threeTeams = threeTeams;
	}

	public Integer getAdm() {
		return adm;
	}

	public void setAdm(Integer adm) {
		this.adm = adm;
	}

	public Integer getCm() {
		return cm;
	}

	public void setCm(Integer cm) {
		this.cm = cm;
	}

	public Integer getAt() {
		return at;
	}

	public void setAt(Integer at) {
		this.at = at;
	}

	public Integer getCex() {
		return cex;
	}

	public void setCex(Integer cex) {
		this.cex = cex;
	}

}
