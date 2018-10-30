package vn.com.kma.hatuan314.yeah;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import vn.com.kma.hatuan314.Adapter.QuestionAdapter;
import vn.com.kma.hatuan314.model.Question;

public class ExaminationActivity extends AppCompatActivity {
    private TextView tvCountdown;
    FrameLayout container;

    public long START_TIME_IN_MILLIS = 600000;
    CountDownTimer mCountDownTimer;
    boolean mTimeRunning;
    long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    TextView tvConfirm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);
        addControls();
        addEvents();
    }

    private void addEvents() {
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ExaminationActivity.this);
                builder.setTitle("Yeah");
                builder.setMessage("Bạn có muốn kiểm tra lại bài làm không ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ResultFragment fragment = new ResultFragment();
                        loadFragment(fragment);
                    }
                });
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void addControls() {
        tvCountdown =  findViewById(R.id.tvCountdown);
        tvConfirm =  findViewById(R.id.tvConfirm);
        container = findViewById(R.id.container);

        ExaminationFragment fragment = new ExaminationFragment();
        loadFragment(fragment);
        startTime();
    }

    private void startTime() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                Toasty.error(ExaminationActivity.this, "Hết giờ").show();
                ResultFragment fragment = new ResultFragment();
                loadFragment(fragment);
            }
        }.start();

        mTimeRunning = true;
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis/1000)/60;
        int second = (int) (mTimeLeftInMillis/1000)%60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, second);
        tvCountdown.setText(timeLeftFormatted);
    }

    public  void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }


}
