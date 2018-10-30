package vn.com.kma.hatuan314.model;

import java.io.Serializable;

public class Question implements Serializable {
    private int id_question;
    private int id_thematic;
    private int level;
    private String content_question;
    private String true_answer;
    private String false_answer_one;
    private String false_answer_two;
    private String false_answer_three;
    private String answer_user_choice;

    public Question(int id_question, int id_thematic, int level, String content_question, String true_answer, String false_answer_one, String false_answer_two, String false_answer_three) {
        this.id_question = id_question;
        this.id_thematic = id_thematic;
        this.level = level;
        this.content_question = content_question;
        this.true_answer = true_answer;
        this.false_answer_one = false_answer_one;
        this.false_answer_two = false_answer_two;
        this.false_answer_three = false_answer_three;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public int getId_thematic() {
        return id_thematic;
    }

    public void setId_thematic(int id_thematic) {
        this.id_thematic = id_thematic;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getContent_question() {
        return content_question;
    }

    public void setContent_question(String content_question) {
        this.content_question = content_question;
    }

    public String getTrue_answer() {
        return true_answer;
    }

    public void setTrue_answer(String true_answer) {
        this.true_answer = true_answer;
    }

    public String getFalse_answer_one() {
        return false_answer_one;
    }

    public void setFalse_answer_one(String false_answer_one) {
        this.false_answer_one = false_answer_one;
    }

    public String getFalse_answer_two() {
        return false_answer_two;
    }

    public void setFalse_answer_two(String false_answer_two) {
        this.false_answer_two = false_answer_two;
    }

    public String getFalse_answer_three() {
        return false_answer_three;
    }

    public void setFalse_answer_three(String false_answer_three) {
        this.false_answer_three = false_answer_three;
    }

    public String getAnswer_user_choice() {
        return answer_user_choice;
    }

    public void setAnswer_user_choice(String answer_user_choice) {
        this.answer_user_choice = answer_user_choice;
    }
}
