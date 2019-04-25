/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.bean;

import java.io.Serializable;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Xrio
 */
@Entity
public class Detail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private String wording;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private Integer pan;
    private Integer hn;
    private Integer he;
    private String mode;
    

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getReference() {
        return reference;
    }

    /**
     *
     * @param reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     *
     * @return
     */
    public String getWording() {
        return wording;
    }

    /**
     *
     * @param wording
     */
    public void setWording(String wording) {
        this.wording = wording;
    }

    /**
     *
     * @return
     */
    public LocalTime getStartingTime() {
        return startingTime;
    }

    /**
     *
     * @param startingTime
     */
    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    /**
     *
     * @return
     */
    public LocalTime getEndingTime() {
        return endingTime;
    }

    /**
     *
     * @param endingTime
     */
    public void setEndingTime(LocalTime endingTime) {
        this.endingTime = endingTime;
    }

    /**
     *
     * @return
     */
    public Integer getPan() {
        return pan;
    }

    /**
     *
     * @param pan
     */
    public void setPan(Integer pan) {
        this.pan = pan;
    }

    /**
     *
     * @return
     */
    public Integer getHn() {
        return hn;
    }

    /**
     *
     * @param hn
     */
    public void setHn(Integer hn) {
        this.hn = hn;
    }

    /**
     *
     * @return
     */
    public Integer getHe() {
        return he;
    }

    /**
     *
     * @param he
     */
    public void setHe(Integer he) {
        this.he = he;
    }

    /**
     *
     * @return
     */
    public String getMode() {
        return mode;
    }

    /**
     *
     * @param mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

   

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detail)) {
            return false;
        }
        Detail other = (Detail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication1.DayDetail[ id=" + id + " ]";
    }

}
