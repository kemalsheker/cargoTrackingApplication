package com.example.drn20;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetActivity extends AppCompatActivity {


    Button resetButton;
    EditText sendMail;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_layout);

        resetButton = findViewById(R.id.reset_button);
        sendMail = findViewById(R.id.email_send);
        progressBar = findViewById(R.id.reset_progress);

        firebaseAuth = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.sendPasswordResetEmail(sendMail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ResetActivity.this ,"Mail'inize gönderilmiştir.." , Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(ResetActivity.this ,task.getException().getMessage() , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }

}
