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
public class Detail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private String wording;
    @OneToOne(cascade = CascadeType.ALL)
    private Timing startingTime;
    @OneToOne(cascade = CascadeType.ALL)
    private Timing endingTime;
    private Integer pan;
    @OneToOne(cascade = CascadeType.ALL)
    private Timing hn;
    @OneToOne(cascade = CascadeType.ALL)
    private Timing he;
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

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
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

    public int getPan() {
        return pan;
    }

    public void setPan(int pan) {
        this.pan = pan;
    }

    public Timing getHn() {
        return hn;
    }

    public void setHn(Timing hn) {
        this.hn = hn;
    }

    public Timing getHe() {
        return he;
    }

    public void setHe(Timing he) {
        this.he = he;
    }

    public String getMode() {
        return mode;
    }

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
        return "Detail{" +
                "pan=" + pan +
                ", hn=" + hn +
                ", he=" + he +
                '}';
    }
}
