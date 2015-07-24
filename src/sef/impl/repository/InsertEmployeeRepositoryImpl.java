package sef.impl.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import sef.interfaces.repository.InsertEmployeeRepository;

public class InsertEmployeeRepositoryImpl implements InsertEmployeeRepository {
	
	private static Logger log = Logger.getLogger(InsertEmployeeRepositoryImpl.class);
	private Connection connection;
	
	public InsertEmployeeRepositoryImpl(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private long insert(PreparedStatement st) {
		long newID = -1;
		
		try {
			int rows = st.executeUpdate();
			connection.commit();			
			if (rows == 1) {
				PreparedStatement st2 = connection.prepareStatement(
						"SELECT last_insert_id()");				
				ResultSet rs = st2.executeQuery();
				if (rs.next()) {
					newID = rs.getInt(1);
				}				
				rs.close();
			}
		}
		catch (SQLException e) {
			log.error(e.getMessage(), e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error(e.getMessage(), e1);
			}
		}		
		return newID;
	}
	
	@Override
	public long insertNewEmployee(String firstName, String lastName,
			String middleInitial, String level, String workForce,
			String enterpriseID) {
		PreparedStatement st = null;
		long newID = -1;
		
		try
		{
			st = connection.prepareStatement(
				"INSERT INTO employees " +
				"(FIRST_NAME, LAST_NAME, MIDDLE_INITIAL, LEVEL, WORKFORCE, ENTERPRISE_ID) " +
				"VALUES (?,?,?,?,?,?)");
			st.setString(1, firstName);
			st.setString(2, lastName);
			st.setString(3, middleInitial);
			st.setString(4, level);
			st.setString(5, workForce);
			st.setString(6, enterpriseID);
			
			newID = insert(st);
		}
		catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		
		return newID;
	}

	@Override
	public long insertEmployeeSkill(long employeeID, long skillID, int rating) {
		// TODO Auto-generated method stub
		//return 0;
		
		PreparedStatement st = null;
		long newID = -1;
		try {
			st = connection.prepareStatement(
					"INSERT INTO employee_skill_map " +
					"(employee_id, skill_id, rating) " +
					"VALUES (?,?,?)");
			st.setLong(1, employeeID);
			st.setLong(2, skillID);
			st.setLong(3, rating);			
			newID = insert(st);
		}
		catch (SQLException e) {
			log.error(e.getMessage(), e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error(e.getMessage(), e1);
			}
		}
		finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		
		return newID;
	}

	@Override
	public long insertEmployeeProject(long employeeID, long projectID, String employeeRole, Date startDate,
			Date endDate) {
		// TODO Auto-generated method stub
		//return 0;
		
		PreparedStatement st = null;
		long newID = -1;
		
		try {
			st = connection.prepareStatement(
					"INSERT INTO employee_project_map " +
					"(EMPLOYEE_ID, PROJECT_ID, EMPLOYEE_ROLE, START_DATE, END_DATE) " +
					"VALUES (?,?,?,?,?)");
			st.setLong(1, employeeID);
			st.setLong(2, projectID);
			st.setString(3, employeeRole);
			st.setDate(4, startDate);
			st.setDate(5, endDate);
			
			newID = insert(st);
		}
		catch (SQLException e) {
			log.error(e.getMessage(), e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error(e.getMessage(), e1);
			}
		}
		finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					log.error(e.getMessage(), e);
				}
			}
		}		
		return newID;		
	}

}
