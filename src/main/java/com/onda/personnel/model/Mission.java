/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Xrio
 */
@Entity
public class Mission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    @OneToOne
    private Employee employee;
    private String type;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startingDate;
    @OneToOne(cascade = CascadeType.ALL)
    private Timing startingTime;
    @OneToOne(cascade = CascadeType.ALL)
    private Timing endingTime;

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
    public Employee getEmployee() {
        return employee;
    }

    /**
     *
     * @param employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public Date getStartingDate() {
        return startingDate;
    }

    /**
     *
     * @param startingDate
     */
    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    /**
     *
     * @return
     */
    

    /**
     *
     * @param endingDate
     */
    

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
        if (!(object instanceof Mission)) {
            return false;
        }
        Mission other = (Mission) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Timing getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Timing startingTime) {
        this.startingTime = startingTime;
    }

    public Timing getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Timing endingTime) {
        this.endingTime = endingTime;
    }

   
    

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "onda.personnel.horaire.model.Mission[ id=" + id + " ]";
    }

}
