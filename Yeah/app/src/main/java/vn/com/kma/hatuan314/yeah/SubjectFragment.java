package vn.com.kma.hatuan314.yeah;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends Fragment implements View.OnClickListener{
    LinearLayout math_layout;
    LinearLayout physics_layout;
    LinearLayout chemistry_layout;
    LinearLayout english_layout;
    public Fragment fragment;
    String subject_id;

    public SubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        addControls(view);
        addEvents();
        return view;
    }

    private void addEvents() {
        math_layout.setOnClickListener(this);
        physics_layout.setOnClickListener(this);
        chemistry_layout.setOnClickListener(this);
        english_layout.setOnClickListener(this);
    }

    private void addControls(View view) {
        math_layout = view.findViewById(R.id.math_layout);
        physics_layout = view.findViewById(R.id.physics_layout);
        chemistry_layout = view.findViewById(R.id.chemistry_layout);
        english_layout = view.findViewById(R.id.english_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.math_layout:
            {
                subject_id="1";
                if (CenterFragment.check_fragment_status==0){
                    Intent intent = new Intent(getContext(), ExaminationActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("SUBJECT_ID", subject_id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), ThematicActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("SUBJECT_ID", subject_id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            } break;
            case R.id.physics_layout:
            {
                subject_id="2";
                Toasty.warning(getContext(), "Dữ liệu đang được cập nhật...").show();
            } break;
            case R.id.chemistry_layout:
            {
                subject_id="3";
                Toasty.warning(getContext(), "Dữ liệu đang được cập nhật...").show();
            } break;
            case R.id.english_layout:
            {
                subject_id="4";
                Toasty.warning(getContext(), "Dữ liệu đang được cập nhật...").show();
            } break;

        }
    }
}
