/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.model;

import java.io.Serializable;
import java.util.Date;
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
public class Vacation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Employee employee;
    private String reference;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startingDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endingDate;
    private String type;

    public Vacation() {
    }

    public Vacation(Employee employee, Date startingDate, Date endingDate, String type) {
        this.employee = employee;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.type = type;
    }

   

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

   

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
    public Date getEndingDate() {
        return endingDate;
    }

    /**
     *
     * @param endingDate
     */
    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
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
        if (!(object instanceof Vacation)) {
            return false;
        }
        Vacation other = (Vacation) object;
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
        return "onda.personnel.horaire.model.Vacation[ id=" + id + " ]";
    }

}
