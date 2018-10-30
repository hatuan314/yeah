package vn.com.kma.hatuan314.model;

import android.net.Uri;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String user_name;
    private String avatar;
    private String email;
    private String phone;
    private String facebook_id;
    private String google_id;
    private int user_scores;

    public User(String id, String user_name, String avatar, String email, String phone, String facebook_id, String google_id, int user_scores) {
        this.id = id;
        this.user_name = user_name;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.facebook_id = facebook_id;
        this.google_id = google_id;
        this.user_scores = user_scores;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
    }

    public int getUser_scores() {
        return user_scores;
    }

    public void setUser_scores(int user_scores) {
        this.user_scores = user_scores;
    }
}
