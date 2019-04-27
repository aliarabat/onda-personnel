/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.vo;

/**
 * @author AMINE
 */
public class DetailVo {

    private Long id;
    private String wording;
    private String reference;
    private TimingVo startingTimeVo;
    private TimingVo endingTimeVo;
    private String pan;
    private TimingVo hnVo;
    private TimingVo heVo;
    private String mode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public TimingVo getStartingTimeVo() {
        return startingTimeVo;
    }

    public void setStartingTimeVo(TimingVo startingTimeVo) {
        this.startingTimeVo = startingTimeVo;
    }

    public TimingVo getEndingTimeVo() {
        return endingTimeVo;
    }

    public void setEndingTimeVo(TimingVo endingTimeVo) {
        this.endingTimeVo = endingTimeVo;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public TimingVo getHnVo() {
        return hnVo;
    }

    public void setHnVo(TimingVo hnVo) {
        this.hnVo = hnVo;
    }

    public TimingVo getHeVo() {
        return heVo;
    }

    public void setHeVo(TimingVo heVo) {
        this.heVo = heVo;
    }

    public String getReference() { return reference; }

    public void setReference(String reference) { this.reference = reference; }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
