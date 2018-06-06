package com.example.ruijs.aeiscte;

public class User {
    private String user_name;
    private String email;
    private String curso;
    private String birth;
    private int phone;

    public User(){

    }

    public User(String user_name, String email, String curso, String birth, int phone) {
        this.user_name = user_name;
        this.email = email;
        this.curso = curso;
        this.birth = birth;
        this.phone = phone;
    }

    public User(String user_name, String email){
        this.email=email;
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
