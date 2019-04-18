/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.rest.vo;

import com.onda.personnel.bean.Day;
import java.util.Date;
import java.util.List;
import javax.persistence.OneToMany;

/**
 *
 * @author AMINE
 */
public class WorkDetailVo {
    private Long id;
    private List<DayVo> days;
    private String workDetailDate;
    private String pan;
    private String hn;
    private String hjf;
}
