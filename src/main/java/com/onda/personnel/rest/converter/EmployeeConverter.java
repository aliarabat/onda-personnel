/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.converter;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.common.util.NumberUtil;
import com.onda.personnel.rest.vo.EmployeeVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class EmployeeConverter extends AbstractConverter<Employee, EmployeeVo> {
   

    @Override
    public Employee toItem(EmployeeVo vo) {
        if (vo == null) {
            return null;
        } else {
            Employee emp = new Employee();
            emp.setId(vo.getId());
            emp.setFirstName(vo.getFirstName());
            emp.setLastName(vo.getLastName());
            emp.setMatricule(NumberUtil.toInteger(vo.getMatricule()));
            return emp;
        }
    }

    @Override
    public EmployeeVo toVo(Employee item) {
        if (item == null) {
            return null;
        } else {
            EmployeeVo empVo = new EmployeeVo();
            empVo.setId(item.getId());
            empVo.setFirstName(item.getFirstName());
            empVo.setLastName(item.getLastName());
            empVo.setMatricule(NumberUtil.toString(item.getMatricule()));
            return empVo;
        }
    }

}
