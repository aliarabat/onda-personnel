/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Xrio
 */
@Entity
public class Day implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<DayDetail> dayDetails;
    private Integer pan;
    private Integer hn;
    private Integer he;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DayDetail> getDayDetails() {
        return dayDetails;
    }

    public void setDayDetails(List<DayDetail> dayDetails) {
        this.dayDetails = dayDetails;
    }

    public Integer getPan() {
        return pan;
    }

    public void setPan(Integer pan) {
        this.pan = pan;
    }

    public Integer getHn() {
        return hn;
    }

    public void setHn(Integer hn) {
        this.hn = hn;
    }

    public Integer getHe() {
        return he;
    }

    public void setHe(Integer he) {
        this.he = he;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Day)) {
            return false;
        }
        Day other = (Day) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.DayNormal[ id=" + id + " ]";
    }

}
