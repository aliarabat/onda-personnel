/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import com.onda.personnel.bean.Work;
import java.time.LocalDate;

/**
 *
 * @author AMINE
 */
public interface WorkService {
        public Work findByEmployeeMatriculeAndWorkDetailWorkDetailDate(Integer matricule, LocalDate workDetailDate);
}
