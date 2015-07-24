package sef.impl.service;

import sef.domain.EmployeeDetail;
import sef.interfaces.repository.EmployeeRepository;
import sef.interfaces.repository.ProjectRepository;
import sef.interfaces.repository.SkillRepository;
import sef.interfaces.service.EmployeeDetailsService;

import org.apache.log4j.Logger;

public class StubEmployeeDetailsServiceImpl implements EmployeeDetailsService{
	
	//Tip: create member variables in this class that will contain the objects 
	//passed by the Spring framework so that other methods can access the objects.

	private static Logger log = Logger.getLogger(StubEmployeeDetailsServiceImpl.class);
	
	private EmployeeRepository employee;
	private ProjectRepository project;
	private SkillRepository skills;
	
	public StubEmployeeDetailsServiceImpl(EmployeeRepository empDAO, ProjectRepository projectDAO, SkillRepository skillDAO){
		this.employee = empDAO;
		this.project = projectDAO;
		this.skills = skillDAO;
	}

	@Override
	public EmployeeDetail getEmployeeDetails(long employeeID) {
		
		EmployeeDetail detail = new EmployeeDetail();		
		detail.setEmployee(employee.findEmployeeByID(employeeID));
		detail.setSkillList(skills.findEmployeeSkills(employeeID));
		detail.setProjectList(project.getEmployeeProjectHistory(employeeID));
		
		return detail;
	}
}
