package it.unibo.tw.model;

import java.util.Date;

public class Student {
    
    private int code;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Student(){}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCode() {
        return code;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }   
}
