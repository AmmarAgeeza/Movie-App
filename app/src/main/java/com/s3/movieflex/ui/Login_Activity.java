package com.s3.movieflex.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.s3.movieflex.R;
import com.s3.movieflex.adapters.sqlite.UserDataBase;

public class Login_Activity extends AppCompatActivity {
    Boolean check;
    UserDataBase userDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        EditText nameEdt = findViewById(R.id.edt_username);
        EditText passwordEdt = findViewById(R.id.edt_password);
        EditText mailEdt = findViewById(R.id.edt_email);
        Button loginBtn = findViewById(R.id.btn_login);
        TextView signUpBtn = findViewById(R.id.tv_signup);
        userDataBase = new UserDataBase(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(email.equals("")&&password.equals("")) {
//                    Toast.makeText(getApplicationContext(), "mail is empty", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), "password is empty", Toast.LENGTH_SHORT).show();
//
//                }
                if (nameEdt.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "user name is empty", Toast.LENGTH_SHORT).show();
                else if (mailEdt.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "mail is empty", Toast.LENGTH_SHORT).show();

                else if (passwordEdt.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "password is empty", Toast.LENGTH_SHORT).show();
                else {
                    if (!mailEdt.getText().toString().equals("") && !(mailEdt.getText().toString().contains("@gmail.com")) ) {
                        Toast.makeText(getApplicationContext(), " Invalid e-mail", Toast.LENGTH_SHORT).show();

                    } else {
                        check = userDataBase.if_user_exists(mailEdt.getText().toString(), passwordEdt.getText().toString());
                        if (check == true) {
//                            mailEdt.setText("");
//                            passwordEdt.setText("");
                            Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                            startActivity(intent);

                        } else {

                            mailEdt.setText("");
                            nameEdt.setText("");
                            passwordEdt.setText("");
                            Toast.makeText(getApplicationContext(), "This Account does not exist , please sign up", Toast.LENGTH_SHORT).show();


                        }
                    }
                }


            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Login_Activity.this, SignUp_Activity.class);
                startActivity(intent2);
            }
        });
    }
   // @Override
//    protected void onSaveInstanceState(@Nullable Bundle savedInstanceState) {
//        if(savedInstanceState!=null)
//        {
//
//            nameEdt=findViewById(R.id.edt_name2);
//            mailEdt=findViewById(R.id.edt_mail2);
//            passwordEdt=findViewById(R.id.edt_phone2);
//            savedInstanceState.putString("Name",nameEdt.getText().toString());
//            savedInstanceState.putString("email",mailEdt.getText().toString());
//            savedInstanceState.putString("password",passwordEdt.getText().toString());
//        }
//        super.onSaveInstanceState(savedInstanceState);
//
//    }
}