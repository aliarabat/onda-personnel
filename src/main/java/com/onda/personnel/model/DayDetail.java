/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.model;

import java.io.Serializable;
import java.time.LocalTime;
import javax.persistence.CascadeType;
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
public class DayDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Detail detail;
    @OneToOne
    private Replacement replacement;
    @OneToOne
    private Skip skip;
    @OneToOne
    private Mission mission;
    

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

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public Replacement getReplacement() {
        return replacement;
    }

    public void setReplacement(Replacement replacement) {
        this.replacement = replacement;
    }

    public Skip getSkip() {
        return skip;
    }

    public void setSkip(Skip skip) {
        this.skip = skip;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
    
    
    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DayDetail)) {
            return false;
        }
        DayDetail other = (DayDetail) object;
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
        return "DayDetail{" +
                "detail=" + detail +
                '}';
    }
}
