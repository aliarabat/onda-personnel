/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JsonExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleJsonExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 *
 * @author moulaYounes
 */
@Component
public class JasperUtil {

//    public static void generatePDFUser(List myList, Map params, String cheminExport, String cheminJasper, int flag) throws JRException, IOException {
//        // List<User> users est une liste qui va etre afficher dans jasper ==> $F
//        //  Map params ==> les parametre 
//        //String nom1 ==> chemin d'export de pdf
//        // chemin1 ==> chemin fixe de votre fichier jrxml (le user ne le voit pas)
//
//        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(myList);
//        // creation d'une liste de jasper a partir de notre liste users
//
//        runPdf(cheminExport, cheminJasper, params, beanCollectionDataSource, flag);
//        // methode private permet de generer le pdf + afficher le pdf en question
//    }
//
//    private static void runPdf(String cheminExport, String cheminJasper, Map params, JRBeanCollectionDataSource beanCollectionDataSource, int flag) throws JRException, IOException {
//        // creation de pdf
//        JasperDesign jasperDesign = JRXmlLoader.load(cheminJasper);
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, beanCollectionDataSource);
//        JasperExportManager.exportReportToPdfFile(jasperPrint, cheminExport);
//
//            // affichage pdf
//        // nom="BC-"+code+".pdf"
//        if (flag == 1) {
//            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + cheminExport);
//        }
//
//    }
    @Autowired
    private ResourceLoader resourceLoader;
    
    public JasperPrint generatePdf(List list, String cheminJapser, String cheminExport, Map params, boolean isPdfVisible) throws FileNotFoundException, JRException, IOException {
        //String path=resourceLoader.getResource("classpath:Blank_A4.jrxml").getURI().getPath();
        InputStream reportSource = new FileInputStream(cheminJapser);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
         
        //  JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\monpremierpdf.pdf");
        //JasperExportManager.exportReportToPdfFile(jasperPrint, cheminExport);
        //JasperExportManager.exportReportToPdfStream(reportSource, outputStream);
        //JRPdfExporter exporter = new JRPdfExporter();
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportSource, params, beanCollectionDataSource);
        return jasperPrint;
        /*
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(
                new SimpleOutputStreamExporterOutput(cheminExport));

        SimplePdfReportConfiguration reportConfig
                = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig
                = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor("baeldung");
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);
        
        exporter.exportReport();*/
        /*if (isPdfVisible) {
            showPdf(cheminExport);
        }
        reportSource.close();*/
    }

    public JasperPrint generatePdf(List list, Map params, boolean isPdfVisible) throws FileNotFoundException, JRException, IOException {
        return generatePdf(list, Config.getCheminJasper(), Config.getCheminExport() + new Date().getTime() + ".pdf", params, isPdfVisible);
    }

    public static void showPdf(String chemin) throws IOException {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + chemin);
    }

    public static void generateXls(List list, String cheminJapser, String cheminExport, Map params, boolean isPdfVisible) throws JRException, FileNotFoundException, IOException {
        InputStream reportSource = null;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        reportSource = new FileInputStream(cheminJapser);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportSource, params, beanCollectionDataSource);

        JRXlsxExporter exporter = new JRXlsxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(
                new SimpleOutputStreamExporterOutput(cheminExport));

        SimpleXlsxReportConfiguration reportConfig
                = new SimpleXlsxReportConfiguration();
        reportConfig.setSheetNames(new String[]{"Employee Data"});

        exporter.setConfiguration(reportConfig);
        exporter.exportReport();
        if (isPdfVisible) {
            showPdf(cheminExport);
        }
        reportSource.close();
    }
    
    public static void generateXls(List list, Map params, boolean isXlsVisible) throws JRException, FileNotFoundException, IOException{
        generateXls(list, Config.getCheminJasper(), Config.getCheminExport() + new Date().getTime() + ".xlsx", params, isXlsVisible);
    }
    
    public static void generateCsv(List list, String cheminJapser, String cheminExport, Map params, boolean isPdfVisible) throws JRException, FileNotFoundException, IOException {
        InputStream reportSource;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        reportSource = new FileInputStream(cheminJapser);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportSource, params, beanCollectionDataSource);

        JRCsvExporter exporter = new JRCsvExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(
                new SimpleWriterExporterOutput(cheminExport));
        exporter.exportReport();
        if (isPdfVisible) {
            showPdf(cheminExport);
        }
        reportSource.close();
    }
    
    public static void generateCsv(List list, Map params, boolean isXlsVisible) throws JRException, FileNotFoundException, IOException{
        generateCsv(list, Config.getCheminJasper(), Config.getCheminExport() + new Date().getTime() + ".csv", params, isXlsVisible);
    }
    
    public static void generateJson(List list, String cheminJapser, String cheminExport, Map params, boolean isPdfVisible) throws JRException, FileNotFoundException, IOException {
        InputStream reportSource;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        reportSource = new FileInputStream(cheminJapser);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportSource, params, beanCollectionDataSource);

       
        
        JsonExporter exporter=new JsonExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(
                new SimpleJsonExporterOutput(cheminExport));
        exporter.exportReport();
        if (isPdfVisible) {
            showPdf(cheminExport);
        }
        reportSource.close();
    }
    
    public static void generateJson(List list, Map params, boolean isXlsVisible) throws JRException, FileNotFoundException, IOException{
        generateCsv(list, Config.getCheminJasper(), Config.getCheminExport() + new Date().getTime() + ".json", params, isXlsVisible);
    }

}
