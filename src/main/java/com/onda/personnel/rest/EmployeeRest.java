/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.model.Employee;
import com.onda.personnel.rest.converter.AbstractConverter;
import com.onda.personnel.rest.converter.EmployeeConverter;
import com.onda.personnel.rest.vo.EmployeeVo;
import com.onda.personnel.service.EmployeeService;

import java.io.IOException;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"https://onda-marrakech.firebaseapp.com", "https://onda-menara.tk"})
@RequestMapping("/personnel-api/personnels/employee")
public class EmployeeRest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    @Qualifier("employeeConverter")
    private AbstractConverter<Employee, EmployeeVo> employeeConverter;

    @GetMapping("/matricule/{matricule}")
    public EmployeeVo findByMatricule(@PathVariable Integer matricule) {
        Employee checkEmployee = employeeService.findByMatricule(matricule);
        return employeeConverter.toVo(checkEmployee);
    }

    @PostMapping("/")
    public int createEmployee(@RequestBody List<EmployeeVo> employeeVos) {
        List<Employee> employees = employeeConverter.toItem(employeeVos);
        return employeeService.createEmployee(employees);
    }

    @GetMapping("/allExist/isExist/{isExist}")
    public List<Employee> findByIsExist(@PathVariable boolean isExist) {
        return employeeService.findByIsExist(isExist);
    }

    @GetMapping("/type/{type}")
    public List<EmployeeVo> findByType(@PathVariable String type) {
        return new EmployeeConverter().toVo(employeeService.findByType(type));
    }

    @PutMapping("/")
    public int editEmployee(@RequestBody EmployeeVo newEmployeeVo) {
        Employee newEmployee = employeeConverter.toItem(newEmployeeVo);
        return employeeService.editEmployee(newEmployee);
    }

    @DeleteMapping("/matricule/{matricule}")
    public int deleteEmployee(@PathVariable Integer matricule) {
        return employeeService.deleteEmployee(matricule);
    }

    @DeleteMapping("revert/matricule/{matricule}")
    public int revert(@PathVariable Integer matricule) {
        return employeeService.revert(matricule);
    }

    @GetMapping("/id/{id}")
    public EmployeeVo getEmployeeById(@PathVariable Long id) {
        return employeeConverter.toVo(employeeService.getEmployeeById(id));
    }

    @GetMapping("/generatepdf/matricule/{matricule}")
    public void generatePdf(HttpServletResponse response, @PathVariable Integer matricule) throws JRException, IOException {
        employeeService.print(response, matricule);
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public AbstractConverter<Employee, EmployeeVo> getEmployeeConverter() {
        return employeeConverter;
    }

    public void setEmployeeConverter(AbstractConverter<Employee, EmployeeVo> employeeConverter) {
        this.employeeConverter = employeeConverter;
    }

    @GetMapping("/numberofemployees")
    public long count() {
        return employeeService.count();
    }

}
