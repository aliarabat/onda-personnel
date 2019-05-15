/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.util;

import java.util.List;

/**
 *
 * @author Xrio
 */
public class ListUtil {
    
    private static boolean isNull(List list){
        return ObjectUtil.isNull(list);
    }
    
    private static boolean isEmpty(List list){
        return list.isEmpty();
    }
    
    private static int size(List list){
        return list.size();
    }
}
