/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service;

import java.util.List;

import com.onda.personnel.model.Vacation;

/**
 *
 * @author AMINE
 */
public interface VacationService {

    void saveVacation(Vacation vacation);

    int removeVacation(Long id);

    List<Vacation> findAllVacation();

    Vacation getVacationByID(Long id);

}
