package vn.com.kma.hatuan314.model;

import java.io.Serializable;

public class Thematic implements Serializable{
    private int id_thematic;
    private int id_subject;
    private String thematic_name;

    public Thematic(int id_thematic, int id_subject, String thematic_name) {
        this.id_thematic = id_thematic;
        this.id_subject = id_subject;
        this.thematic_name = thematic_name;
    }

    public int getId_thematic() {
        return id_thematic;
    }

    public void setId_thematic(int id_thematic) {
        this.id_thematic = id_thematic;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getThematic_name() {
        return thematic_name;
    }

    public void setThematic_name(String thematic_name) {
        this.thematic_name = thematic_name;
    }
}
