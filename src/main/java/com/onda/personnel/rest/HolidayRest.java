package com.onda.personnel.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onda.personnel.model.Holiday;
import com.onda.personnel.rest.converter.AbstractConverter;
import com.onda.personnel.rest.converter.HolidayConverter;
import com.onda.personnel.rest.vo.HolidayVo;
import com.onda.personnel.service.HolidayService;

@RestController
@CrossOrigin(origins = {"https://onda-marrakech.firebaseapp.com", "https://onda-menara.tk"})
@RequestMapping("/personnel-api/personnels/holiday")
public class HolidayRest {

    @Autowired
    private HolidayService holidayService;

    @Autowired
    @Qualifier("holidayConverter")
    private AbstractConverter<Holiday, HolidayVo> holidayConverter;

    @PostMapping("/")
    public int createHoliday(@RequestBody List<HolidayVo> holidaysVo) {
        return holidayService.createHoliday(new HolidayConverter().toItem(holidaysVo));
    }

    @GetMapping("/")
    public List<HolidayVo> findAll() {
        return holidayConverter.toVo(holidayService.findAll());
    }

    @PutMapping("/")
    public int update(@RequestBody HolidayVo holidayVo) {
        return holidayService.update(holidayConverter.toItem(holidayVo));
    }

    @GetMapping("/reference/{reference}")
    public HolidayVo findByReference(@PathVariable String reference) {
        return new HolidayConverter().toVo(holidayService.findByReference(reference));
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable Long id) {
        holidayService.delete(id);
    }

    @GetMapping("/countholidays")
    public long count() {
        return holidayService.count();
    }

    public HolidayService getHolidayService() {
        return holidayService;
    }

    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    public AbstractConverter<Holiday, HolidayVo> getHolidayConverter() {
        return holidayConverter;
    }

    public void setHolidayConverter(AbstractConverter<Holiday, HolidayVo> holidayConverter) {
        this.holidayConverter = holidayConverter;
    }

}
