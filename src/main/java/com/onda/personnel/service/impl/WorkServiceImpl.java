/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.onda.personnel.model.DayDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.onda.personnel.common.util.DateUtil;
import com.onda.personnel.common.util.JasperUtil;
import com.onda.personnel.common.util.MonthUtil;
import com.onda.personnel.common.util.NumberUtil;
import com.onda.personnel.dao.WorkDao;
import com.onda.personnel.model.Work;
import com.onda.personnel.rest.converter.WorkConverter;
import com.onda.personnel.rest.vo.DayDetailVo;
import com.onda.personnel.rest.vo.WorkVo;
import com.onda.personnel.service.WorkService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * @author AMINE
 */
@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkDao workDao;

    @Override
    public void saveWork(Work work) {
        workDao.save(work);
    }

    @Override
    public Work findByEmployeeMatriculeAndWorkDetailWorkDetailDate(Integer matricule, Date workDetailDate) {
        return workDao.findByEmployeeMatriculeAndWorkDetailWorkDetailDateOrderByEmployeeMatriculeAsc(matricule,
                workDetailDate);
    }

    public WorkDao getWorkDao() {
        return workDao;
    }

    public void setWorkDao(WorkDao workDao) {
        this.workDao = workDao;
    }

    @Override
    public Work findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(Integer matricule) {
        return workDao.findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(matricule);
    }

    @Override
    public List<Work> findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(Integer matricule, Integer annee) {
        LocalDate ldStart = LocalDate.of(annee, 1, 1);
        LocalDate ldEnd = LocalDate.of(annee, 12, 31);
        List<Work> works = workDao.findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(matricule,
                DateUtil.toDate(ldStart), DateUtil.toDate(ldEnd));
        listWorkToShow(works);
        return works;
    }

    @Override
    public List<Work> findAllByWorkDetailWorkDetailDateBetween(Integer annee) {
        LocalDate ldStart = LocalDate.of(annee, 1, 1);
        LocalDate ldEnd = LocalDate.of(annee, 12, 31);
        List<Work> works = workDao.findByWorkDetailWorkDetailDateBetweenOrderByEmployeeMatriculeAsc(
                DateUtil.toDate(ldStart), DateUtil.toDate(ldEnd));
        listWorkToShow(works);
        return works;
    }

    private void minutesManipulations(int hnHours, int hnMinutes, int hjfHours, int hjfMinutes) {
        if (hnMinutes <= 0) {
            --hnHours;
            hnMinutes = hnMinutes + 60;
        }
        if (hjfMinutes <= 0) {
            --hjfHours;
            hjfMinutes = hjfMinutes + 60;
        }
    }

    public List<WorkVo> listToPrintToVo(List<Work> works) {
        List<WorkVo> worksVo = new WorkConverter().toVo(works);
        worksVo.stream().forEach((w) -> {
            w.getWorkDetailVo().getDaysVo().stream().forEach((day) -> {
                Calendar c = Calendar.getInstance();
                c.setTime(DateUtil.toDate(DateUtil.fromStringToLocalDate(day.getDayDate())));
                if (day.getVacationVo() != null) {
                    if (day.getVacationVo().getType().equals("C.R") && day.getReference() == null) {
                        if (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                            w.getWorkDetailVo().setAdm(w.getWorkDetailVo().getAdm() + 1);
                        }
                    } else if (day.getVacationVo().getType().equals("C.M")) {
                        w.getWorkDetailVo().setCm(w.getWorkDetailVo().getCm() + 1);
                    } else if (day.getVacationVo().getType().equals("A.T")) {
                        w.getWorkDetailVo().setAt(w.getWorkDetailVo().getAt() + 1);
                    } else if (day.getVacationVo().getType().equals("C.EX")) {
                        w.getWorkDetailVo().setCex(w.getWorkDetailVo().getCex() + 1);
                    }
                } else {
                    DayDetailVo dayDetailVoCheck = day
                            .getDayDetailsVo().stream().filter((d) -> (
                                    d.getDetailVo() != null && d.getMissionVo() != null) ||
                                    (!d.getDetailVo().getWording().equals("R") && (d.getReplacementVo() == null || d.getSkipVo() == null)) ||
                                    (d.getDetailVo() == null && (d.getReplacementVo() != null) || d.getMissionVo() != null))
                            .findAny().orElse(null);
                    if (day.getReference() != null && dayDetailVoCheck != null) {
                        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                            w.getWorkDetailVo().setHolidayHundered(w.getWorkDetailVo().getHolidayHundered() + 1);
                        } else {
                            w.getWorkDetailVo().setHolidayZero(w.getWorkDetailVo().getHolidayZero() + 1);
                        }
                    }
                }
            });
            Integer hjf = NumberUtil.toInteger(w.getWorkDetailVo().getHjf().getHour());
            if (hjf > 192) {
                w.getWorkDetailVo().setThreeTeams(hjf - 192);
            }
        });
        return worksVo;
    }

    public void listWorkToShow(List<Work> works) {
        works.stream().forEach((w) -> {
            w.getWorkDetail().getDays().stream().forEach((day) -> {
                if (day.getVacation() == null) {
                    day.getDayDetails().stream().forEach((dd) -> {
                        int hnHours = w.getWorkDetail().getHn().getHour();
                        int hnMinutes = w.getWorkDetail().getHn().getMinute();
                        int hjfHours = w.getWorkDetail().getHjf().getHour();
                        int hjfMinutes = w.getWorkDetail().getHjf().getMinute();
                        if (dd.getDetail() != null) {
                            if (dd.getMission() != null || dd.getReplacement() != null || dd.getSkip() != null) {
                                hnHours = hnHours - dd.getDetail().getHn().getHour();
                                hnMinutes = hnMinutes - dd.getDetail().getHn().getMinute();
                                hjfHours = hjfHours - dd.getDetail().getHn().getHour();
                                hjfMinutes = hjfMinutes - dd.getDetail().getHn().getMinute();

                                minutesManipulations(hnHours, hnMinutes, hjfHours, hjfMinutes);

                                w.getWorkDetail().setPan(w.getWorkDetail().getPan() - dd.getDetail().getPan());
                                setWorkParams(w, hnHours, hnMinutes, hjfHours, hjfMinutes);
                            }
                        } else {
                            if (dd.getReplacement() != null) {
                                hnHours = hnHours + dd.getReplacement().getDetail().getHn().getHour();
                                hnMinutes = hnMinutes + dd.getReplacement().getDetail().getHn().getMinute();
                                hjfHours = hjfHours + dd.getReplacement().getDetail().getHe().getHour();
                                hjfMinutes = hjfMinutes + dd.getReplacement().getDetail().getHe().getMinute();

                                w.getWorkDetail().setPan(w.getWorkDetail().getPan() + dd.getReplacement().getDetail().getPan());

                                replacementMinutesManipulations(hnHours, hnMinutes, hjfHours, hjfMinutes);
                            } else if (dd.getMission() != null) {
                                hnHours = hnHours - dd.getMission().getDetail().getHn().getHour();
                                hnMinutes = hnMinutes - dd.getMission().getDetail().getHn().getMinute();
                                hjfHours = hjfHours - dd.getMission().getDetail().getHe().getHour();
                                hjfMinutes = hjfMinutes - dd.getMission().getDetail().getHe().getMinute();

                                minutesManipulations(hnHours, hnMinutes, hjfHours, hjfMinutes);

                                w.getWorkDetail().setPan(w.getWorkDetail().getPan() - dd.getMission().getDetail().getPan());
                            } else if (dd.getSkip() != null) {
                                hnHours = hnHours - dd.getSkip().getDetail().getHn().getHour();
                                hnMinutes = hnMinutes - dd.getSkip().getDetail().getHn().getMinute();
                                hjfHours = hjfHours - dd.getSkip().getDetail().getHe().getHour();
                                hjfMinutes = hjfMinutes - dd.getSkip().getDetail().getHe().getMinute();

                                minutesManipulations(hnHours, hnMinutes, hjfHours, hjfMinutes);

                                w.getWorkDetail().setPan(w.getWorkDetail().getPan() - dd.getSkip().getDetail().getPan());
                            }
                            setWorkParams(w, hnHours, hnMinutes, hjfHours, hjfMinutes);
                        }
                    });
                } else {
                    int hnHours = w.getWorkDetail().getHn().getHour() - day.getHn().getHour();
                    int hnMinutes = w.getWorkDetail().getHn().getMinute() - day.getHn().getMinute();
                    int hjfHours = w.getWorkDetail().getHjf().getHour() - day.getHn().getHour();
                    int hjfMinutes = w.getWorkDetail().getHjf().getMinute() - day.getHn().getMinute();

                    minutesManipulations(hnHours, hnMinutes, hjfHours, hjfMinutes);

                    w.getWorkDetail().setPan(w.getWorkDetail().getPan() - day.getPan());
                    setWorkParams(w, hnHours, hnMinutes, hjfHours, hjfMinutes);
                }
            });
        });
    }

    private void replacementMinutesManipulations(int hnHours, int hnMinutes, int hjfHours, int hjfMinutes) {
        if (hnMinutes >= 60) {
            ++hnHours;
            hnMinutes = hnMinutes - 60;
        }
        if (hjfMinutes >= 60) {
            ++hjfHours;
            hjfMinutes = hjfMinutes - 60;
        }
    }

    private void setWorkParams(Work w, int hnHours, int hnMinutes, int hjfHours, int hjfMinutes) {
        w.getWorkDetail().getHn().setHour(hnHours);
        w.getWorkDetail().getHn().setMinute(hnMinutes);
        w.getWorkDetail().getHjf().setHour(hjfHours);
        w.getWorkDetail().getHjf().setMinute(hjfMinutes);
    }

    @Override
    public List<String> findFromDateToDate(Integer matricule) {
        Work work = findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(matricule);
        List<String> dateList = new ArrayList<>(2);
        LocalDate fromDate;
        LocalDate toDate;
        if (work == null) {
            fromDate = DateUtil.getFirstDayOfWeek();
            // toDate = fromDate.plusDays(6);
        } else {
            int size = work.getWorkDetail().getDays().size();
            fromDate = DateUtil.fromDate(work.getWorkDetail().getDays().get(size - 1).getDayDate()).plusDays(1);
        }
        toDate = fromDate.plusDays(6);
        dateList.add(0, fromDate.toString());
        dateList.add(1, toDate.toString());
        return dateList;
    }

    @Override
    public List<Work> findByEmployeeMatriculeAndMonthAndYear(Integer matricule, int year, int month) {
        LocalDate localDate = LocalDate.of(year, month, 1);
        Date theDate = DateUtil.toDate(localDate);
        Work theWork = findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule, theDate);
        if (theWork == null) {
            return null;
        } else {
            List<Work> works = new ArrayList<>();
            works.add(theWork);
            listWorkToShow(works);
            return works;
        }
    }

    @Override
    public List<Work> findByMonthAndYear(int year, int month) {
        LocalDate localDate = LocalDate.of(year, month, 1);
        Date theDate = DateUtil.toDate(localDate);
        List<Work> listOfWorksMonthly = findByWorkDetailWorkDetailDate(theDate);
        if (listOfWorksMonthly.isEmpty() || listOfWorksMonthly == null) {
            return null;
        } else {
            return listOfWorksMonthly;
        }
    }

    @Override
    public List<Work> findByWorkDetailWorkDetailDate(Date workDetailDate) {
        return workDao.findByWorkDetailWorkDetailDateOrderByEmployeeMatriculeAsc(workDetailDate);

    }

    @Override
    public List<Work> findWorksByDate(Date workDate) {
        LocalDate checklocalDate = DateUtil.fromDate(workDate);
        LocalDate localDate = LocalDate.of(checklocalDate.getYear(), checklocalDate.getMonth(), 1);
        Date theDate = DateUtil.toDate(localDate);
        List<Work> works = findByWorkDetailWorkDetailDate(theDate);
        listWorkToShow(works);
        return works;
    }

    @Override
    public void printDoc(HttpServletResponse response, Integer year, Integer month) {
        JasperPrint jasperPrint = null;
        Map<String, Object> params = new HashMap<>();
        String mois = MonthUtil.getMonth(month - 1);

        params.put("month", mois);
        params.put("year", year);
        List<Work> list = findByMonthAndYear(year, month);
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachement; filename=\"etatsElements" + mois + year + ".pdf" + "\"");
        OutputStream out = null;

        try {
            out = response.getOutputStream();
            jasperPrint = new JasperUtil().generateDoc(listToPrintToVo(list), params, "etat_elements.jasper", "pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void printXlsx(HttpServletResponse response, Integer year, Integer month) {
        JasperPrint jasperPrint = null;
        Map<String, Object> params = new HashMap<>();
        String mois = MonthUtil.getMonth(month - 1);

        params.put("month", mois);
        params.put("year", year);
        List<Work> list = findByMonthAndYear(year, month);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.addHeader("Content-Disposition",
                "attachement; filename=\"etatsElements" + mois + year + ".xlsx" + "\"");
        OutputStream out = null;

        try {
            out = response.getOutputStream();
            jasperPrint = new JasperUtil().generateDoc(listToPrintToVo(list), params, "etat_elements.jasper", "xlsx");
            JRXlsxExporter exporter = new JRXlsxExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));

            SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
            reportConfig.setUseTimeZone(true);
            exporter.setConfiguration(reportConfig);
            exporter.exportReport();
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Work findTopByWorkDetailWorkDetailDateOrderByWorkDetailWorkDetailDateDesc(Date date) {
        return workDao.findTopByWorkDetailWorkDetailDateOrderByWorkDetailWorkDetailDateDesc(date);
    }


    @Override
    public List<Work> findAll() {
        return workDao.findAll();
    }


}
