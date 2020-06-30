package com.example.drn20;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class NewPasswordActivity extends AppCompatActivity {

    Toolbar toolbarNewPassword;
    private Button newPassword;
    private EditText getNewPassword;
    private ProgressBar newPasswordProgress;

    private FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthStateLitener;
    FirebaseUser firebaseUser;
    DatabaseReference myRef;
    FirebaseDatabase database;

    String userID;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpassword_layout);

        toolbarNewPassword = findViewById(R.id.toolBar_newPassword);
        setSupportActionBar(toolbarNewPassword);
        getSupportActionBar().setTitle(null);

        newPassword = findViewById(R.id.newPassword_button);
        getNewPassword = findViewById(R.id.getNewPassword);
        newPasswordProgress = findViewById(R.id.newPasswordProgress);

        myAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        firebaseUser = myAuth.getCurrentUser();
        myRef = database.getReference();
        userID = firebaseUser.getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child(firebaseUser.getUid());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                email = (Objects.requireNonNull(dataSnapshot.child("email").getValue()).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        newPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_entry = getNewPassword.getText().toString();
                newPasswordProgress.setVisibility(View.VISIBLE);
                if (email_entry.equals(email)){

                    myAuth.sendPasswordResetEmail(getNewPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(NewPasswordActivity.this ,"Mail'inize gönderilmiştir.." , Toast.LENGTH_LONG).show();
                                        newPasswordProgress.setVisibility(View.INVISIBLE);
                                    }
                                    else{
                                        Toast.makeText(NewPasswordActivity.this ,task.getException().getMessage() , Toast.LENGTH_LONG).show();
                                        newPasswordProgress.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });

                }
                else{
                    Toast.makeText(NewPasswordActivity.this ,"Yanlış e-mail adresi.." , Toast.LENGTH_LONG).show();
                    newPasswordProgress.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
}
