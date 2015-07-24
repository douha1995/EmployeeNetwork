package sef.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import sef.domain.Project;
import sef.domain.EmployeeProjectDetail;
import sef.domain.ProjectRole;
import sef.interfaces.repository.ProjectRepository;

import org.apache.log4j.Logger;

public class StubProjectRepositoryImpl implements ProjectRepository {

	//DataSource class encapsulates the driver, database url, username and 
	//password information.  The dataSource object is automatically created by 
	//the Spring framework and passed to the constructor therefore there's no need 
	//to instantiate the dataSource variable. A connection can be acquired by 
	//accessing the getConnection method of dataSource. 
	//
	//Tip: create member variables in this class that will contain the objects 
	//passed by the Spring framework so that other methods can access the objects.

	private static Logger log = Logger.getLogger(StubProjectRepositoryImpl.class);

	private Connection connection;

	public StubProjectRepositoryImpl(DataSource dataSource) {
		//this.dataSource = dataSource;
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public List<Project> listAllProjects() {

		List<Project> projects = new ArrayList<Project>();		
		try {
			PreparedStatement st = connection.prepareStatement(
					"select * from projects");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Project p = new Project();
				p.setID(rs.getInt("ID"));
				p.setName(rs.getString("NAME"));
				p.setDescription(rs.getString("DESCRIPTION"));
				p.setClient(rs.getString("CLIENT"));
				projects.add(p);
			}
			
			st.close();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		
		return projects;
	}

	@Override
	public List<EmployeeProjectDetail> getEmployeeProjectHistory(long employeeID) {
		
		List<EmployeeProjectDetail> detailList = new ArrayList<EmployeeProjectDetail>();
		List<Project> projs = getEmployeeProjects(employeeID);
		
		for (int i = 0; i < projs.size(); i++) {
			List<ProjectRole> roles = getEmployeeProjectRoles(employeeID, projs.get(i).getID());
			EmployeeProjectDetail det = new EmployeeProjectDetail();
			det.setProject(projs.get(i));
			det.setProjectRoles(roles);
			detailList.add(det);
		}
		return detailList;
	}

	@Override
	public List<ProjectRole> getEmployeeProjectRoles(long employeeID,
			long projectID) {
		
List<ProjectRole> roles = new ArrayList<ProjectRole>();
		
		try {
			PreparedStatement st = connection.prepareStatement(
					"SELECT ID, EMPLOYEE_ROLE, START_DATE, END_DATE " +
					"FROM employee_project_map " +
					"WHERE EMPLOYEE_ID=? AND PROJECT_ID=?");
			st.setLong(1, employeeID);
			st.setLong(2, projectID);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				ProjectRole r = new ProjectRole();
				r.setID(rs.getInt("ID"));
				r.setRole(rs.getString("EMPLOYEE_ROLE"));
				r.setStartDate(rs.getDate("START_DATE"));
				r.setEndDate(rs.getDate("END_DATE"));
				roles.add(r);
			}
			
			st.close();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		
		return roles;
	}

	@Override
	public List<Project> getEmployeeProjects(long employeeID) {
List<Project> projects = new ArrayList<Project>();
		
		try {
			PreparedStatement st = connection.prepareStatement(
					"SELECT DISTINCT projects.ID, NAME, DESCRIPTION, CLIENT " +
					"FROM projects " +
					"JOIN employee_project_map ON employee_project_map.PROJECT_ID=projects.ID " +
					"WHERE employee_project_map.EMPLOYEE_ID=?");

			st.setLong(1, employeeID);

			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Project p = new Project();
				p.setID(rs.getInt("ID"));
				p.setName(rs.getString("NAME"));
				p.setDescription(rs.getString("DESCRIPTION"));
				p.setClient(rs.getString("CLIENT"));
				projects.add(p);
			}			
			rs.close();
			st.close();			
			st.close();

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		
		return projects;
	}

}
