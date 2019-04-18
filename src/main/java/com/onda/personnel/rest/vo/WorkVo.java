/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.vo;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.WorkDetail;
import javax.persistence.OneToOne;

/**
 *
 * @author AMINE
 */
public class WorkVo {
     private Long id;
    private WorkDetailVo workDetail;
    private EmployeeVo employee;
}
