package br.ufal.main.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Hourly extends Employee {
	private double salary, priceHour = 60 * 60 * 1000;

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public double calculateSalary(ArrayList<Timecard> timecards) {
		Date entryTimecard = null;
		double salary = 0;
		
		for (Timecard timecard : timecards) {
			if(timecard.getId() == this.getId()) {
				if(entryTimecard == null) {
					entryTimecard = timecard.getDate();
				} else {
					double differenceDate = timecard.getDate().getTime() - entryTimecard.getTime();
					double workedHours = differenceDate / (60 * 60 * 1000);
					
					if(workedHours - 8 > 0) {
						salary += (workedHours - 8) * 1.5;
						salary += 8;
					} else {
						salary += workedHours;
					}
					
					entryTimecard = null;		
				}
			}
		}
		return salary * this.priceHour;
	}
}
