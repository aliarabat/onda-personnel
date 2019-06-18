/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest;

import com.onda.personnel.model.Replacement;
import com.onda.personnel.service.ReplacementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */
@RestController
@CrossOrigin(origins = {"https://onda-marrakech.firebaseapp.com", "https://onda-menara.tk", "http://localhost:4200"})
@RequestMapping("/personnel-api/personnels/replacement")
public class ReplacementRest {

    @Autowired
    ReplacementService replacementService;

    @GetMapping("/")
    public List<Replacement> findAll() {
        return replacementService.findAll();
    }

    public ReplacementService getReplacementService() {
        return replacementService;
    }

    public void setReplacementService(ReplacementService replacementService) {
        this.replacementService = replacementService;
    }

}
