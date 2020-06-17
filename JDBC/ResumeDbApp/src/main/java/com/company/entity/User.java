package com.company.entity;

import java.sql.Date;
import java.util.List;

public class User {

    private int id;
    private String name;
    private String surname;
    private String phone;
    private String profileDesc;
    private String email;
    private Date birthDate;
    private Country nationality;
    private Country birtPlace;
    private List<UserSkill> skills;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String surname, String phone, String email,String profileDesc, Date birthDate, Country nationality, Country birtPlace) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.birtPlace = birtPlace;
        this.profileDesc = profileDesc;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Country getBirtPlace() {
        return birtPlace;
    }

    public void setBirtPlace(Country birtPlace) {
        this.birtPlace = birtPlace;
    }

    public List<UserSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<UserSkill> skills) {
        this.skills = skills;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", phone=" + phone + ", profileDesc=" + profileDesc + ", email=" + email + ", birthDate=" + birthDate + ", nationality=" + nationality + ", birtPlace=" + birtPlace + ", skills=" + skills + '}';
    }

    

}
