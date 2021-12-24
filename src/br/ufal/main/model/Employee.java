package br.ufal.main.model;

public class Employee {

	private String name, address; 
	private int id;
	private Schedule schedule;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "\nAddress: " + address + "\nId: " + id + "\n***********";
	}	
	
}
