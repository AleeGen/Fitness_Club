package com.example.fitnessclub.model.entity;

import java.sql.Date;

public class User extends AbstractEntity {

    private UserRole role;
    private String login;
    private String password;
    private String mail;
    private String name;
    private String lastname;
    private Date dateBirth;
    private String sex;
    private String phone;
    private boolean corporate;
    private short visitPeriodDays;
    private String numberCard;
    private String pathAvatar;
    private String aboutMe;
    private boolean isBlocked;
    private short cash;

    public static UserBuilder newBuilder() {
        return new User().new UserBuilder();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public short getVisitPeriodDays() {
        return visitPeriodDays;
    }

    public void setVisitPeriodDays(short visitPeriodDays) {
        this.visitPeriodDays = visitPeriodDays;
    }

    public boolean isCorporate() {
        return corporate;
    }

    public void setCorporate(boolean corporate) {
        this.corporate = corporate;
    }

    public String getPathAvatar() {
        return pathAvatar;
    }

    public void setPathAvatar(String pathAvatar) {
        this.pathAvatar = pathAvatar;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public short getCash() {
        return cash;
    }

    public void setCash(short cash) {
        this.cash = cash;
    }

    public class UserBuilder {

        public UserBuilder setId(Long userId) {
            User.this.setId(userId);
            return this;
        }

        public UserBuilder setLogin(String login) {
            User.this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public UserBuilder setRole(UserRole role) {
            User.this.role = role;
            return this;
        }

        public UserBuilder setMail(String mail) {
            User.this.mail = mail;
            return this;
        }

        public UserBuilder setName(String name) {
            User.this.name = name;
            return this;
        }

        public UserBuilder setLastname(String lastname) {
            User.this.lastname = lastname;
            return this;
        }

        public UserBuilder setDateBirth(Date dateBirth) {
            User.this.dateBirth = dateBirth;
            return this;
        }

        public UserBuilder setSex(String sex) {
            User.this.sex = sex;
            return this;
        }

        public UserBuilder setPhone(String phone) {
            User.this.phone = phone;
            return this;
        }

        public UserBuilder setNumberCard(String numberCard) {
            User.this.numberCard = numberCard;
            return this;
        }

        public UserBuilder setCorporate(boolean corporate) {
            User.this.corporate = corporate;
            return this;
        }

        public UserBuilder setVisitPeriodDays(short visitPeriodDays) {
            User.this.visitPeriodDays = visitPeriodDays;
            return this;
        }

        public UserBuilder setPathAvatar(String pathAvatar) {
            User.this.pathAvatar = pathAvatar;
            return this;
        }

        public UserBuilder setAboutMe(String aboutMe) {
            User.this.aboutMe = aboutMe;
            return this;
        }

        public UserBuilder setBlocked(boolean isBlocked) {
            User.this.isBlocked = isBlocked;
            return this;
        }

        public UserBuilder setCash(short cash) {
            User.this.cash = cash;
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
