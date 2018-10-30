package vn.com.kma.hatuan314.yeah;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.kma.hatuan314.model.Question;
import vn.com.kma.hatuan314.model.Rating;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {
    TextView tvSentence;
    TextView tvScores;


    float point=0;

    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        tvScores = view.findViewById(R.id.tvScores);

        ArrayList<Question> questions = ExaminationFragment.question_list_after_rand;
        for (int i = 0; i<questions.size(); i++){
            Question question = questions.get(i);
            if (question==null){
                continue;
            }
            if (question.getAnswer_user_choice().equals(question.getTrue_answer())){
                point = (float) (point + 0.83);
            }
        }
        ArrayList<Rating> ratings = RatingFragment.ratings;
        Rating rating = ratings.get(1);
        float mPoint = rating.getMath_point()+point;
        RatingFragment.mRating.setMath_point(mPoint);
        rating.setMath_point(mPoint);
        tvScores.setText(String.valueOf(point));
        return view;
    }


//    @Override
//    public void onDestroy() {
//        getActivity().finish();
//        super.onDestroy();
//    }
}
