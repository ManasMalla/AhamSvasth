package com.manasmalla.ahamsvasth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.twitter.sdk.android.core.models.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDataQuizActivity extends AppCompatActivity {

    TextView nameTextView, emailTextView;
    public static final int storageRquestCode = 001;
    OutputStream outputStream;
    MaterialCardView materialCardView;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == storageRquestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, 2);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_user_data_quiz);
        nameTextView = findViewById(R.id.userNameTextView);
        nameTextView.setText(getIntent().getStringExtra("Name"));
        emailTextView = findViewById(R.id.emailTextView);
        if (getIntent().getStringExtra("Email") != null) {
            emailTextView.setText(getIntent().getStringExtra("Email"));
        }
        materialCardView = findViewById(R.id.appInfoQuizActivity);
        File externalStorage = UserDataQuizActivity.this.getExternalFilesDir(null);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
            File storage = Environment.getExternalStorageDirectory();
            externalStorage = new File(storage.getAbsolutePath() +
                    "/Android/data/" + UserDataQuizActivity.this.getPackageName() + "/files");
        }
        File filePath = new File(externalStorage.getAbsolutePath() + "profile");
        File imageSlide = new File(filePath, "profile_image.png");
        if (imageSlide.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageSlide.getAbsolutePath());
            CircleImageView circleImageView = findViewById(R.id.circleImageView);
            circleImageView.setImageBitmap(bitmap);
        }
    }

    public void letsStartOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), UserDataQuestionsActivity.class);
        startActivity(intent);
    }

    public void changeProfilePic(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UserDataQuizActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, storageRquestCode);
            } else {
                Log.i("ButtonPressed", "Button");
                Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, 2);
            }
        } else {
            Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent2, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();

            try {

                Bitmap profileBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                profileBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                File externalStorage = UserDataQuizActivity.this.getExternalFilesDir(null);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
                    File storage = Environment.getExternalStorageDirectory();
                    externalStorage = new File(storage.getAbsolutePath() +
                            "/Android/data/" + UserDataQuizActivity.this.getPackageName() + "/files");
                }
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
                CircleImageView circleImageView = findViewById(R.id.circleImageView);
                circleImageView.setImageBitmap(profileBitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUser(View view) {
        nameTextView.setVisibility(View.INVISIBLE);
        emailTextView.setVisibility(View.INVISIBLE);
        findViewById(R.id.editDetailsImageView).setVisibility(View.GONE);
        findViewById(R.id.updatedImageView).setVisibility(View.VISIBLE);
        TextInputLayout nameEditEditText, emailEditEditText;
        nameEditEditText = findViewById(R.id.nameEdit_EditLayout);
        emailEditEditText = findViewById(R.id.emailEditEditLayout);
        nameEditEditText.setVisibility(View.VISIBLE);
        emailEditEditText.setVisibility(View.VISIBLE);
    }

    public void updatedUser(View view) {
        nameTextView.setVisibility(View.VISIBLE);
        emailTextView.setVisibility(View.VISIBLE);
        findViewById(R.id.editDetailsImageView).setVisibility(View.VISIBLE);
        findViewById(R.id.updatedImageView).setVisibility(View.GONE);
        TextInputLayout nameEditEditText, emailEditEditText;
        nameEditEditText = findViewById(R.id.nameEdit_EditLayout);
        emailEditEditText = findViewById(R.id.emailEditEditLayout);
        TextInputEditText namedEditText, emailedEditText;
        namedEditText = findViewById(R.id.namedEditText);
        emailedEditText = findViewById(R.id.emailedEditText);
        SharedPreferences.Editor editor = getSharedPreferences("com.manasmalla.ahamsvasth", MODE_PRIVATE).edit();
        editor.putString("username", namedEditText.getText().toString()).apply();
        if (emailedEditText.getText().toString() != null && !(emailedEditText.getText().toString().matches(""))) {
            editor.putString("email", emailedEditText.getText().toString()).apply();
        }
        editor.putString("current_username", namedEditText.getText().toString()).apply();
        nameTextView.setText(namedEditText.getText().toString());
        if (emailedEditText.getText().toString() != null && !(emailedEditText.getText().toString().matches(""))) {
           emailTextView.setText(emailedEditText.getText().toString());
        }
        nameEditEditText.setVisibility(View.GONE);
        emailEditEditText.setVisibility(View.GONE);
    }

    public void showQuestionInfo(View view) {
        materialCardView.setVisibility(View.VISIBLE);
    }

    public void closeQuestionInfo(View view) {
        materialCardView.setVisibility(View.GONE);
    }
}