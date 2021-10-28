package br.ufal.main.database;

import java.util.ArrayList;

import br.ufal.main.model.Employee;

public class DatabaseConnection {
	private ArrayList<Employee> employees;
	
	public DatabaseConnection() {
		this.employees = new ArrayList<Employee>();
	}

	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}
	
	public ArrayList<Employee> getEmployees() {
		return this.employees;
	}

}
