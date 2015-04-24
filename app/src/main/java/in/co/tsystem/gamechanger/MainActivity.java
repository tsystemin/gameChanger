package in.co.tsystem.gamechanger;

/**
 * Created by diganta.paladhi on 05/04/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;


public class MainActivity extends Activity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    /* A flag indicating that a PendingIntent is in progress and prevents
     * us from starting further intents.
     */
    private boolean mIntentInProgress;

    //Variable to keep track of button clicked
    private boolean FB_CLICKED = false;
    private boolean mSignInClicked;
    private ConnectionResult mConnectionResult;
    private View main_view;

    CallbackManager callbackManager;
    LoginButton fbLoginButton;
    SignInButton gLoginButton;
    Button gcSignInButton;
    Button gcSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize Facebook SDK
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        //Initialize Google SDK
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        setContentView(R.layout.activity_main);

        //FB Login
        fbLoginButton = (LoginButton) findViewById(R.id.fb_login_button);
        fbLoginButton.setReadPermissions("user_friends");
        fbLoginButton.setOnClickListener(this);

        //Google+ Login
        gLoginButton = (SignInButton) findViewById(R.id.g_login_button);
        setGooglePlusButtonText(gLoginButton, "Sign In with Google");
        gLoginButton.setOnClickListener(this);
        gLoginButton.setSize(SignInButton.SIZE_WIDE);

        //GameChanger Login
        gcSignInButton = (Button) findViewById(R.id.gc_sign_in_button);
        gcSignInButton.setOnClickListener(this);

        //GameChanger Register
        gcSignUpButton = (Button) findViewById(R.id.gc_sign_up_button);
        gcSignUpButton.setOnClickListener(this);

        main_view = findViewById(R.id.main_view);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fb_login_button:
                main_view.setVisibility(true ? View.GONE : View.VISIBLE);
                // Callback registration
                FB_CLICKED = true;
                fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.d("SUCCESS"," Facebook Login Successful");
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
                break;
            case R.id.g_login_button:
                main_view.setVisibility(true ? View.GONE : View.VISIBLE);
                // Google+ Client
                mSignInClicked = true;
                if (!mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                } else if (mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.disconnect();
                }
                break;
            case R.id.gc_sign_in_button:
                //myAsyncTask tsk = new myAsyncTask();
                //tsk.execute();
                main_view.setVisibility(true ? View.GONE : View.VISIBLE);
                gc_login(view);
                break;
            case R.id.gc_sign_up_button:
                main_view.setVisibility(true ? View.GONE : View.VISIBLE);
                gc_register(view);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //if(!mResolvingError) {
            //mGoogleApiClient.connect();
        //}
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }

        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("SUCCESS"," Google Login Successful");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if(connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, ConnectionResult.RESOLUTION_REQUIRED);
            } catch (IntentSender.SendIntentException e) {
                mGoogleApiClient.connect();
            }
        }
    }

    protected void setGooglePlusButtonText(SignInButton signInButton, String buttonText) {
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);
            if (v instanceof TextView) {
                TextView mTextView = (TextView) v;
                mTextView.setText(buttonText);
                return;
            }
        }
    }

    public void gc_login(View view) {
        Intent intent = new Intent(this, GcLoginActivity.class);
        startActivity(intent);
    }

    public void gc_register(View view) {
        Intent intent = new Intent(this, GcRegisterActivity.class);
        startActivity(intent);
    }

}
