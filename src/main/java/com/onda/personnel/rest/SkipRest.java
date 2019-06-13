/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.rest.converter.DayDetailConverter;
import com.onda.personnel.rest.vo.DayDetailVo;
import com.onda.personnel.service.SkipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin(origins = {"https://onda-marrakech.firebaseapp.com", "https://onda-menara.tk"})
@RequestMapping("/personnel-api/personnels/skip")
public class SkipRest {

    @Autowired
    SkipService skipService;

    @PutMapping("/")
    public int updateSkip(@RequestBody DayDetailVo dayDetailVo) {
        return skipService.updateSkip(new DayDetailConverter().toItem(dayDetailVo));
    }

    

    public SkipService getSkipService() {
        return skipService;
    }

    public void setSkipService(SkipService skipService) {
        this.skipService = skipService;
    }

}
