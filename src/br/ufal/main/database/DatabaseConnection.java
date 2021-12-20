package br.ufal.main.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import br.ufal.main.model.Commissioned;
import br.ufal.main.model.Employee;
import br.ufal.main.model.Hourly;
import br.ufal.main.model.Sale;
import br.ufal.main.model.Timecard;

public class DatabaseConnection {
	private ArrayList<Employee> employees;
	private ArrayList<Timecard> timecards;
	private ArrayList<Sale> sales;
	private int countFridays;
	
	public DatabaseConnection() {
		this.employees = new ArrayList<Employee>();
		this.timecards = new ArrayList<Timecard>();
		this.sales = new ArrayList<Sale>();
		this.countFridays = 0;
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
		for (Employee employee : this.employees) {
			if (employee.getId() == id) {
				this.employees.remove(employee);
				break;
			}
		}
	}
	
	public boolean employeeExists(int id) {
		for (Employee employee : this.employees) {
			if (employee.getId() == id) return true;
		}
		return false;
	}
	
	public boolean isCommissioned(int id) {
		for (Employee employee : this.employees) {
			if (employee.getId() == id && employee instanceof Commissioned) return true;
		}
		return false;
	}
	
	public void addTimecard(Timecard timecard) {
		this.timecards.add(timecard);
	}
	
	public boolean hasPointRegistered(int id) {
		for (Timecard timecard : this.timecards) {
			if (timecard.getId() == id) {
				return timecard.getType() == 1;
			}
		}
		
		return false;
	}
	
	public ArrayList<Timecard> getTimecards() {
		return this.timecards;
	}
	
	public ArrayList<Timecard> getTimecards(int id) {
		ArrayList<Timecard> timecardsEmployee = new ArrayList<Timecard>();
		
		for (Timecard timecard : this.timecards) {
			if (timecard.getId() == id) {
				timecardsEmployee.add(timecard);
			}
		}
		
		return timecardsEmployee;
	}
	
	public Employee getEmployee(int id) {
		for (Employee employee : this.employees) {
			if(employee.getId() == id) {
				return employee;
			} 
		}
		return null;
	}
	
	public boolean isFriday(Date dateToday) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(dateToday);
		
		return (calendar.get(Calendar.DAY_OF_WEEK) == 6);
	}
	
	public ArrayList<Sale> getSale(int id) {
		ArrayList<Sale> salesEmployee = new ArrayList<Sale>();
		
		for (Sale sale : salesEmployee) {
			if (sale.getId() == id) {
				salesEmployee.add(sale);
			}
		}
		
		return salesEmployee;
	}
	
	public ArrayList<Sale> getSales() {
		return this.sales;
	}
	
	public void addSale(Sale sale) {
		this.sales.add(sale);
	}
	
	public void runPayroll() {
		if(isFriday(new Date()) || true) {
			countFridays += 1;
			
			if(countFridays == 2) {				
				for (Employee employee : this.employees) {
					if (employee instanceof Commissioned) {
						System.out.println(employee.getName() + " recebeu " + ((Commissioned) employee).calculateCommission(this.sales));
					}
				}
				countFridays = 0;
			}

			for (Employee employee : this.employees) {
				if (employee instanceof Hourly) {
					System.out.println(employee.getName() + " recebeu " + ((Hourly) employee).calculateSalary(this.timecards));
				}
			}
		}
	}
}
