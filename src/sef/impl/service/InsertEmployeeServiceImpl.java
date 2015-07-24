package sef.impl.service;

import java.sql.Date;

import sef.interfaces.repository.InsertEmployeeRepository;
import sef.interfaces.service.InsertEmployeeService;

public class InsertEmployeeServiceImpl implements InsertEmployeeService {
	
	private InsertEmployeeRepository employee;
	private InsertEmployeeRepository project;
	private InsertEmployeeRepository skill;
	
	public InsertEmployeeServiceImpl(InsertEmployeeRepository empDAO, InsertEmployeeRepository projectDAO, 
			InsertEmployeeRepository skDAO) {
		this.employee = empDAO;
		this.project = projectDAO;
		this.skill = skDAO;
	}

	@Override
	public long insertNewEmployee(String FirstName, String LastName, String middleInitial, String level,
			String Workforce, String EnterpriceID) {
		// TODO Auto-generated method stub
		//return 0;
		return employee.insertNewEmployee(FirstName, LastName, middleInitial, level, Workforce, EnterpriceID);
		
	}

	@Override
	public long insertEmployeeSkills(long emplyee_id, long skill_id, int rating) {
		// TODO Auto-generated method stub
		return skill.insertEmployeeSkill(emplyee_id, skill_id, rating);
	}

	@Override
	public long insertEmployeeProject(long employee_id, long project_id, String employee_role, Date start_date,
			Date end_date) {
		// TODO Auto-generated method stub
		return project.insertEmployeeProject(employee_id, project_id, employee_role, start_date, end_date);
	}

}
