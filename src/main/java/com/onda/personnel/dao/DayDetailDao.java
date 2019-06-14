/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.dao;

import com.onda.personnel.model.DayDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AMINE
 */
@Repository
public interface DayDetailDao extends JpaRepository<DayDetail, Long> {

    public List<DayDetail> findByMissionIsNotNull();

    public List<DayDetail> findByReplacementIsNotNullAndDetailIsNotNull();

    public List<DayDetail> findByDetailIsNullAndSkipIsNullAndReplacementIsNullAndMissionIsNull();

    public DayDetail findByReplacementIdAndDetailIsNull(Long id);

    public List<DayDetail> findBySkipIsNotNull();

    public List<DayDetail> findBySkipId(Long id);

}
