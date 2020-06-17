package com.company.main;

import com.company.dao.inter.UserDaoInter;

public class Main {

    public static void main(final String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        UserDaoInter udi = Context.instanceUserDao();
    }
}
