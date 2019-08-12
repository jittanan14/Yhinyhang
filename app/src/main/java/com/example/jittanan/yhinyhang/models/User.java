package com.example.jittanan.yhinyhang.models;

public class User {
    private String email, password, username, gender, birthday, element, food, image_user, body, num_yhin, num_yhang;

    public User(String email, String password, String username, String gender, String birthday, String element, String food, String image_user, String body, String num_yhin, String num_yhang) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.birthday = birthday;
        this.element = element;
        this.food = food;
        this.image_user = image_user;
        this.body = body;
        this.num_yhin = num_yhin;
        this.num_yhang = num_yhang;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getElement() {
        return element;
    }

    public String getBody() {
        return body;
    }

    public String getFood() {
        return food;
    }

    public String getImage_user() {
        return image_user;
    }

    public String getNum_yhin() {
        return num_yhin;
    }

    public String getNum_yhang() {
        return num_yhang;
    }
}
