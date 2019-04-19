/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.rest.vo.EmployeeVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */

@Component
public class EmployeeConverter  extends AbstractConverter<Employee, EmployeeVo>{

    @Override
    public Employee toItem(EmployeeVo vo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmployeeVo toVo(Employee item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
