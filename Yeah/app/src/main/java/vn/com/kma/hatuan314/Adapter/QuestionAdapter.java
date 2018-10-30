package vn.com.kma.hatuan314.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import katex.hourglass.in.mathlib.MathView;
import vn.com.kma.hatuan314.model.Question;
import vn.com.kma.hatuan314.other.RandomAlgorithm;
import vn.com.kma.hatuan314.yeah.ExaminationActivity;
import vn.com.kma.hatuan314.yeah.R;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    private String TAG = "MATH_LIST_ADAP";
    private int selected_card_position = -1;
    ArrayList<Question> questionList;
    ArrayList<String> answerList;
    private Context mContext;
    String mAnswer;

    public QuestionAdapter(Context context,  ArrayList<Question> questionList)
    {
        //Full Page Correction Rubric list click
        this.mContext = context;
        this.questionList = questionList;
    }



    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(this.mContext)
                .inflate(R.layout.question_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder holder, int position) {
        Question question = questionList.get(position);
        String true_answer = question.getTrue_answer();
        String false_answer_one = question.getFalse_answer_one();
        String false_answer_two = question.getFalse_answer_two();
        String false_answer_three = question.getFalse_answer_three();
        if (selected_card_position==position) {
            holder.math_view.setTextColor(ContextCompat.getColor(mContext, R.color.card_view));
            holder.math_view.setTextSize(16);
        }
        else {
            holder.math_view.setTextSize(16);
        }
        holder.math_view.setDisplayText("Câu " +(position+1)+": "+ question.getContent_question());

        answerList = new ArrayList<>();
        answerList.add(true_answer);
        answerList.add(false_answer_one);
        answerList.add(false_answer_two);
        answerList.add(false_answer_three);

        String answer;

        int i = RandomAlgorithm.rand(0,answerList.size()-1);
        answer = answerList.get(i);
        holder.rbAnswerA.setText(answer);
        answerList.remove(i);

        i = RandomAlgorithm.rand(0, answerList.size()-1);
        answer = answerList.get(i);
        holder.rbAnswerB.setText(answer);
        answerList.remove(i);

        i = RandomAlgorithm.rand(0, answerList.size()-1);
        answer = answerList.get(i);
        holder.rbAnswerC.setText(answer);
        answerList.remove(i);

        i = RandomAlgorithm.rand(0, answerList.size()-1);
        answer = answerList.get(i);
        holder.rbAnswerD.setText(answer);
        answerList.remove(i);

        addEvents(holder, question);
    }

    private void addEvents(final ViewHolder holder, final Question question) {
        holder.rgAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbAnswerA:{
                        mAnswer = holder.rbAnswerA.getText().toString();
                    } break;

                    case R.id.rbAnswerB:{
                        mAnswer = holder.rbAnswerB.getText().toString();
                    } break;

                    case R.id.rbAnswerC:{
                        mAnswer = holder.rbAnswerC.getText().toString();
                    } break;

                    case R.id.rbAnswerD:{
                        mAnswer = holder.rbAnswerD.getText().toString();
                    } break;
                }

                question.setAnswer_user_choice(mAnswer);
            }
        });

    }


    @Override
    public int getItemCount() {

        return questionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        MathView math_view;
        RadioGroup rgAnswer;
        RadioButton rbAnswerA;
        RadioButton rbAnswerB;
        RadioButton rbAnswerC;
        RadioButton rbAnswerD;


        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            math_view = (MathView) v.findViewById(R.id.math_view);
            rgAnswer = v.findViewById(R.id.rgAnswer);
            rbAnswerA = v.findViewById(R.id.rbAnswerA);
            rbAnswerA.setChecked(false);
            rbAnswerB = v.findViewById(R.id.rbAnswerB);
            rbAnswerB.setChecked(false);
            rbAnswerC = v.findViewById(R.id.rbAnswerC);
            rbAnswerC.setChecked(false);
            rbAnswerD = v.findViewById(R.id.rbAnswerD);
            rbAnswerD.setChecked(false);
        }
    }


    public void toggleMarked(int position) {
        if (selected_card_position==position)
        {
            selected_card_position = -1;
            //de selecting a previous selection;
        }
        else
        {
            selected_card_position = position;
            // mentioning a new selection
        }
        notifyItemChanged(position);
    }
}
