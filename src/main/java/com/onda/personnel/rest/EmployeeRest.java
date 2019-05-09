/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.common.util.JasperUtil;
import com.onda.personnel.model.Employee;
import com.onda.personnel.rest.converter.AbstractConverter;
import com.onda.personnel.rest.vo.EmployeeVo;
import com.onda.personnel.service.EmployeeService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
    
/**
 * @author AMINE
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
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

    @PutMapping("/")
    public int editEmployee(@RequestBody EmployeeVo newEmployeeVo) {
        Employee newEmployee = employeeConverter.toItem(newEmployeeVo);
        return employeeService.editEmployee(newEmployee);
    }

    @DeleteMapping("/matricule/{matricule}")
    public int deleteEmployee(@PathVariable Integer matricule) {
        return employeeService.deleteEmployee(matricule);
    }
    @GetMapping("/id/{id}")
    public EmployeeVo getEmployeeById( @PathVariable Long id) {
        return employeeConverter.toVo(employeeService.getEmployeeById(id));
    }
    
    /*@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("home");
        return mav;
    }*/

    @GetMapping("/generatepdf/matricule/{matricule}")
    public void generatePdf(HttpServletResponse response,@PathVariable Integer matricule) throws JRException, IOException {
        employeeService.print(response, matricule);
    }
    
    @GetMapping("/generateXLS")
    public @ResponseBody void generateXls(HttpServletResponse response) throws JRException, IOException {

        List<Employee> employees = findByIsExist(true);
//        response.setContentType("application/x-download");
//        response.setHeader("Content-Disposition", String.format("attachement; filename=\"empployees.pdf\""));

        JasperUtil.generateXls(employees, null, true);

    }
    
    @GetMapping("/generateCSV")
    public @ResponseBody void generateCsv(HttpServletResponse response) throws JRException, IOException {

        List<Employee> employees = findByIsExist(true);
//        response.setContentType("application/x-download");
//        response.setHeader("Content-Disposition", String.format("attachement; filename=\"empployees.pdf\""));

        JasperUtil.generateCsv(employees, null, true);

    }
    
    @GetMapping("/generateJSON")
    public @ResponseBody void generateJson(HttpServletResponse response) throws JRException, IOException {

        List<Employee> employees = findByIsExist(true);
//        response.setContentType("application/x-download");
//        response.setHeader("Content-Disposition", String.format("attachement; filename=\"empployees.pdf\""));

        JasperUtil.generateJson(employees, null, true);

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

}
