package sef.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import sef.domain.EmployeeSkill;
import sef.interfaces.repository.SkillRepository;

import org.apache.log4j.Logger;


public class StubSkillRepositoryImpl implements SkillRepository{

	//DataSource class encapsulates the driver, database url, username and 
	//password information.  The dataSource object is automatically created by 
	//the Spring framework and passed to the constructor therefore there's no need 
	//to instantiate the dataSource variable. A connection can be acquired by 
	//accessing the getConnection method of dataSource. 
	//
	//Tip: create member variables in this class that will contain the objects 
	//passed by the Spring framework so that other methods can access the objects.
		
	private static Logger log = Logger.getLogger(StubSkillRepositoryImpl.class);
	
	private Connection connection;

	public StubSkillRepositoryImpl(DataSource dataSource){
		try {
			this.connection = dataSource.getConnection();
			System.out.println("Connection established");
		} catch (SQLException e) {

			System.out.println("Connection is not established");
			e.printStackTrace();
		}
	}

	@Override
	public List<EmployeeSkill> findEmployeeSkills(long employeeID) {

List<EmployeeSkill> list = new ArrayList<EmployeeSkill>();
		
		try {
			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM skills JOIN employee_skill_map ON skills.ID=employee_skill_map.skill_id WHERE employee_id=?");
			pstm.setLong(1, employeeID);
			ResultSet rset = pstm.executeQuery();
			
			while(rset.next()){
				EmployeeSkill skill = new EmployeeSkill();
				skill.setID(rset.getLong("ID"));
				skill.setName(rset.getString("NAME"));
				skill.setDescription(rset.getString("DESCRIPTION"));
				skill.setRating(rset.getInt("RATING"));
				list.add(skill);
			}
			
			rset.close();
			pstm.close();

		} catch (SQLException e) {
			System.out.println("Query is not established");
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<EmployeeSkill> listAllSkills() {
		List<EmployeeSkill> skills = new ArrayList<EmployeeSkill>();
		try {
			PreparedStatement pstm = connection.prepareStatement("SELECT * from skills");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				EmployeeSkill skill = new EmployeeSkill();
				skill.setID(rs.getInt("ID"));
				skill.setName(rs.getString("NAME"));
				skill.setDescription(rs.getString("DESCRIPTION"));
				skills.add(skill);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return skills;
	}
	
	
	


	

}
