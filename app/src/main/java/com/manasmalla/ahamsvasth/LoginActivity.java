package com.manasmalla.ahamsvasth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements View.OnKeyListener {

    private static final int RC_SIGN_IN = 001;
    private static final String TAG = LoginActivity.class.getSimpleName();
    TextInputLayout nameTextInputLayout;
    TextInputEditText nameEditText;
    String name;
    CallbackManager mCallbackManager;
    Intent socialIntent;
    OutputStream outputStream;
    SharedPreferences.Editor editor;

    //Social Login
    private FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    TwitterLoginButton twitterLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        //Twitter Login
        TwitterConfig config = new TwitterConfig.Builder(this)
                .twitterAuthConfig(
                        new TwitterAuthConfig(
                                getString(R.string.consumerkey), getString(R.string.consumerSecretKey)
                        )
                )
                .debug(BuildConfig.DEBUG)
                .build();
        Twitter.initialize(config);
        setContentView(R.layout.activity_login);

        nameTextInputLayout = findViewById(R.id.nameTextInputLayout);
        nameEditText = findViewById(R.id.nameEditText);
        nameEditText.setOnKeyListener(this);

        //Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        editor = getSharedPreferences("com.manasmalla.ahamsvasth", MODE_PRIVATE).edit();

        //Google Login
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Facebook Login
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.loginButton_facebook);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });

        twitterLoginButton = findViewById(R.id.btnTwitterLogin);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                handleTwitterAccessToken(result.data);

            }

            @Override
            public void failure(TwitterException exception) {
                Log.d(TAG, "Twitter Error: " + exception.toString());
            }
        });
    }

    public void continueLoginOnClick(View view) {
        //Normal Login
        name = nameEditText.getText().toString();
        Log.i("Button Pressed", name);
        Intent intent = new Intent(getApplicationContext(), UserDataQuizActivity.class);
        intent.putExtra("Name", name);
        intent.putExtra("Email", "Please enter an email by editing!");
        editor.putString("username", name).apply();
        editor.putString("current_username", name).apply();
        startActivity(intent);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //OnKeyEvent
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            name = nameEditText.getText().toString();
            Log.i("Button Pressed", name);
            Intent intent = new Intent(getApplicationContext(), UserDataQuizActivity.class);
            intent.putExtra("Name", name);
            intent.putExtra("Email", "Please enter an email by editing!");
            editor.putString("username", name).apply();
            editor.putString("current_username", name).apply();
            startActivity(intent);
        }
        return false;
    }

    public void signInWithGoogle(View view) {
        TextView googleText = findViewById(R.id.googleLoginButtonText);
        if (googleText.getText().toString().matches("Sign Out")) {
            mAuth.getInstance().signOut();
            googleText.setText("Sign In With Google");
        } else {

            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        } else if (requestCode == TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE) {
            twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        } else {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user, "Google");
                        } else {
                            // If sign in fails, display a message to the user.
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                FirebaseAuthUserCollisionException exception =
                                        (FirebaseAuthUserCollisionException) task.getException();
                                if (exception.getErrorCode().matches("ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL")) {
                                    mGoogleSignInClient.signOut();
                                    Toast.makeText(LoginActivity.this, "Error logging you in, You already have logged in with some other provider! Please use that instead!", Toast.LENGTH_LONG).show();
                                }
                            }
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null, "Google");
                        }

                        // ...
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user, "Facebook");
                        } else {
                            // If sign in fails, display a message to the user.
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                FirebaseAuthUserCollisionException exception =
                                        (FirebaseAuthUserCollisionException) task.getException();
                                if (exception.getErrorCode().matches("ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL")) {
                                    LoginManager.getInstance().logOut();
                                    Toast.makeText(LoginActivity.this, "Error logging you in, You already have logged in with some other provider! Please use that instead!", Toast.LENGTH_LONG).show();
                                }
                            }
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null, "Facebook");
                        }

                        // ...
                    }
                });
    }


    private void handleTwitterAccessToken(TwitterSession session) {
        AuthCredential credential = TwitterAuthProvider.getCredential(session.getAuthToken().token, session.getAuthToken().secret);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user, "Twitter");
                } else {
                    // If sign in fails, display a message to the user.
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        FirebaseAuthUserCollisionException exception =
                                (FirebaseAuthUserCollisionException) task.getException();
                        if (exception.getErrorCode().matches("ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL")) {
                            TwitterCore.getInstance().getSessionManager().clearActiveSession();
                            Toast.makeText(LoginActivity.this, "Error logging you in, You already have logged in with some other provider! Please use that instead!", Toast.LENGTH_LONG).show();
                        }
                    }
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null, "Twitter");
                }

                // ...
            }
        });
    }

    private void updateUI(FirebaseUser user, String provider) {
        //
        if (user != null) {
            if (provider.matches("Google")) {
                TextView googleText = findViewById(R.id.googleLoginButtonText);
                googleText.setText("Sign Out");
            }

            socialIntent = new Intent(getApplicationContext(), UserDataQuizActivity.class);

            String userPhotoURL = String.valueOf(user.getPhotoUrl());
            if (provider.matches("Facebook")) {
                userPhotoURL = userPhotoURL + "?height=512";
            } else if (provider.matches("Twitter")) {
                userPhotoURL = userPhotoURL.replace("_normal", "");
            }
            userPhotoURL = userPhotoURL.replace("s96-c", "s512-c");
            Log.i("profileURL", userPhotoURL);
            socialIntent.putExtra("Name", user.getDisplayName());
            socialIntent.putExtra("Email", user.getEmail());
            editor.putString("username", user.getDisplayName()).apply();
            editor.putString("email", user.getEmail()).apply();
            editor.putString("current_username", user.getDisplayName()).apply();
            Bitmap profileBitmap = downloadImage(userPhotoURL);
            File externalStorage = LoginActivity.this.getExternalFilesDir(null);
            File filePath = new File(externalStorage.getAbsolutePath() + "profile");
            if (filePath.exists()) {
                Log.i("FilePath", "Exists");
            } else {
                if (filePath.mkdir()) {
                    Log.i("Created Folder at ", filePath.toString());
                } else {
                    Toast.makeText(this, "Couldn't save profile pic! ", Toast.LENGTH_SHORT).show();
                }
            }
            File imageSlide = new File(filePath, "profile_image.png");

            try {
                outputStream = new FileOutputStream(imageSlide);
                profileBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            startActivity(socialIntent);
        }

    }

    //Download profile pic
    public static Bitmap downloadImage(String profileURL) {

        // https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage = null;

        try {
            myImage = task.execute(profileURL).get();

        } catch (Exception e) {

            e.printStackTrace();

        }
        return myImage;
    }

    public static class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}