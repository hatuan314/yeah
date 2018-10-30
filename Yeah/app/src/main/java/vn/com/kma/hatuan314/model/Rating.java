package vn.com.kma.hatuan314.model;

import java.io.Serializable;

public class Rating implements Serializable{
    private String id_user;
    private String user_name;
    private float math_point;
    private float physics_point;
    private float chemistry_point;
    private float english_point;

    public Rating(String id_user, String user_name, float math_point, float physics_point, float chemistry_point, float english_point) {
        this.id_user = id_user;
        this.user_name = user_name;
        this.math_point = math_point;
        this.physics_point = physics_point;
        this.chemistry_point = chemistry_point;
        this.english_point = english_point;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public float getMath_point() {
        return math_point;
    }

    public void setMath_point(float math_point) {
        this.math_point = math_point;
    }

    public float getPhysics_point() {
        return physics_point;
    }

    public void setPhysics_point(float physics_point) {
        this.physics_point = physics_point;
    }

    public float getChemistry_point() {
        return chemistry_point;
    }

    public void setChemistry_point(float chemistry_point) {
        this.chemistry_point = chemistry_point;
    }

    public float getEnglish_point() {
        return english_point;
    }

    public void setEnglish_point(float english_point) {
        this.english_point = english_point;
    }
}
