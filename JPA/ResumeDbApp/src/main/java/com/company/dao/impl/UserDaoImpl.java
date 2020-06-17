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
            if(name != null && !name.trim().isEmpty()){
                sql += " and u.name=?";
            }

            if(surname != null && !surname.trim().isEmpty()){
                sql += " and u.surname=?";
            }

            if(nationalityId != null ){
                sql += " and u.nationality_Id=?";
            }

            PreparedStatement stmt = c.prepareStatement(sql);

            int i = 1;
            if(name != null && !name.trim().isEmpty()){
                stmt.setString(i, name);
                i++;
            }

            if(surname != null && !surname.trim().isEmpty()){
                stmt.setString(i, surname);
                i++;
            }

            if(nationalityId != null){
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
            while (rs.next()){
//                result = getUserSimple(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateUser(User u) {
        try (Connection c = this.connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?, surname=?, phone=?, email=?, profile_description=?, birthdate=?, birthplace_id=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
//            stmt.setDate(6, u.getBirthDate());
            stmt.setInt(7, u.getBirthPlace().getId());
            stmt.setInt(8, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean removeUser(final int id) {
        try (final Connection c = this.connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id = " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public User getById(int userId) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("resumeappPU");
        EntityManager entitymanager = emfactory.createEntityManager(); 
        
        User u = entitymanager.find(User.class, userId);
        return u;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = this.connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name, surname, phone, email, profile_description) values(?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User findByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
