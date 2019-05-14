package com.onda.personnel;

import com.ibm.icu.text.SimpleDateFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.util.Date;

@SpringBootApplication
public class PersonnelApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(PersonnelApplication.class, args);
    }

}
