package sef.interfaces.repository;

import java.sql.SQLException;

public interface InsertSkillRepository {
	
	public boolean insertNewSkill(String Name, String Description) throws SQLException;


}
