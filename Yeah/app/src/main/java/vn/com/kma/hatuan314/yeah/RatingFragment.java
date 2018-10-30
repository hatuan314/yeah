package vn.com.kma.hatuan314.yeah;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import vn.com.kma.hatuan314.Adapter.RatingAdapter;
import vn.com.kma.hatuan314.model.Rating;
import vn.com.kma.hatuan314.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class RatingFragment extends Fragment implements View.OnClickListener{
    TextView tvMath;
    TextView tvPhysics;
    TextView tvChemistry;
    TextView tvEnglish;
    TextView tvUpdate;

    ListView rcListRating;
    public static Rating mRating;

    public static ArrayList<Rating> ratings;

    public RatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        addControls(view);
        addEvents();
        return view;
    }

    private void addEvents() {
        tvMath.setOnClickListener(this);
        tvPhysics.setOnClickListener(this);
        tvChemistry.setOnClickListener(this);
        tvEnglish.setOnClickListener(this);
    }

    private void addControls(View view) {
        tvMath = view.findViewById(R.id.tvMath);
        tvPhysics = view.findViewById(R.id.tvPhysics);
        tvChemistry = view.findViewById(R.id.tvChemistry);
        tvEnglish = view.findViewById(R.id.tvEnglish);
        tvUpdate = view.findViewById(R.id.tvUpdate);

        rcListRating = view.findViewById(R.id.rcListRating);

        ratings = new ArrayList<>();
        mRating = new Rating("1","Hoàng Tuấn",45,0,0,0);
        ratings.add(mRating);
        ratings.add(new Rating("2","Ha ha",34,0,0,0));
        ratings.add(new Rating("3","Tuan Hoang",56,0,0,0));
        ratings.add(new Rating("4","Hong Hung",65,0,0,0));
        ratings.add(new Rating("5","Đinh Hải",21,0,0,0));
        ratings.add(new Rating("6","Ngọc Hà",15,0,0,0));
        ratings.add(new Rating("7","Xu si",31,0,0,0));
        ratings.add(new Rating("8","Nguyễn Long",27,0,0,0));
        ratings.add(new Rating("9","Tai Tran",57,0,0,0));
        ratings.add(new Rating("10","Slow down",43,0,0,0));
        ratings.add(new Rating("11","Hằng Hải",66,0,0,0));
        ratings.add(new Rating("12","Ngọc Bá",86,0,0,0));
        ratings = sort_rating(ratings);

        RatingAdapter ratingAdapter = new RatingAdapter(getContext(), ratings);
        rcListRating.setAdapter(ratingAdapter);

    }

    public ArrayList<Rating> sort_rating(ArrayList<Rating> ratings){
        Collections.sort(ratings, new Comparator<Rating>() {
            @Override
            public int compare(Rating ratings_one, Rating ratings_two) {
                if (ratings_one.getMath_point()<ratings_two.getMath_point()){
                    return 1;
                } else if (ratings_one.getMath_point()==ratings_one.getMath_point()){
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return ratings;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvMath:{
                tvMath.setTextColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
                tvPhysics.setTextColor(ContextCompat.getColor(getContext(),R.color.colorText));
                tvChemistry.setTextColor(ContextCompat.getColor(getContext(),R.color.colorText));
                tvEnglish.setTextColor(ContextCompat.getColor(getContext(),R.color.colorText));
                tvMath.setTextSize(18);
                tvPhysics.setTextSize(16);
                tvChemistry.setTextSize(16);
                tvEnglish.setTextSize(16);
                rcListRating.setVisibility(View.VISIBLE);
                tvUpdate.setVisibility(View.INVISIBLE);
            } break;
            case R.id.tvPhysics:{
                tvMath.setTextColor(ContextCompat.getColor(getContext(),R.color.colorText));
                tvPhysics.setTextColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
                tvChemistry.setTextColor(ContextCompat.getColor(getContext(),R.color.colorText));
                tvEnglish.setTextColor(ContextCompat.getColor(getContext(),R.color.colorText));
                tvMath.setTextSize(16);
                tvPhysics.setTextSize(18);
                tvChemistry.setTextSize(16);
                tvEnglish.setTextSize(16);
                rcListRating.setVisibility(View.GONE);
                tvUpdate.setVisibility(View.VISIBLE);
            } break;
            case R.id.tvChemistry:{
                tvMath.setTextColor(ContextCompat.getColor(getContext(),R.color.colorText));
                tvPhysics.setTextColor(ContextCompat.getColor(getContext(),R.color.colorText));
                tvChemistry.setTextColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
                tvEnglish.setTextColor(ContextCompat.getColor(getContext(),R.color.colorText));
                tvMath.setTextSize(16);
                tvPhysics.setTextSize(16);
                tvChemistry.setTextSize(18);
                tvEnglish.setTextSize(16);
                rcListRating.setVisibility(View.GONE);
                tvUpdate.setVisibility(View.VISIBLE);
            } break;
            case R.id.tvEnglish:{
                tvMath.setTextColor(ContextCompat.getColor(getContext(),R.color.colorText));
                tvPhysics.setTextColor(ContextCompat.getColor(getContext(),R.color.colorText));
                tvChemistry.setTextColor(ContextCompat.getColor(getContext(),R.color.colorText));
                tvEnglish.setTextColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
                tvMath.setTextSize(16);
                tvPhysics.setTextSize(16);
                tvChemistry.setTextSize(16);
                tvEnglish.setTextSize(18);
                rcListRating.setVisibility(View.GONE);
                tvUpdate.setVisibility(View.VISIBLE);
            } break;
        }
    }
}
