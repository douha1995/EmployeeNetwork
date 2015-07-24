package sef.interfaces.service;

import java.sql.Date;

public interface InsertEmployeeService {
	
	public long insertNewEmployee(String FirstName, String LastName, String middleInitial, 
			String level, String Workforce, String EnterpriceID);
	
	public long insertEmployeeSkills(long emplyee_id, long skill_id, int rating);

	public long insertEmployeeProject(long employee_id, long project_id, String employee_role, 
			Date start_date, Date end_date);

}
