package br.ufal.main;

import java.util.Scanner;

import br.ufal.main.database.DatabaseConnection;
import br.ufal.main.model.Employee;

public class Main {

	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		Scanner input = new Scanner(System.in);
		
		int option;
		
		do {
			System.out.println("------------------------");
			System.out.println("\t0. SAIR");
			System.out.println("\t1. Adicionar empregado");
			System.out.println("\t2. Listar empregados");
			option = input.nextInt();
			
			switch (option) {
			case 1:
				Employee employee = new Employee();
				
				System.out.println("Informe o nome do empregado: ");
				employee.setName(input.next());
				
				System.out.println("Informe o endereço: ");
				employee.setAddress(input.next());
				
				System.out.println("Informe o tipo do empregado: ");
				System.out.println("\t- Hourly\t- Salaried \t- Commissioned");
				employee.setType(input.next());
				
				databaseConnection.addEmployee(employee);
				break;

			case 2:
				
				for (Employee employeeIterator : databaseConnection.getEmployees()) {
					System.out.println(employeeIterator.toString());
				}
				break;
			default:
				break;
			}
		} while (option != 0);
		
		
	}

}
