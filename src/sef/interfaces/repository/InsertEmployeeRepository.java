package sef.interfaces.repository;

import java.sql.Date;

public interface InsertEmployeeRepository {
	
	public long insertNewEmployee (String firstName, String lastName, String middleInitial,
								String level, String workForce,	String enterpriseID);	

	public long insertEmployeeSkill(long employeeID, long skillID,	int rating);

	public long insertEmployeeProject(long employeeID,	long projectID,	String employeeRole,
									Date startDate,	Date endDate);

}
