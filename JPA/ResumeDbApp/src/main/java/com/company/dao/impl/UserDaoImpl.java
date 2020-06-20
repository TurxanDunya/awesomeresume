package com.company.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import com.company.entity.User;
import java.util.List;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.AbstractDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> result = new ArrayList<User>();
        try (Connection c = this.connect()) {

            String sql = "select "
                    + " u.*, "
                    + " n.nationality, "
                    + " c.name as birthplace "
                    + " from user u "
                    + " left join country n on u.nationality_id = n.id "
                    + " left join country c on u.birthplace_id = c.id where 1=1";
            if (name != null && !name.trim().isEmpty()) {
                sql += " and u.name=?";
            }

            if (surname != null && !surname.trim().isEmpty()) {
                sql += " and u.surname=?";
            }

            if (nationalityId != null) {
                sql += " and u.nationality_Id=?";
            }

            PreparedStatement stmt = c.prepareStatement(sql);

            int i = 1;
            if (name != null && !name.trim().isEmpty()) {
                stmt.setString(i, name);
                i++;
            }

            if (surname != null && !surname.trim().isEmpty()) {
                stmt.setString(i, surname);
                i++;
            }

            if (nationalityId != null) {
                stmt.setInt(i, nationalityId);
            }

            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
//                User u = getUser(rs);
//                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;

        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=? and password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
//                result = getUserSimple(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateUser(User u) {
        EntityManager em = em();

        em.getTransaction().begin(); //transactioni achiriq
        em.merge(u);
        em.getTransaction().commit(); //baglayirig

        em.close();
        return true;
    }

    public boolean removeUser(final int id) {
        EntityManager em = em();

        User u = em.find(User.class, id); //id obyekt istediyi uchun

        em.getTransaction().begin(); //transactioni achiriq
        em.remove(u);
        em.getTransaction().commit(); //baglayirig

        em.close();
        return true;
    }

    public User getById(int userId) {
        EntityManager em = em();

        User u = em.find(User.class, userId);

        em.close();
        return u;
    }

    //private static final BCrypt.Hasher crypt = BCrypt.withDefaults();
    @Override
    public boolean addUser(User u) {
        //u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));

        EntityManager em = em();

        em.getTransaction().begin(); //transactioni achiriq
        em.persist(u);
        em.getTransaction().commit(); //baglayirig

        em.close();
        return true;
    }

    @Override
    public User findByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
