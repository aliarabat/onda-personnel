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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onda.personnel.util.DateUtil;
import com.onda.personnel.util.JasperUtil;
import com.onda.personnel.util.MonthUtil;
import com.onda.personnel.util.NumberUtil;
import com.onda.personnel.util.WorkComparator;
import com.onda.personnel.dao.WorkDao;
import com.onda.personnel.model.Work;
import com.onda.personnel.rest.converter.WorkConverter;
import com.onda.personnel.rest.vo.DayDetailVo;
import com.onda.personnel.rest.vo.WorkVo;
import com.onda.personnel.service.WorkService;
import java.time.Month;

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
        return workDao.findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule, workDetailDate);
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
        List<Work> works = workDao
                .findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetweenOrderByWorkDetailWorkDetailDateAsc(
                        matricule, DateUtil.toDate(ldStart), DateUtil.toDate(ldEnd));
        listWorkToShow(works);
        return works;
    }

    @Override
    public List<Work> findAllByWorkDetailWorkDetailDateBetween(Integer annee) {
        LocalDate ldStart = LocalDate.of(annee, 1, 1);
        LocalDate ldEnd = LocalDate.of(annee, 12, 31);
        List<Work> works = workDao
                .findByWorkDetailWorkDetailDateBetweenOrderByEmployeeMatriculeAscWorkDetailWorkDetailDateAsc(
                        DateUtil.toDate(ldStart), DateUtil.toDate(ldEnd));
        listWorkToShow(works);
        return works;
    }

    private void minutesManipulations(int hnHours, int hnMinutes, int hjfHours, int hjfMinutes) {
        if (hnMinutes < 0) {
            --hnHours;
            hnMinutes = hnMinutes + 60;
        }
        if (hjfMinutes < 0) {
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
                            w.getWorkDetailVo().setAdm(
                                    NumberUtil.toString(NumberUtil.toInteger(w.getWorkDetailVo().getAdm()) + 1));
                        }
                    } else if (day.getVacationVo().getType().equals("C.M")) {
                        w.getWorkDetailVo()
                                .setCm(NumberUtil.toString(NumberUtil.toInteger(w.getWorkDetailVo().getCm()) + 1));
                    } else if (day.getVacationVo().getType().equals("A.T")) {
                        w.getWorkDetailVo()
                                .setAt(NumberUtil.toString(NumberUtil.toInteger(w.getWorkDetailVo().getAt()) + 1));
                    } else if (day.getVacationVo().getType().equals("C.EXCEP")) {
                        w.getWorkDetailVo()
                                .setCex(NumberUtil.toString(NumberUtil.toInteger(w.getWorkDetailVo().getCex()) + 1));
                    }
                } else {
                    DayDetailVo dayDetailVoCheck = day.getDayDetailsVo().stream().filter((d)
                            -> // (d.getDetailVo() != null && d.getMissionVo() != null) ||
                            (!d.getDetailVo().getWording().equals("R") && d.getReplacementVo() == null && d.getSkipVo() == null)
                            || (d.getDetailVo() == null && (d.getReplacementVo() != null || d.getMissionVo() != null)))
                            .findAny().orElse(null);
                    if (day.getReference() != null && dayDetailVoCheck != null) {
                        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                            w.getWorkDetailVo().setHolidayHundered(NumberUtil
                                    .toString(NumberUtil.toInteger(w.getWorkDetailVo().getHolidayHundered()) + 1));
                        } else {
                            w.getWorkDetailVo().setHolidayZero(NumberUtil
                                    .toString(NumberUtil.toInteger(w.getWorkDetailVo().getHolidayZero()) + 1));
                        }
                    }
                }
            });
            Integer hjf = NumberUtil.toInteger(w.getWorkDetailVo().getHjf().getHour());
            if (hjf > 192) {
                w.getWorkDetailVo().setThreeTeams(NumberUtil.toString(hjf - 192));
            }
            if (NumberUtil.toInteger(w.getWorkDetailVo().getPan()) == 0) {
                w.getWorkDetailVo().setPan("");
            }
            if (NumberUtil.toInteger(w.getWorkDetailVo().getHn().getHour()) == 0) {
                w.getWorkDetailVo().getHn().setHour("");
            }
            if (NumberUtil.toInteger(w.getWorkDetailVo().getAdm()) == 0) {
                w.getWorkDetailVo().setAdm("");
                System.out.println("haa l adm ==> " + w.getWorkDetailVo().getAdm());
            }
            if (NumberUtil.toInteger(w.getWorkDetailVo().getHolidayZero()) == 0) {
                w.getWorkDetailVo().setHolidayZero("");
            }
            if (NumberUtil.toInteger(w.getWorkDetailVo().getHolidayHundered()) == 0) {
                w.getWorkDetailVo().setHolidayHundered("");
            }
            if (NumberUtil.toInteger(w.getWorkDetailVo().getAt()) == 0) {
                w.getWorkDetailVo().setAt("");
            }
            if (NumberUtil.toInteger(w.getWorkDetailVo().getCm()) == 0) {
                w.getWorkDetailVo().setCm("");
            }
            if (NumberUtil.toInteger(w.getWorkDetailVo().getCex()) == 0) {
                w.getWorkDetailVo().setCex("");
            }
        });
        return worksVo;
    }

    public void listWorkToShow(List<Work> works) {
        works.forEach((w) -> {
            w.getWorkDetail().getDays().forEach((day) -> {
                if (day.getVacation() == null) {
                    day.getDayDetails().forEach((dd) -> {
                        int hnHours = w.getWorkDetail().getHn().getHour();
                        int hnMinutes = w.getWorkDetail().getHn().getMinute();
                        int hjfHours = w.getWorkDetail().getHjf().getHour();
                        int hjfMinutes = w.getWorkDetail().getHjf().getMinute();
                        if (dd.getDetail() != null) {
                            if (dd.getMission() != null || dd.getReplacement() != null || dd.getSkip() != null) {
                                hnHours = hnHours - dd.getDetail().getHn().getHour();
                                hnMinutes = hnMinutes - dd.getDetail().getHn().getMinute();
                                hjfHours = hjfHours - dd.getDetail().getHe().getHour();
                                hjfMinutes = hjfMinutes - dd.getDetail().getHe().getMinute();
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

                                w.getWorkDetail()
                                        .setPan(w.getWorkDetail().getPan() + dd.getReplacement().getDetail().getPan());

                                replacementMinutesManipulations(hnHours, hnMinutes, hjfHours, hjfMinutes);
                            } else if (dd.getSkip() != null) {
                                hnHours = hnHours - dd.getSkip().getDetail().getHn().getHour();
                                hnMinutes = hnMinutes - dd.getSkip().getDetail().getHn().getMinute();
                                hjfHours = hjfHours - dd.getSkip().getDetail().getHe().getHour();
                                hjfMinutes = hjfMinutes - dd.getSkip().getDetail().getHe().getMinute();

                                minutesManipulations(hnHours, hnMinutes, hjfHours, hjfMinutes);

                                w.getWorkDetail()
                                        .setPan(w.getWorkDetail().getPan() - dd.getSkip().getDetail().getPan());
                            }
                            setWorkParams(w, hnHours, hnMinutes, hjfHours, hjfMinutes);
                        }
                    });
                } else {
                    int hnHours = w.getWorkDetail().getHn().getHour() - day.getHn().getHour();
                    int hnMinutes = w.getWorkDetail().getHn().getMinute() - day.getHn().getMinute();
                    int hjfHours = w.getWorkDetail().getHjf().getHour() - day.getHe().getHour();
                    int hjfMinutes = w.getWorkDetail().getHjf().getMinute() - day.getHe().getMinute();

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
            fromDate = DateUtil.getFirstDayOfMonth();
            // toDate = fromDate.plusDays(6);
            // for tests
            // fromDate = DateUtil.getFirstDayOfMonth();
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
        List<Work> listOfWorksMonthly = findByWorkDetailWorkDetailDate(DateUtil.toDate(localDate));
        if (listOfWorksMonthly.isEmpty() || listOfWorksMonthly == null) {
            return null;
        } else {
            listWorkToShow(listOfWorksMonthly);
            return listOfWorksMonthly;
        }
    }

    @Override
    public List<Work> findByWorkDetailWorkDetailDate(Date workDetailDate) {
        return workDao
                .findByWorkDetailWorkDetailDateOrderByEmployeeMatriculeAscWorkDetailWorkDetailDateAsc(workDetailDate);
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
        response.addHeader("Content-Disposition",
                "attachement; filename=\"etatsElements" + mois + year + ".pdf" + "\"");
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

    @SuppressWarnings("deprecation")
    @Override
    public void printGraphForEmployee(HttpServletResponse response, int matricule, int year) {
        List<Work> list = findAllByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(matricule, year);
        if (list != null && !list.isEmpty() && list.size()>1) {
            JasperPrint jasperPrint = null;
            Map<String, Object> params = new HashMap<>();

            Double average = list.stream().mapToDouble(item -> item.getWorkDetail().getHjf().getHour()
                    + (item.getWorkDetail().getHjf().getMinute() * 10 / 6)).average().getAsDouble();
            Work workMin = Collections.min(list, new WorkComparator());
            Work workMax = Collections.max(list, new WorkComparator());
            String lastMonth = MonthUtil.getMonth(
                    workMax.getWorkDetail().getWorkDetailDate().getMonth() - 1);
            params.put("year", year);
            params.put("average", average);
            params.put("employee", list.get(0).getEmployee());
            params.put("firstMonth", MonthUtil.getMonth(workMin.getWorkDetail().getWorkDetailDate().getMonth() - 1));
            params.put("lastMonth", lastMonth);
            params.put("workMin", Double.parseDouble(workMin.getWorkDetail().getHjf().getHour() + "") - 4);
            params.put("workMax", Double.parseDouble(workMax.getWorkDetail().getHjf().getHour() + "") + 4);

            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition",
                    "attachement; filename=\"graphe" + list.get(0).getEmployee().getMatricule() + year + ".pdf" + "\"");
            OutputStream out = null;

            try {
                out = response.getOutputStream();
                jasperPrint = new JasperUtil().generateDoc(listToPrintToVo(list), params, "Graphe_Employee.jasper",
                        "pdf");
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JRException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Work findTopByEmployeeMatriculeAndWorkDetailWorkDetailDateBetween(Integer matricule, int year) {
        Date dateDebut = DateUtil.toDate(LocalDate.of(year, Month.JANUARY, 1));
        Date dateFin = DateUtil.toDate(LocalDate.of(year, Month.DECEMBER, 31));
        System.out.println("hha date debut ==> "+dateDebut);
        System.out.println("hha date fin ==> "+dateFin);
        return workDao.findTopByEmployeeMatriculeAndWorkDetailWorkDetailDateBetweenOrderByIdAsc(matricule, dateDebut, dateFin);
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
