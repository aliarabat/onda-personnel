/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onda.personnel.model.Detail;
/**
 *
 * @author AMINE
 */
@Repository
public interface DetailDao extends JpaRepository<Detail, Long> {

    public Detail findByWording(String wording);

    @Override
	public List<Detail> findAll();

    public List<Detail> findByMode(String mode);

    Detail findTopByWording(String wording);
}
