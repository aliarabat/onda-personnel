/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.util;

import java.io.File;

/**
 *
 * @author moulaYounes
 */
public class Config {

    private static String cheminJasper = "personnel-0.0.1-SNAPSHOT.jar\\BOOT-INF\\classes\\com\\onda\\personnel\\reports\\";
    private static String cheminExport = "E:\\employess";

    public static String getCheminJasper() {
        return cheminJasper;
    }

    public static void setCheminJasper(String cheminJasper) {
        Config.cheminJasper = cheminJasper;
    }

    public static String getCheminExport() {
        return cheminExport;
    }

    public static void setCheminExport(String cheminExport) {
        Config.cheminExport = cheminExport;
    }

}
