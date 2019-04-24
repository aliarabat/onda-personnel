/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.personnel.service.impl;

import com.onda.personnel.bean.Employee;
import com.onda.personnel.bean.Work;
import com.onda.personnel.bean.WorkDetail;
import com.onda.personnel.dao.WorkDao;
import com.onda.personnel.service.WorkService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
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
    public void createWork(Employee emp, WorkDetail workDetail) {
        Work work = new Work();
        work.setEmployee(emp);
        work.setWorkDetail(workDetail);
        saveWork(work);
    }

    @Override
    public Work findByEmployeeMatriculeAndWorkDetailTestDate(Integer matricule, Date workDetailDate) {
        return workDao.findByEmployeeMatriculeAndWorkDetailWorkDetailDate(matricule, workDetailDate);
    }

    public WorkDao getWorkDao() {
        return workDao;
    }

    public void setWorkDao(WorkDao workDao) {
        this.workDao = workDao;
    }

    @Override
    public Work findTopByEmployeeMatriculeOrderByWorkDetailTestDateDesc(Integer matricule) {
        return workDao.findTopByEmployeeMatriculeOrderByWorkDetailWorkDetailDateDesc(matricule);
    }

    @Override
    public List<Work> findByEmployeeMatricule(Integer matricule) {
        return workDao.findByEmployeeMatricule(matricule);
    }
/*
    @Override
    public int saveEmployeeInHistory(Employee emp) {
        List<Work> works = findByEmployeeMatricule(emp.getMatricule());
        if (works == null || works.isEmpty()) {
            return -1;
        } else {
            EmployeeHistory history = new EmployeeHistory(emp.getMatricule(), emp.getFirstName(), emp.getLastName(), new Date());
            historyService.saveHistory(history);
            for (Work work : works) {
                work.setEmployee(null);
                work.setHistory(history);
                saveWork(work);
            }
            return 1;
        }
    }
*/
    @Override
    public void deleteWork(Integer matricule, Date workDetailDate) {
        Work work = findByEmployeeMatriculeAndWorkDetailTestDate(matricule, workDetailDate);
        workDao.delete(work);
    }

    @Override
    public void deleteById(Long id) {
        workDao.deleteById(id);
    }

}
