package sef.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "employee_entities")
public class EmployeeEntity {

	//@Id @GeneratedValue
	//@Column(name = "id" insert="false" update="false")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ID;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "middleInitial")
	private String middleInitial;
	
	@Column(name = "level")
	private String level;
	
	@Column(name = "workForce")
	private String workForce;
	
	@Column(name = "enterpriseID")
	private String enterpriseID;

	
	public EmployeeEntity(){}
	
	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getEnterpriseID() {
		return enterpriseID;
	}

	public void setEnterpriseID(String enterpriseID) {
		this.enterpriseID = enterpriseID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getWorkForce() {
		return workForce;
	}

	public void setWorkForce(String workForce) {
		this.workForce = workForce;
	}

}