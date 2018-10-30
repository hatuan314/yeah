package vn.com.kma.hatuan314.yeah;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CenterFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String PROFILE = "Profile";
    public static final String TEST = "Test";
    public static final String THEMATIC = "Thematic";
    public static final String DOCUMENT = "Document";
    public static final String RATING = "Rating";
    public static final String INFO = "Info";
    public static final String FEEDBACK = "Feedback";
    public static final String SIGNOUT = "Sign Out";
    public static int check_fragment_status = 0;

    FirebaseAuth auth;

    private View containerView;
    protected int res;
    private Bitmap bitmap;

    public static CenterFragment newInstance(int resId){
        CenterFragment centerFragment = new CenterFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        centerFragment.setArguments(bundle);
        return centerFragment;
    }

    public CenterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_center, container, false);
        auth = FirebaseAuth.getInstance();
        String fragment_name = CenterActivity.fragment_name;
        CenterActivity.tvFragmentName.setText("Thi thử");
        Fragment fragment = new SubjectFragment();
        loadFragment(fragment);
        CatchEventChoiceFunction(fragment_name, rootView);
        return rootView;
    }

    /* Hàm bắt sự kiện chọn chức năng */
    private void CatchEventChoiceFunction(String fragment_name, View view) {
        switch ((fragment_name))
        {
            case "Profile":
            {
                check_fragment_status=6;
                CenterActivity.tvFragmentName.setText("Trang cá nhân");
                Fragment profile_fragment = new ProfileFragment();
                loadFragment(profile_fragment);
            } break;
            case "Test":
            {
                check_fragment_status=0;
                CenterActivity.tvFragmentName.setText("Thi thử");
                Fragment test_fragment = new SubjectFragment();
                loadFragment(test_fragment);
            } break;
            case "Thematic":
            {
                check_fragment_status=1;
                CenterActivity.tvFragmentName.setText("Chủ đề");
                Fragment fragment = new SubjectFragment();
                loadFragment(fragment);
            } break;
            case "Rating":
            {
                check_fragment_status=3;
                CenterActivity.tvFragmentName.setText("Xếp hạng");
                Fragment fragment = new RatingFragment();
                loadFragment(fragment);
            } break;
            case "Info":
            {
                check_fragment_status = 4;
                CenterActivity.tvFragmentName.setText("Thông tin sản phẩm");
                Fragment fragment = new InfoFragment();
                loadFragment(fragment);
            } break;
            case "Feedback":
            {
                check_fragment_status=5;
                CenterActivity.tvFragmentName.setText("Phản hồi");
                Fragment fragment = new FeedbackFragment();
                loadFragment(fragment);
            } break;

            case "Sign Out":{
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Yeah");
                builder.setMessage("Bạn muốn đăng xuất ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        auth.signOut();
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MainActivity.saveLogIn,MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        getActivity().finish();
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            } break;
        }
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                CenterFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * loading fragment into FrameLayout
     *
     * @param childFragment
     */
    public void loadFragment(Fragment childFragment) {
        // load fragment
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container, childFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
