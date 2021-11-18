package br.ufal.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import br.ufal.main.database.DatabaseConnection;
import br.ufal.main.model.Employee;
import br.ufal.main.model.Timecard;

public class Main {

	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		Scanner input = new Scanner(System.in);
		Timecard timecard = new Timecard();
		int id, pointType, employeeType;
		
		int option;
		
		do {
			System.out.println("------------------------");
			System.out.println("\t0. SAIR");
			System.out.println("\t1. Adicionar empregado");
			System.out.println("\t2. Listar empregados");
			System.out.println("\t3. Remover um empregado");
			System.out.println("\t4. Lan�ar Cart�o de Ponto");
			option = input.nextInt();
			
			switch (option) {
			case 1:	
				Employee employee = new Employee();
				
				System.out.println("Informe o nome do empregado: ");
				employee.setName(input.next());
				
				System.out.println("Informe o endere�o: ");
				employee.setAddress(input.next());
				
				System.out.println("Informe o tipo do empregado: ");
				System.out.println("\t(1) Hourly\t(2) Salaried \t(3) Commissioned");
				employee.setType(input.nextInt());
				
				databaseConnection.addEmployee(employee);
				break;

			case 2:
				
				for (Employee employeeIterator : databaseConnection.getEmployees()) {
					System.out.println(employeeIterator.toString());
				}
				break;
				
			case 3:
				System.out.println("Informe o ID do empregado que ser� removido: ");
				id = input.nextInt();
				
				databaseConnection.removeEmployee(id);
				
				System.out.println("Empregado " + id + " removido com sucesso!");
				break;
				
			case 4:	
				
				do {
					System.out.println("Informe o ID do empregado horista: ");
					id = input.nextInt();
				} while ((!databaseConnection.employeeExists(id)) && (!databaseConnection.isHourly(id)));

				timecard.setId(id);
				
				do {
					System.out.println("Informe se o ponto � de entrada (1) ou sa�da (2): ");
					pointType = input.nextInt();
				} while ((pointType != 1) && (pointType != 2));
				
				timecard.setType(pointType);
				
				// DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				timecard.setDate(date);
				// System.out.println(date);
				
				databaseConnection.addTimecard(timecard);
				
			default:
				break;
			}
		} while (option != 0);
		
		
	}

}
