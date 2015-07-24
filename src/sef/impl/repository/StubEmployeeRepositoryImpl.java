package sef.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import sef.domain.Employee;
import sef.interfaces.repository.EmployeeRepository;

import org.apache.log4j.Logger;

public class StubEmployeeRepositoryImpl implements EmployeeRepository {


	//DataSource class encapsulates the driver, database url, username and 
	//password information.  The dataSource object is automatically created by 
	//the Spring framework and passed to the constructor therefore there's no need 
	//to instantiate the dataSource variable. A connection can be acquired by 
	//accessing the getConnection method of dataSource. 
	//
	//Tip: create member variables in this class that will contain the objects 
	//passed by the Spring framework so that other methods can access the objects.

	private static Logger log = Logger.getLogger(StubEmployeeRepositoryImpl.class);
	
	private Connection connection;	
	
	public StubEmployeeRepositoryImpl(DataSource dataSource) {
		try {
			connection=dataSource.getConnection();
		}
		catch(SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public List<Employee> findEmployeesByName(String fn, String ln) {		

		List<Employee> list = new ArrayList<Employee>();
		
		String firstName = fn.trim();
		String lastName = ln.trim();
		String query="SELECT * FROM employees WHERE ";
		
		PreparedStatement prep;
		
		try {
			if(firstName=="" && lastName=="" ) {
				query = "Select * from employees";
				prep = connection.prepareStatement(query);
			}
			else if (firstName=="" && lastName!="") {
				query+="LAST_NAME=?";
				prep=connection.prepareStatement(query);
				prep.setString(1, lastName);
			}
			else if(firstName!="" && lastName=="") {
				query+="FIRST_NAME=?";
				prep=connection.prepareStatement(query);
				prep.setString(1, firstName);
			}
			else {
				query+="FIRST_NAME=? AND LAST_NAME=?";
				prep=connection.prepareStatement(query);
				prep.setString(1, firstName);
				prep.setString(2, lastName);
			}
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Employee temp = new Employee ();
				temp.setID(rs.getLong("ID"));
				temp.setFirstName(rs.getString("FIRST_NAME"));
				temp.setLastName(rs.getString("LAST_NAME"));
				temp.setMiddleInitial(rs.getString("MIDDLE_INITIAL"));
				temp.setLevel(rs.getString("LEVEL"));
				temp.setWorkForce(rs.getString("WORKFORCE"));
				temp.setEnterpriseID(rs.getString("ENTERPRISE_ID"));
				list.add(temp);
			}
			rs.close();
			prep.close();		
		}
		catch(Exception e) {
			log.error(e.getMessage(), e);			
		}	

		return list;
		
	}

	@Override
	public Employee findEmployeeByID(long employeeID) {
		
		Employee employee = null;
		try {

		PreparedStatement prep = connection.prepareStatement("SELECT * FROM employees" 
				+ " WHERE ID=?");
		prep.setLong(1,employeeID);
		ResultSet rs = prep.executeQuery();
		while(rs.next()) {
			employee = new Employee();
			employee.setID(rs.getLong("ID"));
			employee.setFirstName(rs.getString("FIRST_NAME"));
			employee.setLastName(rs.getString("LAST_NAME"));
			employee.setMiddleInitial(rs.getString("MIDDLE_INITIAL"));
			employee.setLevel(rs.getString("LEVEL"));
			employee.setWorkForce(rs.getString("WORKFORCE"));
			employee.setEnterpriseID(rs.getString("ENTERPRISE_ID"));						
		}
		rs.close();
		prep.close();		
		}
		catch(Exception e) {
			log.error(e.getMessage(), e);			
		}
		
		return employee;
	}

	@Override
	public List<Employee> findEmployeesByProject(long projectID) {

		List<Employee> list = new ArrayList<Employee>();
		
		try {
			String sql = "SELECT *  FROM employees AS E, "
					+"employee_project_map AS M"
					+" WHERE M.EMPLOYEE_ID=E.ID AND M.PROJECT_ID=?";
			
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setLong(1, projectID);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				Employee tmp = new Employee();
				tmp.setID(rs.getLong("ID"));
				tmp.setFirstName(rs.getString("FIRST_NAME"));
				tmp.setLastName(rs.getString("LAST_NAME"));
				tmp.setMiddleInitial(rs.getString("MIDDLE_INITIAL"));
				tmp.setLevel(rs.getString("LEVEL"));
				tmp.setWorkForce(rs.getString("WORKFORCE"));
				tmp.setEnterpriseID(rs.getString("ENTERPRISE_ID"));
				list.add(tmp);
			}
			rs.close();
			prep.close();			
		}
		catch(Exception e) {
			log.error(e.getMessage(), e);			
		}
		return list;
	}
	
	@Override
	public List<Employee> listAllEmployees(){
		List<Employee> employeesList = new ArrayList<Employee>();
		try {
			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM employees");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Employee tmp = new Employee();
				tmp.setID(rs.getLong("ID"));
				tmp.setFirstName(rs.getString("FIRST_NAME"));
				tmp.setLastName(rs.getString("LAST_NAME"));
				tmp.setMiddleInitial(rs.getString("MIDDLE_INITIAL"));
				tmp.setLevel(rs.getString("LEVEL"));
				tmp.setWorkForce(rs.getString("WORKFORCE"));
				tmp.setEnterpriseID(rs.getString("ENTERPRISE_ID"));
				employeesList.add(tmp);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e){
			log.error(e.getMessage(), e);
		}
		return employeesList;
	}


}
