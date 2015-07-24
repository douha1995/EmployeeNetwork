package sef.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import sef.interfaces.repository.InsertSkillRepository;

public class InsertSkillRepositoryImpl implements InsertSkillRepository {
	
	private static Logger log = Logger.getLogger(InsertSkillRepositoryImpl.class);
	private Connection connection;
	
	public InsertSkillRepositoryImpl(DataSource dataSource){
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@Override
	public boolean insertNewSkill(String Name, String Description) throws SQLException {
		// TODO Auto-generated method stub
		//return false;
		
		PreparedStatement pStmt = connection.prepareStatement("INSERT INTO skills (name, description) VALUES(?, ?)");
		pStmt.setString(1, Name);
		pStmt.setString(2, Description);

		if (pStmt.executeUpdate() == 1) {
			return true;
		} else {
			return false;
		}
	}
	

	
	

}
