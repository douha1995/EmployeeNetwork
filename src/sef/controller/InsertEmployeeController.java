package sef.controller;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sef.domain.Employee;
import sef.domain.EmployeeSkill;
import sef.domain.Project;
import sef.interfaces.service.InsertEmployeeService;
import sef.interfaces.service.InsertProjectService;
import sef.interfaces.service.InsertSkillService;
import sef.interfaces.service.SearchService;

@Controller
@RequestMapping("/insert/*.htm")
public class InsertEmployeeController {
		
	private static Logger log = Logger.getLogger(InsertEmployeeController.class);
	private InsertEmployeeService insertEmployee;
	private InsertProjectService insertProject;
	private InsertSkillService insertSkill;
	private SearchService searchService;
		
	public InsertEmployeeController(InsertEmployeeService insertEmployee, InsertProjectService insertProject, InsertSkillService insertSkill,
			SearchService searchService) {
		this.insertEmployee = insertEmployee;
			this.insertProject = insertProject;
			this.insertSkill = insertSkill;
			this.searchService = searchService;
		}
		/**
		 * Controller for adding employee into database
		 */
		@RequestMapping(value="insertEmployee.htm", params="action=Insert employee")
		public ModelAndView onSubmitInsertEmployee(
				@RequestParam("firstName") String firstName,
				@RequestParam("lastName") String lastName,
				@RequestParam("middleInitial") String middleInitial,
				@RequestParam("level") String level,
				@RequestParam("workforce") String workforce,
				@RequestParam("enterpriceID") String enterpriceID){
			log.info("Adding First name: " + firstName + ", Last name: " + lastName + 
					", MiddleInitial: " + middleInitial + ", Level: " + level + 
					", Workforce: " + workforce + " and EnterpriseID: " + enterpriceID);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("insertEmployee", insertEmployee.insertNewEmployee(firstName, 
					lastName, middleInitial, level, workforce, enterpriceID));
			List<Project> projectList = searchService.listAllProjects();
			List<EmployeeSkill> skillList = searchService.listAllSkills();
			List<Employee> employeesList = searchService.listAllEmployees();
			mav.addObject("projectList", projectList);
			mav.addObject("skillList", skillList);
			mav.addObject("employeesList", employeesList);
			mav.setViewName("insert/insertEmployee");
			
			return mav;
		}
		/**
		 * Controller for adding employee projects into database
		 */
		@RequestMapping(value="insertEmployee.htm", params="action=Insert employee's project")
		public ModelAndView onSubmitInsertProject(
				@RequestParam("employee_idP") long employee_id,
				@RequestParam("project_id") long project_id,
				@RequestParam("employee_role") String employee_role,
				@RequestParam("start_date") String startDate,
				@RequestParam("end_date") String endDate) {
			log.info("Adding employee_id: " + employee_id + " project_id: " 
				+ project_id + " employee_role: " + employee_role + " start_date: " 
					+ startDate + " end_date: " + endDate);
			
			Date start_date = Date.valueOf(startDate);
			Date end_date = Date.valueOf(endDate);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("insertEmployee", insertEmployee.insertEmployeeProject(employee_id, 
					project_id, employee_role, start_date, end_date));
			List<Project> projectList = searchService.listAllProjects();
			List<EmployeeSkill> skillList = searchService.listAllSkills();
			List<Employee> employeesList = searchService.listAllEmployees();
			mav.addObject("projectList", projectList);
			mav.addObject("skillList", skillList);
			mav.addObject("employeesList", employeesList);
			mav.setViewName("insert/insertEmployee");
			
			return mav;
		}
		/**
		 * Controller for adding employee skills into database
		 */
		@RequestMapping(value="insertEmployee.htm", params="action=Insert employee's skill")
		public ModelAndView onSubmitInsertSkill(
				@RequestParam("employee_idS") long employee_id,
				@RequestParam("skill_id") long skill_id,
				@RequestParam("rating") int rating) {
			log.info("Adding Employee's ID: " + employee_id + 
					", Skill's ID: " + skill_id + " and Rating: " + rating );
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("insertEmployee", insertEmployee.insertEmployeeSkills(employee_id, skill_id, rating));
			List<Project> projectList = searchService.listAllProjects();
			List<EmployeeSkill> skillList = searchService.listAllSkills();
			List<Employee> employeesList = searchService.listAllEmployees();
			mav.addObject("projectList", projectList);
			mav.addObject("skillList", skillList);
			mav.addObject("employeesList", employeesList);
			mav.setViewName("insert/insertEmployee");
			
			return mav;
		}
		/**
		 * Controller for adding new project into database
		 */
		@RequestMapping(value="insertEmployee.htm", params="action=Insert new project")
		public ModelAndView onSubmitInsertProject(
				@RequestParam("nameP") String name,
				@RequestParam("descriptionP") String description,
				@RequestParam("client") String client) {
			log.info("Adding Name: " + name + ", Description: " + description + 
					" and Client: " + client + " to the Project");
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("insertProject", insertProject.insertNewProject(name, description, client));
			List<Project> projectList = searchService.listAllProjects();
			List<EmployeeSkill> skillList = searchService.listAllSkills();
			List<Employee> employeesList = searchService.listAllEmployees();
			mav.addObject("projectList", projectList);
			mav.addObject("skillList", skillList);
			mav.addObject("employeesList", employeesList);
			mav.setViewName("insert/insertEmployee");
			
			return mav;
		}
		/**
		 * Controller for adding new skills into database
		 */
		@RequestMapping(value="insertEmployee.htm", params="action=Insert skill")
		public ModelAndView onSubmitInsertSkill(
				@RequestParam("nameS") String name,
				@RequestParam("descriptionS") String description) {
			log.info("Adding Name: " + name + " and Description: " + description + " to Skills");
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("insertSkill", insertSkill.insertNewSkill(name, description));
			List<Project> projectList = searchService.listAllProjects();
			List<EmployeeSkill> skillList = searchService.listAllSkills();
			List<Employee> employeesList = searchService.listAllEmployees();
			mav.addObject("projectList", projectList);
			mav.addObject("skillList", skillList);
			mav.addObject("employeesList", employeesList);
			mav.setViewName("insert/insertEmployee");
			
			return mav;
		}
		/**
		 * Performs initial state
		 */
		@RequestMapping(value="insertEmployee.htm", params="!searchType")
		public ModelAndView onInitialInsertFormState() {
			List<Project> projectList = searchService.listAllProjects();
			List<EmployeeSkill> skillList = searchService.listAllSkills();
			List<Employee> employeesList = searchService.listAllEmployees();
			log.info("Entering add form in its initial state");
			ModelAndView mav = new ModelAndView();
			mav.addObject("projectList", projectList);
			mav.addObject("skillList", skillList);
			mav.addObject("employeesList", employeesList);
			mav.setViewName("insert/insertEmployee");
			
			return mav;
		}


}
