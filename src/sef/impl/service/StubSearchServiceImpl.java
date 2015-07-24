package sef.impl.service;

import java.util.ArrayList;
import java.util.List;

import sef.domain.Employee;
import sef.domain.EmployeeSkill;
import sef.domain.Project;
import sef.interfaces.repository.EmployeeRepository;
import sef.interfaces.repository.ProjectRepository;
import sef.interfaces.repository.SkillRepository;
import sef.interfaces.service.SearchService;

import org.apache.log4j.Logger;

public class StubSearchServiceImpl implements SearchService {

	//Tip: create member variables in this class that will contain the objects 
	//passed by the Spring framework so that other methods can access the objects.

	private static Logger log = Logger.getLogger(StubSearchServiceImpl.class);
	
	private EmployeeRepository employee;
	private ProjectRepository project;
	private SkillRepository skill;

	public StubSearchServiceImpl(EmployeeRepository empDAO,
			ProjectRepository projectDAO, SkillRepository skillDAO) {
		this.employee = empDAO;
		this.project = projectDAO;
		this.skill = skillDAO;
	}

	@Override
	public List<Employee> findEmployeesByName(String firstName, String lastName) {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.addAll(employee.findEmployeesByName(firstName, lastName));
			return employeeList;
	}

	@Override
	public List<Employee> findEmployeesByProject(long projectID) {		
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.addAll(employee.findEmployeesByProject(projectID));
		return employeeList;
	}

	@Override
	public List<Project> listAllProjects() {		
		List<Project> projectList = new ArrayList<Project>();
		projectList.addAll(project.listAllProjects());
		return projectList;
	}
	
	@Override
	public List<EmployeeSkill> listAllSkills(){
		List<EmployeeSkill> skillList = new ArrayList<EmployeeSkill>();
		
		skillList.addAll(skill.listAllSkills());
		return skillList;
 	}

	@Override
	public List<Employee> listAllEmployees(){
		List<Employee> employeeList = new ArrayList<Employee>();
		
		employeeList.addAll(employee.listAllEmployees());
		return employeeList;
 	}

}
