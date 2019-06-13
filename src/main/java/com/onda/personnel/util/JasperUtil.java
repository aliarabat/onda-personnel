/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.onda.personnel.model.Employee;
import com.onda.personnel.rest.vo.WorkVo;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JsonExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleJsonExporterOutput;

/**
 *
 * @author moulaYounes
 */
@Component
public class JasperUtil {

    public JasperPrint generatePdf(List<WorkVo> list, Map<String, Object> params, String cheminJapser)
            throws FileNotFoundException, JRException, IOException {
        // String
        // path=resourceLoader.getResource("classpath:Blank_A4.jrxml").getURI().getPath();
        InputStream reportSource = getClass().getResourceAsStream(cheminJapser);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        params.put("workDataSource", beanCollectionDataSource);
        // JasperExportManager.exportReportToPdfFile(jasperPrint,
        // "D:\\monpremierpdf.pdf");
        // JasperExportManager.exportReportToPdfFile(jasperPrint, cheminExport);
        // JasperExportManager.exportReportToPdfStream(reportSource, outputStream);
        // JRPdfExporter exporter = new JRPdfExporter();
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportSource, params, new JREmptyDataSource());
        return jasperPrint;
        /*
		 * exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		 * exporter.setExporterOutput( new
		 * SimpleOutputStreamExporterOutput(cheminExport));
		 * 
		 * SimplePdfReportConfiguration reportConfig = new
		 * SimplePdfReportConfiguration(); reportConfig.setSizePageToContent(true);
		 * reportConfig.setForceLineBreakPolicy(false);
		 * 
		 * SimplePdfExporterConfiguration exportConfig = new
		 * SimplePdfExporterConfiguration(); exportConfig.setMetadataAuthor("baeldung");
		 * exportConfig.setEncrypted(true);
		 * exportConfig.setAllowedPermissionsHint("PRINTING");
		 * 
		 * exporter.setConfiguration(reportConfig);
		 * exporter.setConfiguration(exportConfig);
		 * 
		 * exporter.exportReport();
         */
 /*
		 * if (isPdfVisible) { showPdf(cheminExport); } reportSource.close();
         */
    }

    public JasperPrint generateDoc(List list, Map<String, Object> params, String jasperFile, String type)
            throws FileNotFoundException, JRException, IOException {
        JasperPrint jasperPrint = null;
        switch (type.toLowerCase()) {
            case "pdf":
                jasperPrint = generatePdf(list, params, Config.getCheminJasper() + jasperFile);
                break;
            case "xlsx":
                jasperPrint = generateXls(list, params, Config.getCheminJasper() + jasperFile, Config.getCheminExport());
                break;
            case "csv":
                jasperPrint = generateCsv(list, params, Config.getCheminJasper() + jasperFile, Config.getCheminExport());
                break;
            default:
                break;
        }
        return jasperPrint;
    }

    public static void show(String chemin) throws IOException {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + chemin);
    }

    public static JasperPrint generateXls(List list, Map<String, Object> params, String cheminJapser,
            String cheminExport) throws JRException, FileNotFoundException, IOException {
        InputStream reportSource = null;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        reportSource = new FileInputStream(cheminJapser);
        params.put("workDataSource", beanCollectionDataSource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportSource, params, new JREmptyDataSource());
        return jasperPrint;
        /*
		 * JRXlsxExporter exporter = new JRXlsxExporter();
		 * 
		 * exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		 * exporter.setExporterOutput(new
		 * SimpleOutputStreamExporterOutput(cheminExport));
		 * 
		 * SimpleXlsxReportConfiguration reportConfig = new
		 * SimpleXlsxReportConfiguration(); reportConfig.setSheetNames(new String[] {
		 * "Employee Data" });
		 * 
		 * exporter.setConfiguration(reportConfig); exporter.exportReport();
		 * reportSource.close();
         */
    }

    /*
	 * public static void generateXls(List list, Map params, boolean isXlsVisible)
	 * throws JRException, FileNotFoundException, IOException { generateXls(list,
	 * Config.getCheminJasper(), Config.getCheminExport() + new Date().getTime() +
	 * ".xlsx", params, isXlsVisible); }
     */
    public static JasperPrint generateCsv(List list, Map<String, Object> params, String cheminJapser,
            String cheminExport) throws JRException, FileNotFoundException, IOException {
        InputStream reportSource;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        reportSource = new FileInputStream(cheminJapser);

        params.put("workDataSource", beanCollectionDataSource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportSource, params, new JREmptyDataSource());
        return jasperPrint;
        /*
		 * JRCsvExporter exporter = new JRCsvExporter();
		 * 
		 * exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		 * exporter.setExporterOutput(new SimpleWriterExporterOutput(cheminExport));
		 * exporter.exportReport();
         */
 /*
		 * show(cheminExport); reportSource.close();
         */
    }

    /*
	 * public static void generateCsv(List list, Map params, boolean isXlsVisible)
	 * throws JRException, FileNotFoundException, IOException { generateCsv(list,
	 * Config.getCheminJasper(), Config.getCheminExport() + new Date().getTime() +
	 * ".csv", params, isXlsVisible); }
     */
    public static void generateJson(List list, String cheminJapser, String cheminExport, Map params)
            throws JRException, FileNotFoundException, IOException {
        InputStream reportSource;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        reportSource = new FileInputStream(cheminJapser);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportSource, params, beanCollectionDataSource);

        JsonExporter exporter = new JsonExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleJsonExporterOutput(cheminExport));
        exporter.exportReport();
        show(cheminExport);

        reportSource.close();
    }

    public static JasperPrint generatePdf(Employee employee, String cheminJapser) throws FileNotFoundException, JRException {
        InputStream reportSource = new FileInputStream(Config.getCheminJasper() + cheminJapser);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("empolyeData", employee);

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportSource, params, new JREmptyDataSource());
        return jasperPrint;

    }

    /*
	 * public static void generateJson(List list, Map params, boolean isXlsVisible)
	 * throws JRException, FileNotFoundException, IOException { generateCsv(list,
	 * Config.getCheminJasper(), Config.getCheminExport() + new Date().getTime() +
	 * ".json", params, isXlsVisible); }
     */
}
