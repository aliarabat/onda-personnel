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
public class SkipVo {

    private Long id;
    private String reference;
    private EmployeeVo employeeVo;
    private String type;
    private String skipDate;
    private DetailVo detailVo;

    public String getSkipDate() {
        return skipDate;
    }

    public void setSkipDate(String skipDate) {
        this.skipDate = skipDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public EmployeeVo getEmployeeVo() {
        return employeeVo;
    }

    public void setEmployeeVo(EmployeeVo employeeVo) {
        this.employeeVo = employeeVo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DetailVo getDetailVo() {
        return detailVo;
    }

    public void setDetailVo(DetailVo detailVo) {
        this.detailVo = detailVo;
    }

}
