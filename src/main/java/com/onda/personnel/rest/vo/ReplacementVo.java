/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.vo;

/**
 * @author AMINE
 */
public class ReplacementVo {

    private Long id;
    private String reference;
    private EmployeeVo originalEmployee;
    private EmployeeVo replacedEmployee;
    private String replacementDate;
    private DetailVo detailVo;

    public DetailVo getDetailVo() {
        return detailVo;
    }

    public void setDetailVo(DetailVo detailVo) {
        this.detailVo = detailVo;
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

    public EmployeeVo getOriginalEmployee() {
        return originalEmployee;
    }

    public void setOriginalEmployee(EmployeeVo originalEmployee) {
        this.originalEmployee = originalEmployee;
    }

    public EmployeeVo getReplacedEmployee() {
        return replacedEmployee;
    }

    public void setReplacedEmployee(EmployeeVo replacedEmployee) {
        this.replacedEmployee = replacedEmployee;
    }

    public String getReplacementDate() {
        return replacementDate;
    }

    public void setReplacementDate(String replacementDate) {
        this.replacementDate = replacementDate;
    }

}
