package com.s3.movieflex.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.s3.movieflex.R;
import com.s3.movieflex.adapters.sqlite.UserDataBase;

public class SignUp_Activity extends AppCompatActivity {
    UserDataBase userDataBase;
    boolean ammar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText nameEdt = findViewById(R.id.edt_username2);
        EditText passwordEdt = findViewById(R.id.edt_password2);
        EditText mailEdt = findViewById(R.id.edt_email2);
        TextView loginBtn = findViewById(R.id.tv_login);
        Button signUp = findViewById(R.id.btn_signup);
        userDataBase = new UserDataBase(this);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdt.getText().toString();
                String email = mailEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(getApplicationContext(), "Name is empty", Toast.LENGTH_SHORT).show();

                    ammar = true;
                }

                if (email.equals("")) {
                    Toast.makeText(getApplicationContext(), "Email is empty", Toast.LENGTH_SHORT).show();


                    ammar = true;
                }
                if (password.equals("")) {
                    Toast.makeText(getApplicationContext(), "password is empty", Toast.LENGTH_SHORT).show();

                    ammar = true;
                }

                if (!email.equals("") && !password.equals("")&&!name.equals("")) {
                    if (!email.equals("") && !(email.contains("@gmail.com"))) {
                        Toast.makeText(getApplicationContext(), "Invalid E-mail", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean check = userDataBase.if_email_exists(email);
                        if (check == false) {
                            boolean insertData = userDataBase.insertData(name, email, password);
                            if (insertData == true) {
                                Toast.makeText(getApplicationContext(), "you are in", Toast.LENGTH_SHORT).show();
                                nameEdt.setText("");

                                mailEdt.setText("");
                                passwordEdt.setText("");

                                Intent intent = new Intent(SignUp_Activity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        } else if (check == true && ammar == false) {
                            Toast.makeText(getApplicationContext(), "sorry ! user account not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


//                Intent intent = new Intent(SignUp_Activity.this, MainActivity.class);
//                startActivity(intent);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });

    }

}