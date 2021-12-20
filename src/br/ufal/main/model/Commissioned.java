package br.ufal.main.model;

import java.util.ArrayList;
import java.util.Date;

public class Commissioned extends Employee {
	private Date date;
	private double commissionPercentage = 0, commission = 0;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getCommissionPercentage() {
		return commissionPercentage;
	}

	public void setCommissionPercentage(double commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}
	
	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double calculateCommission(ArrayList<Sale> sales) {
		double commission = 0;
		System.out.println("entrou na calculateCommission");
		
		for (Sale sale : sales) {
			System.out.println(sale.getId() + "==" + this.getId());
			if(sale.getId() == this.getId()) {
				System.out.println(sale.getSaleValue() + " * " + this.commissionPercentage);
				commission += sale.getSaleValue() * this.commissionPercentage;
			}
		}
		return commission;
	}
}
