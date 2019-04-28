/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Xrio
 */
@Entity
public class WorkDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date workDetailDate;
    private Integer pan = 0;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Timing hn =new Timing(0,0);
    @OneToOne(cascade = CascadeType.PERSIST)
    private Timing hjf =new Timing(0,0);
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Day> days = new ArrayList<>();

    public WorkDetail() {
    }

    public WorkDetail(Date workDetailDate) {
        this.workDetailDate = workDetailDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public List<Day> getDays() {
        return days;
    }

    @JsonSetter
    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Date getWorkDetailDate() {
        return workDetailDate;
    }

    public void setWorkDetailDate(Date workDetailDate) {
        this.workDetailDate = workDetailDate;
    }

    public Integer getPan() {
        return pan;
    }

    public void setPan(Integer pan) {
        this.pan = pan;
    }

    public Timing getHn() {
        return hn;
    }

    public void setHn(Timing hn) {
        this.hn = hn;
    }

    public Timing getHjf() {
        return hjf;
    }

    public void setHjf(Timing hjf) {
        this.hjf = hjf;
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
        if (!(object instanceof WorkDetail)) {
            return false;
        }
        WorkDetail other = (WorkDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.WorkDetail[ id=" + id + " ]";
    }

}
