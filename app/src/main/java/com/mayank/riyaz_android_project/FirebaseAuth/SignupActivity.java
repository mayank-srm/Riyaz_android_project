package com.mayank.riyaz_android_project.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mayank.riyaz_android_project.MainActivity;
import com.mayank.riyaz_android_project.R;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText getname,getemail,getpassword;
    String name,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getname = findViewById(R.id.Name);
        getemail = findViewById(R.id.Email);
        getpassword = findViewById(R.id.Password);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    public void Login(View view) {
        Intent intent = new Intent(SignupActivity.this,SigninActivity.class);
        startActivity(intent);
    }

    public void SignUp(View view) {
        name = getname.getText().toString();
        email = getemail.getText().toString();
        password = getpassword.getText().toString();
        if(name != null && email != null) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(SignupActivity.this, "Successful login!",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, "Failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
