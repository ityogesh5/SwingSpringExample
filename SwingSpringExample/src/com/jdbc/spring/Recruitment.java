package com.jdbc.spring;

public class Recruitment {
	private int day;
	private String FName, LName, Address, Salary;

	public Recruitment(String fName, String lName, String address, String salary) {

		FName = fName;
		LName = lName;
		Address = address;
		Salary = salary;
	}

	public Recruitment() {
		// TODO Auto-generated constructor stub
	}

	public String getFName() {
		return FName;
	}

	public void setFName(String fName) {
		FName = fName;
	}

	public String getLName() {
		return LName;
	}

	public void setLName(String lName) {
		LName = lName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getSalary() {
		return Salary;
	}

	public void setSalary(String salary) {
		Salary = salary;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public String toString() {

		return FName + "\n" + LName + "\n" + Address + "\n" + Salary;
	}

}
