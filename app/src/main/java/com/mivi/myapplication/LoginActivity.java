package com.mivi.myapplication;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mivi.myapplication.common.CommonMethods;
import com.mivi.myapplication.databinding.ActivityLoginBinding;
import com.mivi.myapplication.model.Collection;

import java.io.IOException;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        context = LoginActivity.this;

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValidEmail(binding.edEmail.getText()))
                    Toast.makeText(context, "Please enter valid email id", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(binding.edPassword.getText()))
                    Toast.makeText(context, "Please enter password", Toast.LENGTH_SHORT).show();
                else {
                    String email = binding.edEmail.getText().toString();
                    Collection collectionEntity = new Gson().fromJson(CommonMethods.loadJSONFromAsset(context), Collection.class);
                    if (collectionEntity != null && email.equals(collectionEntity.getData().getAttributes().getEmailAddress())) {
                        startActivity(new Intent(context, SplashScreenActivity.class));
                        finish();
                    } else {
                        Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
