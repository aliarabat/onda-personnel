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

    public WorkDetailVo getWorkDetail() {
        return workDetailVo;
    }

    public void setWorkDetail(WorkDetailVo workDetail) {
        this.workDetailVo = workDetail;
    }

    public EmployeeVo getEmployee() {
        return employeeVo;
    }

    public void setEmployee(EmployeeVo employeeVo) {
        this.employeeVo = employeeVo;
    }

}
