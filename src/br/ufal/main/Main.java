package br.ufal.main;

import java.util.Date;
import java.util.Scanner;

import br.ufal.main.database.DatabaseConnection;
import br.ufal.main.model.Commissioned;
import br.ufal.main.model.Employee;
import br.ufal.main.model.Hourly;
import br.ufal.main.model.Salaried;
import br.ufal.main.model.Sale;
import br.ufal.main.model.Timecard;

public class Main {

	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		Scanner input = new Scanner(System.in);
		int id, pointType, employeeType;
		double resultSalary = 0, saleValue = 0;
		
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
			System.out.println("\t7. Lançar resultado de venda");
			System.out.println("\t8. Visualizar os resultados de vendas");
			System.out.println("\t9. Rodar a folha de pagamento");
			option = input.nextInt();
			
			switch (option) {
			case 1:	
				System.out.println("Informe o tipo do empregado: ");
				System.out.println("\t(1) Hourly\t(2) Salaried \t(3) Commissioned");
				int typeEmployee = input.nextInt();
				
				Employee employee = null;
				if(typeEmployee == 1) employee = new Hourly();
				if(typeEmployee == 2) employee = new Salaried();
				if(typeEmployee == 3) {
					employee = new Commissioned();
					System.out.println("Informe o percentual da comissão: ");
					((Commissioned) employee).setCommissionPercentage(input.nextDouble());
				}
				
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
				timecard.setDate(new Date());
				// System.out.println(date);
				
				databaseConnection.addTimecard(timecard);
				
			case 5:
				
				for (Timecard timecardIterator : databaseConnection.getTimecards()) {
					System.out.println(timecardIterator.toString());
				}
				break;
				
			case 6: 
				System.out.println("Informe o ID do funcionário: ");
				id = input.nextInt();
				
				resultSalary = ((Hourly) databaseConnection.getEmployee(id)).calculateSalary(databaseConnection.getTimecards());
				System.out.println("Resultado do salário: " + resultSalary);
				break;
				
			case 7: 
				Sale sale = new Sale();
				
				do {
					System.out.println("Informe o ID do empregado comissionado: ");
					id = input.nextInt();
				} while (!databaseConnection.employeeExists(id) || !databaseConnection.isCommissioned(id));
				
				Commissioned commissioned = (Commissioned) databaseConnection.getEmployee(id);
				sale.setId(id);
				
				
				System.out.println("Informe o valor da venda: ");				
				sale.setSaleValue(input.nextDouble());
				
				sale.setDate(new Date());
				
				System.out.println("Informe o método de pagamento preferido: ");
				System.out.println("\t1. Cheque pelos correios\t2. Cheque em mãos\t3. Depósito em conta bancária");
				sale.setPaymentMethod(input.nextInt());
				
				databaseConnection.addSale(sale);
				
				double commissionAmount = commissioned.calculateCommission(databaseConnection.getSales());
				
				System.out.println("Valor da comissão: " + commissionAmount);
				break;
				
			case 8:
				for (Sale saleIterator : databaseConnection.getSales()) {
					System.out.println(saleIterator.toString());
				}
				break;
				
			case 9:
				System.out.println("Rodando a folha de pagamento...");
				databaseConnection.runPayroll();
				break;
			default:
				break;
			}
		} while (option != 0);
		
		
	}

}
