package br.ufal.main;

import java.util.Date;
import java.util.Scanner;

import br.ufal.main.database.DatabaseConnection;
import br.ufal.main.model.Commissioned;
import br.ufal.main.model.Employee;
import br.ufal.main.model.Hourly;
import br.ufal.main.model.Salaried;
import br.ufal.main.model.Timecard;

public class Main {

	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		Scanner input = new Scanner(System.in);
		int id, pointType, employeeType;
		
		int option;
		
		do {
			System.out.println("------------------------");
			System.out.println("\t0. SAIR");
			System.out.println("\t1. Adicionar empregado");
			System.out.println("\t2. Listar empregados");
			System.out.println("\t3. Remover um empregado");
			System.out.println("\t4. Lançar Cartão de Ponto");
			System.out.println("\t5. Exibir Cartões de Ponto");
			System.out.println("\t6. Mostrar salário");
			option = input.nextInt();
			
			switch (option) {
			case 1:	
				System.out.println("Informe o tipo do empregado: ");
				System.out.println("\t(1) Hourly\t(2) Salaried \t(3) Commissioned");
				int typeEmployee = input.nextInt();
				
				Employee employee = null;
				if(typeEmployee == 1) employee = new Hourly();
				if(typeEmployee == 2) employee = new Salaried();
				if(typeEmployee == 3) employee = new Commissioned();
				
				System.out.println("Informe o nome do empregado: ");
				employee.setName(input.next());
				
				System.out.println("Informe o endereço: ");
				employee.setAddress(input.next());
				
				
				databaseConnection.addEmployee(employee);
				break;

			case 2:
				
				for (Employee employeeIterator : databaseConnection.getEmployees()) {
					System.out.println(employeeIterator.toString());
				}
				break;
				
			case 3:
				System.out.println("Informe o ID do empregado que será removido: ");
				id = input.nextInt();
				
				databaseConnection.removeEmployee(id);
				
				System.out.println("Empregado " + id + " removido com sucesso!");
				break;
				
			case 4:	
				Timecard timecard = new Timecard();
				
				do {
					System.out.println("Informe o ID do empregado horista: ");
					id = input.nextInt();
				} while ((!databaseConnection.employeeExists(id)));

				timecard.setId(id);
				
				if ((databaseConnection.getTimecards(id).size() % 2) == 0) {
					timecard.setType(1);
				} else {
					timecard.setType(2);
				}
								
				// DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				timecard.setDate(date);
				// System.out.println(date);
				
				databaseConnection.addTimecard(timecard);
				
			case 5:
				
				for (Timecard timecardIterator : databaseConnection.getTimecards()) {
					System.out.println(timecardIterator.toString());
				}
				break;
				
			case 6: 
				System.out.println("Informe o ID do funcionário: ");
				int idEmployee = input.nextInt();
				
				double result = ((Hourly) databaseConnection.getEmployee(idEmployee)).calculateSalary(databaseConnection.getTimecards());
				System.out.println("Resultado do salário: " + result);
				break;
			default:
				break;
			}
		} while (option != 0);
		
		
	}

}
