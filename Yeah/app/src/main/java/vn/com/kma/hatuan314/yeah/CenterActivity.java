package vn.com.kma.hatuan314.yeah;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;

public class CenterActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private CenterFragment centerFragment;
    private ViewAnimator viewAnimator;
    private int res = R.drawable.ic_test50;
    private LinearLayout linearLayout;

    public static TextView tvFragmentName;
    public static TextView tvCountdown;

    public static String fragment_name = "";

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_center);
        auth = FirebaseAuth.getInstance();
        addControls();
    }

    private void addControls() {
        tvFragmentName = findViewById(R.id.tvFragmentName);
        tvCountdown = findViewById(R.id.tvCountdown);
        centerFragment = CenterFragment.newInstance(R.drawable.ic_test50);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, centerFragment)
                .commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator(this, list, centerFragment, drawerLayout, this);
    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(centerFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);
        SlideMenuItem menuItem1 = new SlideMenuItem(centerFragment.PROFILE, R.drawable.ic_user50);
        list.add(menuItem1);
        SlideMenuItem menuItem2 = new SlideMenuItem(centerFragment.TEST, R.drawable.ic_test50);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(centerFragment.THEMATIC, R.drawable.ic_piece50);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(centerFragment.RATING, R.drawable.ic_star50);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(centerFragment.INFO, R.drawable.ic_info50);
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem(centerFragment.FEEDBACK, R.drawable.ic_feedback50);
        list.add(menuItem6);
        SlideMenuItem menuItem7 = new SlideMenuItem(centerFragment.SIGNOUT, R.drawable.ic_sign_out50);
        list.add(menuItem7);

    }

    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition) {
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        animator.start();
        CenterFragment centerFragment = CenterFragment.newInstance(this.res);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, centerFragment).commit();
        return centerFragment;
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case CenterFragment.CLOSE:
                return screenShotable;
            case CenterFragment.PROFILE:
            {
                fragment_name=CenterFragment.PROFILE;
                return replaceFragment(screenShotable, position);
            }
            case CenterFragment.TEST:
            {
                fragment_name=CenterFragment.TEST;
                return replaceFragment(screenShotable, position);
            }
            case CenterFragment.DOCUMENT:
            {
                fragment_name = CenterFragment.DOCUMENT;
                return replaceFragment(screenShotable, position);
            }
            case CenterFragment.RATING:
            {
                fragment_name = CenterFragment.RATING;
                return replaceFragment(screenShotable, position);
            }
            case CenterFragment.THEMATIC:
            {
                fragment_name = CenterFragment.THEMATIC;
                return replaceFragment(screenShotable, position);
            }
            case CenterFragment.FEEDBACK:
            {
                fragment_name = CenterFragment.FEEDBACK;
                return replaceFragment(screenShotable, position);
            }

            case CenterFragment.INFO:
            {
                fragment_name = CenterFragment.INFO;
                return replaceFragment(screenShotable, position);
            }
            case CenterFragment.SIGNOUT:
            {
                fragment_name = CenterFragment.SIGNOUT;
                return replaceFragment(screenShotable, position);
            }
            default:
                return replaceFragment(screenShotable, position);
        }
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
