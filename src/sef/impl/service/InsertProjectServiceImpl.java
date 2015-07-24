package sef.impl.service;

import sef.interfaces.repository.InsertProjectRepository;
import sef.interfaces.service.InsertProjectService;

public class InsertProjectServiceImpl implements InsertProjectService {
	
private InsertProjectRepository projRep;
	
	public InsertProjectServiceImpl(InsertProjectRepository projRep) {
		this.projRep = projRep;
	}

	@Override
	public boolean insertNewProject(String name, String description, String client) {
		// TODO Auto-generated method stub
		return projRep.insertNewProject(name, description, client);
	}

}
