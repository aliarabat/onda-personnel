/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.vo;

/**
 * @author AMINE
 */
public class WorkVo {

    private Long id;
    private WorkDetailVo workDetailVo;
    private EmployeeVo employeeVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkDetailVo getWorkDetailVo() {
        return workDetailVo;
    }

    public void setWorkDetailVo(WorkDetailVo workDetailVo) {
        this.workDetailVo = workDetailVo;
    }

    public EmployeeVo getEmployeeVo() {
        return employeeVo;
    }

    public void setEmployeeVo(EmployeeVo employeeVo) {
        this.employeeVo = employeeVo;
    }
}
