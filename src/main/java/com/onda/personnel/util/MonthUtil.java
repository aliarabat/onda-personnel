package com.onda.personnel.util;

public class MonthUtil {
	
	public static final String[] MONTHS= {"JANVIER", "FEVRIER", "MARS", "AVRIL",
			"MAI", "JUIN", "JUILLET", "AOUT", "SEPTEMBRE", "OCTOBRE", "NOVEMBRE", "DECEMBRE"};
	
	public static String getMonth(int index) {
		return MONTHS[index];
	}

}
