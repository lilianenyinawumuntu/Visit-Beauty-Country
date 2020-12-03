package com.liliane.visitybeautycountry;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class PasswordActivity extends AppCompatActivity {
    EditText email;
    Button reset;
    TextView login;
    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        email=findViewById(R.id.edit_email);
        reset=findViewById(R.id.btn_signup);
        login=findViewById(R.id.tv_Login);

        firebaseAuth= FirebaseAuth.getInstance();
        dialog= new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Resetting is loading, Please wait");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail;
                useremail=email.getText().toString().trim();
                if (useremail.isEmpty()){
                    email.setError("Please, Enter email");
                }
                else {

                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                Toast.makeText(PasswordActivity.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(PasswordActivity.this, "Password reset Fail", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),PasswordActivity.class));
                            }
                        }
                    });
                }
            }

        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
