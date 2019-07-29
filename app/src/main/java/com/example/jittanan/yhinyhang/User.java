package com.example.jittanan.yhinyhang;

public class User {
    private  String email, password, username,
            gender, birthday, element,body,food,image_user;

    public User(String email, String firstname, String lastname, String gender, String birthday, String telephone, String image_user) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.birthday = birthday;
        this.element = element;
        this.body = body;
        this.food = food;
        this.image_user = image_user;
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

    public String getBody() { return  body; }

    public String getFood() { return food;}

    public String getImage_user() {
        return image_user;
    }
}
