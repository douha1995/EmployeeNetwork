package sef.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import sef.interfaces.repository.InsertProjectRepository;

public class InsertProjectRepositoryImpl implements InsertProjectRepository{
	
	String name;
	String description;
	
	private static Logger log = Logger.getLogger(InsertProjectRepositoryImpl.class);

	private Connection connection;
	
	public InsertProjectRepositoryImpl(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public boolean insertNewProject(String name, String description, String client) {
		// TODO Auto-generated method stub
		//return false;
		
		try {
			PreparedStatement pstm = connection.prepareStatement("insert into projects (NAME, DESCRIPTION, CLIENT) values (?,?,?)");
			pstm.setString(1, name);
			pstm.setString(2, description);
			pstm.setString(3, client);
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			return false;
		}
		return true;
		
	}
	
	

}
