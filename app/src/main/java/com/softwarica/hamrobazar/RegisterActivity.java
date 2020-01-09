package com.softwarica.hamrobazar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softwarica.hamrobazar.API.UsersAPI;
import com.softwarica.hamrobazar.model.User;
import com.softwarica.hamrobazar.serverresponse.ImageResponse;
import com.softwarica.hamrobazar.serverresponse.RegisterResponse;
import com.softwarica.hamrobazar.strictmode.StrictModeClass;
import com.softwarica.hamrobazar.url.URL;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private CircleImageView imgProfile;
    private EditText  etEmail, etFullName, etPassword, etCPassword, etPhone, etMphone, etAddress1, etAddress2, etAddress3;
    private Button btnRegister;
    String imagePath;
    private String imageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

            imgProfile = findViewById(R.id.imgProfile);
            etEmail = findViewById(R.id.etEmail);
            etFullName = findViewById(R.id.etFullName);
            etPassword = findViewById(R.id.etPassword);
            etCPassword = findViewById(R.id.etCPassword);
            etPhone = findViewById(R.id.etPhone);
            etMphone = findViewById(R.id.etMphone);
            etAddress1 = findViewById(R.id.etAddress1);
            etAddress2 = findViewById(R.id.etAddress2);
            etAddress3 = findViewById(R.id.etAddress3);
            btnRegister = findViewById(R.id.btnRegister);

            imgProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BrowseImage();
                }
            });
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etPassword.getText().toString().equals(etCPassword.getText().toString())) {
                        if(validate()) {
                            saveImageOnly();
                            register();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                        etPassword.requestFocus();
                        return;
                    }

                }
            });
        }

        private boolean validate() {
            boolean status=true;
            if (etEmail.getText().toString().length() < 6) {
//            etEmail.setError("Minimum 6 character");
                status=false;
            }
            return status;
        }

        private void BrowseImage() {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 0);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == RESULT_OK) {
                if (data == null) {
                    Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
                }
            }
            Uri uri = data.getData();
            imgProfile.setImageURI(uri);
            imagePath = getRealPathFromUri(uri);
        }

        private String getRealPathFromUri(Uri uri) {
            String[] projection = {MediaStore.Images.Media.DATA};
            CursorLoader loader = new CursorLoader(getApplicationContext(),
                    uri, projection, null, null, null);
            Cursor cursor = loader.loadInBackground();
            int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String result = cursor.getString(colIndex);
            cursor.close();
            return result;
        }

        private void saveImageOnly() {
            File file = new File(imagePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                    file.getName(), requestBody);

            UsersAPI usersAPI = URL.getInstance().create(UsersAPI.class);
            Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);

            StrictModeClass.StrictMode();
            //Synchronous methid
            try {
                Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
                imageName = imageResponseResponse.body().getFilename();
                Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

        private void register() {



            String email = etEmail.getText().toString();
            String fullname = etFullName.getText().toString();
            String password = etPassword.getText().toString();
            String conpassword = etCPassword.getText().toString();
            String phone = etPhone.getText().toString();
            String mobile = etMphone.getText().toString();
            String address1 = etAddress1.getText().toString();
            String address2 = etAddress2.getText().toString();
            String address3 = etAddress3.getText().toString();

            User users = new User(email, fullname, password, conpassword, phone, mobile, address1, address2, address3, imageName);

            UsersAPI usersAPI = URL.getInstance().create(UsersAPI.class);
            Call<RegisterResponse> registerCall = usersAPI.registerUser(users);

            registerCall.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }