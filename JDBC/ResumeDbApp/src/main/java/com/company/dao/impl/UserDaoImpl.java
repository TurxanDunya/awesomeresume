package com.company.dao.impl;

import com.company.entity.Country;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import com.company.entity.User;
import java.util.List;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.AbstractDAO;
import java.sql.Date;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String phone = rs.getString("phone");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);

        return new User(id, name, surname, phone, email, profileDesc, birthdate, nationality, birthplace);
    }

    private User getUserSimple(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String phone = rs.getString("phone");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        Date birthdate = rs.getDate("birthdate");

        return new User(id, name, surname, phone, email, profileDesc, birthdate, null, null);
    }

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
                User u = getUser(rs);
                result.add(u);
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
                result = getUserSimple(rs);
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
            stmt.setDate(6, u.getBirthDate());
            stmt.setInt(7, u.getBirtPlace().getId());
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
        User result = null;
        try (Connection c = this.connect()) {
            Statement stmt = c.createStatement();
            stmt.execute(" SELECT "
                    + " u.*, "
                    + " n.nationality, "
                    + " c.name as birthplace "
                    + " FROM "
                    + " USER u "
                    + " LEFT JOIN country n ON u.nationality_id = n.id "
                    + " LEFT JOIN country c ON u.birthplace_id = c.id where u.id ");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
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
}
