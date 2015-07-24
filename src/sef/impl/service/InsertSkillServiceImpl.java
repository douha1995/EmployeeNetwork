package sef.impl.service;

import java.sql.SQLException;

import sef.interfaces.repository.InsertSkillRepository;
import sef.interfaces.service.InsertSkillService;

public class InsertSkillServiceImpl implements InsertSkillService{
	
	private InsertSkillRepository skillRep;
	
	public InsertSkillServiceImpl(InsertSkillRepository skillRep){
		this.skillRep = skillRep;
	}

	@Override
	public boolean insertNewSkill(String name, String description) {
		try {
			skillRep.insertNewSkill(name, description);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}return true;
	}

}
