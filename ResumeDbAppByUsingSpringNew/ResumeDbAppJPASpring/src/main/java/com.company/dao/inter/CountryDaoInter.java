package com.company.dao.inter;

import com.company.entity.Country;
import com.company.entity.Skill;

import java.util.List;

public interface CountryDaoInter {

    List<Country> getAll();

    public Country getById(int id);

    boolean updateCountry(Skill u);

    boolean removeCountry(int id);

    public List<Country> getByName(String name);

    public boolean insertCountry(Skill skl);

}
