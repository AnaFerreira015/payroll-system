package br.ufal.main.model;

import java.util.ArrayList;
import java.util.Date;

public class Salaried extends Employee {
	private double salary;

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
