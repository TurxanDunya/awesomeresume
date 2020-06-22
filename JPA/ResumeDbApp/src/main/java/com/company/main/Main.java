package com.company.main;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;

public class Main {

    public static void main(final String[] args) throws Exception {
        UserDaoInter dao = Context.instanceUserDao();
        
        User u = dao.findByEmail("turxan.dunya97@gmail.com");
        System.out.println("u= " + u);
    }
}
