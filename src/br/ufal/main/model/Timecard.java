package br.ufal.main.model;

import java.util.Date;

public class Timecard {
	private Date date;
	private int idEmployee, type;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId() {
		return idEmployee;
	}
	public void setId(int id) {
		this.idEmployee = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Date: " + date + "\nID: " + idEmployee + "\nType: " + type;
	}

}
