/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.vo;

/**
 *
 * @author AMINE
 */
public class DayDetailVo {

    private Long id;
    private DetailVo detailVo;
    private ReplacementVo replacementVo;
    private SkipVo skipVo;
    private MissionVo missionVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetailVo getDetailVo() {
        return detailVo;
    }

    public void setDetailVo(DetailVo detailVo) {
        this.detailVo = detailVo;
    }

    public ReplacementVo getReplacementVo() {
        return replacementVo;
    }

    public void setReplacementVo(ReplacementVo replacementVo) {
        this.replacementVo = replacementVo;
    }

    public SkipVo getSkipVo() {
        return skipVo;
    }

    public void setSkipVo(SkipVo skipVo) {
        this.skipVo = skipVo;
    }

    public MissionVo getMissionVo() {
        return missionVo;
    }

    public void setMissionVo(MissionVo missionVo) {
        this.missionVo = missionVo;
    }
}
