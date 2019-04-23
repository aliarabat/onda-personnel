/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Xrio
 */
@Entity
public class Replacement extends Absence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    @OneToOne
    private Employee originalEmployee;
    @OneToOne
    private Employee replacedEmpolyee;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date replacementDate;
    @OneToMany
    private List<DayDetail> dayDetails;

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
    public Employee getOriginalEmployee() {
        return originalEmployee;
    }

    /**
     *
     * @param originalEmployee
     */
    public void setOriginalEmployee(Employee originalEmployee) {
        this.originalEmployee = originalEmployee;
    }

    /**
     *
     * @return
     */
    public Employee getReplacedEmpolyee() {
        return replacedEmpolyee;
    }

    /**
     *
     * @param replacedEmpolyee
     */
    public void setReplacedEmpolyee(Employee replacedEmpolyee) {
        this.replacedEmpolyee = replacedEmpolyee;
    }

    /**
     *
     * @return
     */
    public Date getReplacementDate() {
        return replacementDate;
    }

    /**
     *
     * @param replacementDate
     */
    public void setReplacementDate(Date replacementDate) {
        this.replacementDate = replacementDate;
    }

    /**
     *
     * @return
     */
    public List<DayDetail> getDayDetails() {
        return dayDetails;
    }

    /**
     *
     * @param dayDetails
     */
    public void setDayDetails(List<DayDetail> dayDetails) {
        this.dayDetails = dayDetails;
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
        if (!(object instanceof Replacement)) {
            return false;
        }
        Replacement other = (Replacement) object;
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
        return "onda.personnel.horaire.model.Replacement[ id=" + id + " ]";
    }

}
