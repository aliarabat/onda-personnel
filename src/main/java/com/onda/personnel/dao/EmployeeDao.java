/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onda.personnel.model.Employee;

/**
 *
 * @author AMINE
 */
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

    Employee findByMatricule(Integer matricule);

    List<Employee> findByIsExist(boolean isExist);

    List<Employee> findByType(String type);

}
