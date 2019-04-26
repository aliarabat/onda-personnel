/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.bean.Mission;
import com.onda.personnel.bean.Replacement;
import com.onda.personnel.bean.Skip;
import com.onda.personnel.service.DayDetailService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JaafarDiyaou
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/personnel-api/personnels/dayDetail")
public class DayDetailRest {
    
    @Autowired
    DayDetailService dayDetailService;
@PutMapping("/mission/matricule/{matricule}/wordingDetail/{wordingDetail}")
    public int updateDayDetailMission(@PathVariable Integer matricule, @PathVariable String wordingDetail,@RequestBody Mission mission) {
        return dayDetailService.updateDayDetailMission(matricule,  wordingDetail, mission);
    }
@PutMapping("/skip/matricule/{matricule}/wordingDetail/{wordingDetail}")
    public int updateDayDetailSkip(@PathVariable Integer matricule,@PathVariable String wordingDetail,@RequestBody Skip skip) {
        return dayDetailService.updateDayDetailSkip(matricule, wordingDetail, skip);
    }
@PutMapping("/replacement/matricule/{matricule}/matricule1/{matricule1}/wordingDetail/{wordingDetail}")
    public int updateDayDetailReplacement(@PathVariable Integer matricule,@PathVariable Integer matricule1,@PathVariable String wordingDetail,@RequestBody Replacement replacement) {
        return dayDetailService.updateDayDetailReplacement(matricule, matricule1, wordingDetail, replacement);
    }
    
    
    
    
    
    //@PutMapping("/matricule/{matricule}/dateMission/{dateMission}/wordingDetail/{wordingDetail}")
    

    public DayDetailService getDayDetailService() {
        return dayDetailService;
    }

    public void setDayDetailService(DayDetailService dayDetailService) {
        this.dayDetailService = dayDetailService;
    }
    
    
    
    
    
    
    
}
