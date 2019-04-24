/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.Mission;

/**
 *
 * @author AMINE
 */
public interface MissionService {
        public Employee findByEmployeeMatricule(Integer matricule);
        public int createMisssion (Mission mission);

}
