//package com.company;
//
//import com.company.dao.impl.UserDaoImpl;
//import com.company.entity.User;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class UserDaoImpl2IntegrationTest{
//
//    @Autowired
//    UserDaoImpl userDao;
//
//    @Before
//    public void before(){
//
//    }
//
//    @Test
//    public void testGetAll(){
//        List<User> list = userDao.getAll(null, null, null);
//        System.out.println("list= " + list);
//    }
//}