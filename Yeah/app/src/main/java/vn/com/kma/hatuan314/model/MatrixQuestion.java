package vn.com.kma.hatuan314.model;

public class MatrixQuestion {
    private int id;
    private int id_thematic;
    private int level_1;
    private int level_2;
    private int level_3;

    public MatrixQuestion(int id, int id_thematic, int level_1, int level_2, int level_3) {
        this.id = id;
        this.id_thematic = id_thematic;
        this.level_1 = level_1;
        this.level_2 = level_2;
        this.level_3 = level_3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_thematic() {
        return id_thematic;
    }

    public void setId_thematic(int id_thematic) {
        this.id_thematic = id_thematic;
    }

    public int getLevel_1() {
        return level_1;
    }

    public void setLevel_1(int level_1) {
        this.level_1 = level_1;
    }

    public int getLevel_2() {
        return level_2;
    }

    public void setLevel_2(int level_2) {
        this.level_2 = level_2;
    }

    public int getLevel_3() {
        return level_3;
    }

    public void setLevel_3(int level_3) {
        this.level_3 = level_3;
    }

}
