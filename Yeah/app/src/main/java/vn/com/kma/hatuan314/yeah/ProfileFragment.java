package vn.com.kma.hatuan314.yeah;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.com.kma.hatuan314.model.Rating;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    ImageView ivProfileBackground;
//    CircleImageView cvAvatar;
    TextView tvUser_name;
    TextView tvMath_point;
    TextView tvPhysics_point;
    TextView tvChemistry_point;
    TextView tvEnglish_point;

    ListView lvSubjecScorestList;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        addControls(view);
        return view;
    }

    private void addControls(View view) {
        ivProfileBackground = view.findViewById(R.id.ivProfileBackground);
//        cvAvatar = view.findViewById(R.id.cvAvatar);
        tvUser_name = view.findViewById(R.id.tvUser_name);
        tvMath_point = view.findViewById(R.id.tvMath_point);
        tvPhysics_point = view.findViewById(R.id.tvPhysics_point);
        tvChemistry_point = view.findViewById(R.id.tvChemistry_point);
        tvEnglish_point = view.findViewById(R.id.tvEnglish_point);
        Rating rating = RatingFragment.mRating;
        tvMath_point.setText(String.valueOf(rating.getMath_point()));
        tvPhysics_point.setText(String.valueOf(rating.getPhysics_point()));
        tvChemistry_point.setText(String.valueOf(rating.getChemistry_point()));
        tvEnglish_point.setText(String.valueOf(rating.getEnglish_point()));

    }

}
