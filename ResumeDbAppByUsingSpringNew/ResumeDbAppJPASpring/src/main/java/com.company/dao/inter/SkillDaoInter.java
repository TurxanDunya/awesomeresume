package com.company.dao.inter;

import com.company.entity.Skill;

import java.util.List;

public interface SkillDaoInter {

    List<Skill> getAll();

    public Skill getById(int id);
    
    boolean updateSkill(Skill u);
    
    boolean removeSkill(int id);
    
    public List<Skill> getByName(String name);
    
    public boolean insertSkill(Skill skl);
}
