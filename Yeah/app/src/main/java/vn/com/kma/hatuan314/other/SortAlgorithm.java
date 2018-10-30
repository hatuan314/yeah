package vn.com.kma.hatuan314.other;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import vn.com.kma.hatuan314.model.Question;

public class SortAlgorithm {

    public SortAlgorithm() {
    }

    public ArrayList<Question> SortThematic(ArrayList<Question> questions){
        Collections.sort(questions, new Comparator<Question>() {
            @Override
            public int compare(Question question_one, Question question_two) {
                if (question_one.getId_thematic()>question_two.getId_thematic()){
                    return 1;
                } else if (question_one.getId_thematic()==question_two.getId_thematic()){
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return questions;
    }

    public ArrayList<Question> SortLevel(ArrayList<Question> questions){
        Collections.sort(questions, new Comparator<Question>() {
            @Override
            public int compare(Question question_one, Question question_two) {
                if (question_one.getLevel()>question_two.getLevel()){
                    return 1;
                } else if (question_one.getLevel()==question_two.getLevel()){
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return questions;
    }
}
