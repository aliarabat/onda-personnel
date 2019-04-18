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
    private WorkDetailVo workDetail;
    private EmployeeVo employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkDetailVo getWorkDetail() {
        return workDetail;
    }

    public void setWorkDetail(WorkDetailVo workDetail) {
        this.workDetail = workDetail;
    }

    public EmployeeVo getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeVo employee) {
        this.employee = employee;
    }

}
