/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.Replacement;
import com.onda.personnel.dao.ReplacementDao;
import com.onda.personnel.service.EmployeeService;
import com.onda.personnel.service.ReplacementService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JaafarDiyaou
 */
@Service
public class ReplacementServiceImpl implements ReplacementService{

    @Autowired
    ReplacementDao replacementDao;
    @Autowired
        EmployeeService employeeService;
    @Override
    public List<Replacement> findByOriginalEmployeeMatriculeAndReplacementDate(Integer matricule, Date replacementDate) {
return replacementDao.findByOriginalEmployeeMatriculeAndReplacementDate(matricule, replacementDate);
    }

    @Override
    public int createReplacement(Replacement replacement) {
        Employee employee=employeeService.findByMatricule(replacement.getOriginalEmployee().getMatricule());
                Employee employee2=employeeService.findByMatricule(replacement.getReplacedEmpolyee().getMatricule());
        if(employee==null || employee2==null){
            return -5;
        }
        
        
List<Replacement> checkListReplacement=findByOriginalEmployeeMatriculeAndReplacementDate(replacement.getOriginalEmployee().getMatricule(),replacement.getReplacementDate());
if(checkListReplacement.contains(replacement)){
    return -1;
}
else {
    replacement.setOriginalEmployee(employee);
    replacement.setReplacedEmpolyee(employee2);
    replacementDao.save(replacement);
    return 1;
}
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public ReplacementDao getReplacementDao() {
        return replacementDao;
    }

    public void setReplacementDao(ReplacementDao replacementDao) {
        this.replacementDao = replacementDao;
    }
    
    
    
}
