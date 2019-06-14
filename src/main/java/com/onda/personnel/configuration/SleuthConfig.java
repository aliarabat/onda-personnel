/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brave.sampler.Sampler;

/**
 *
 * @author hp
 */
@Configuration
public class SleuthConfig {
    
    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
}
}
