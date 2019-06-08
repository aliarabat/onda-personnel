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

    private static final File file = new File("");
    private static String cheminJasper = file.getAbsolutePath() + "\\src\\main\\java\\com\\onda\\personnel\\reports\\";
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
