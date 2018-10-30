package vn.com.kma.hatuan314.yeah;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import es.dmoral.toasty.Toasty;
import vn.com.kma.hatuan314.model.User;
import vn.com.kma.hatuan314.other.CheckConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth auth;
    FirebaseUser mFirebaseUser;

    /* Google */
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 101;
    SignInButton google_sign_in_button;

    /* Facebook */
    LoginButton facebook_sign_in_button;
    CallbackManager callbackManager;

    CardView card_view_google;
    CardView card_view_facebook;
    public static User user;
    public static String saveLogIn = "LogIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        boolean flag = checkLogIn();
        if (flag) {
            addControlsWithRestart();
            addEventsWithRestart();
            finish();
        }else {
            if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                addControlsWithStart();
                addEventsWithStart();
            } else {
                CheckConnection.ShowToast_Short(getApplicationContext(), "Không có kết nối Internet");
            }
        }
    }

    private void addEventsWithStart() {
        google_sign_in_button.setOnClickListener(this);
        facebook_sign_in_button.setOnClickListener(this);
        card_view_google.setOnClickListener(this);
        card_view_facebook.setOnClickListener(this);
    }

    private void addControlsWithStart() {
        Google_SignIn();
        Facebook_SignIn();
    }

    private void addEventsWithRestart() {
        Intent intent = new Intent(getApplicationContext(), CenterActivity.class);
        startActivity(intent);
    }

    private void addControlsWithRestart() {
    }

    private boolean checkLogIn() {
        boolean flag = false;
        //Load SharePreferences
        SharedPreferences sharedPreferences = getSharedPreferences(saveLogIn, MODE_PRIVATE);
        String mId = sharedPreferences.getString("ID","");
        String mUserName = sharedPreferences.getString("NAME","");
        String mEmail = sharedPreferences.getString("EMAIL","");
        String mPhone= sharedPreferences.getString("PHONE","");
        String mAvatarGoogleLink = sharedPreferences.getString("AVATAR_GOOGLE_LINK","");
        String mIdFacebook = sharedPreferences.getString("IDFACEBOOK","");
        String mIdGoogle = sharedPreferences.getString("IDGOOGLE","");
        String mUser_Scores = sharedPreferences.getString("USER_SCORES","45");

        if (mUserName.length()>0){
            user = new User(mId, mUserName, mAvatarGoogleLink,  mEmail, mPhone, mIdFacebook, mIdGoogle, Integer.parseInt(mUser_Scores));
            flag = true;
        }
        return flag;
    }


    private void Facebook_SignIn() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        card_view_facebook = findViewById(R.id.card_view_facebook);
        facebook_sign_in_button = findViewById(R.id.facebook_sign_in_button);
        callbackManager = CallbackManager.Factory.create();
        facebook_sign_in_button.setReadPermissions(Arrays.asList("email","public_profile"));
    }

    private void Google_SignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        card_view_google = findViewById(R.id.card_view_google);
        google_sign_in_button = findViewById(R.id.google_sign_in_button);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /* Google */
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

                // ...
            }
        }

        /* Facebook */
        callbackManager.onActivityResult(requestCode, resultCode,data);

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            mFirebaseUser = auth.getCurrentUser();
                            UpdateUI(mFirebaseUser);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toasty.error(getApplicationContext(), "Đăng nhặp thất bại").show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_view_google:{
                google_sign_in_button.performClick();
                Intent signIntent = mGoogleSignInClient.getSignInIntent();
                mFirebaseUser = auth.getCurrentUser();
                startActivityForResult(signIntent, RC_SIGN_IN);
            } break;

            case R.id.card_view_facebook:{
                facebook_sign_in_button.performClick();
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Toasty.warning(getApplicationContext(), "Cancel").show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toasty.error(getApplicationContext(), "Error").show();
                    }
                });
            }
        }
    }

    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(credential).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            mFirebaseUser = auth.getCurrentUser();
                            UpdateUI(mFirebaseUser);
                        } else {
                            Toasty.error(getApplicationContext(), "Couldn't register to firebase").show();
                        }
                    }
                });
    }

    private void UpdateUI(FirebaseUser mFirebaseUser) {
        String id = mFirebaseUser.getProviderId();

        String user_name = mFirebaseUser.getDisplayName();
        String avatar = mFirebaseUser.getPhotoUrl().toString();
        String email = mFirebaseUser.getEmail();
        String phone = mFirebaseUser.getPhoneNumber();
        String id_facebook = "";
        String id_google = "";

        Toasty.success(getApplicationContext(), "Xin chào "+ user_name);

        user = new User(id, user_name,avatar,email,phone,id_facebook,id_google, 45);

        /* Save user data to divice memory */
        SharedPreferences sharedPreferences = getSharedPreferences(saveLogIn,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ID", user.getId());
        editor.putString("NAME", user.getUser_name());
        editor.putString("EMAIL", user.getEmail());
        editor.putString("PHONE", user.getPhone());
        editor.putString("AVATAR_GOOGLE_LINK", user.getAvatar());
        editor.putString("ID_FACEBOOK", user.getFacebook_id());
        editor.putString("ID_GOOGLE", user.getGoogle_id());
        editor.putString("USER_SCORE", "45");
        editor.commit();

        Intent intent = new Intent(MainActivity.this, CenterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER", user);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
