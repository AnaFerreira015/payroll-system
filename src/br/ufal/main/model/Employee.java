package br.ufal.main.model;

public class Employee {

	private String name, address; 
	private int id, type;
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "\nAddress: " + address + "\nType: " + type + "\nId: " + id + "\n***********";
	}	
	
}
