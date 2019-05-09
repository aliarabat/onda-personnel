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
public class MissionVo {

    private Long id;
    private String reference;
    private EmployeeVo employeeVo;
    private String type;
    private String startingDate;
    private DetailVo detailVo;
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeVo getEmployee() {
        return employeeVo;
    }

    public void setEmployee(EmployeeVo employeeVo) {
        this.employeeVo = employeeVo;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public DetailVo getDetailVo() {
        return detailVo;
    }

    public void setDetailVo(DetailVo detailVo) {
        this.detailVo = detailVo;
    }

   

}
