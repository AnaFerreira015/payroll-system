package br.ufal.main.database;

import java.util.ArrayList;
import java.util.Random;

import br.ufal.main.model.Employee;
import br.ufal.main.model.Timecard;

public class DatabaseConnection {
	private ArrayList<Employee> employees;
	private ArrayList<Timecard> timecards;
	
	public DatabaseConnection() {
		this.employees = new ArrayList<Employee>();
		this.timecards = new ArrayList<Timecard>();
	}

	public void addEmployee(Employee employee) {
		Random random = new Random();
		employee.setId(random.nextInt(500));
		this.employees.add(employee);
	}
	
	public ArrayList<Employee> getEmployees() {
		return this.employees;
	}
	
	public void removeEmployee(int id) {
		for (Employee employee : employees) {
			if (employee.getId() == id) {
				employees.remove(employee);
				break;
			}
		}
	}
	
	public boolean employeeExists(int id) {
		for (Employee employee : employees) {
			return employee.getId() == id;
		}
		return false;
	}
	
	public void addTimecard(Timecard timecard) {
		this.timecards.add(timecard);
	}
	
	public boolean isHourly(int id) {
		for (Employee employee : employees) {
			if (employee.getId() == id) {
				return employee.getType() == 1;
			}
		}
		
		return false;
	}
}
