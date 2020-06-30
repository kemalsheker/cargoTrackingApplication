package com.example.drn20;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegisterActivity extends AppCompatActivity {


    EditText fullname_reg;
    EditText surname_reg;
    EditText telno_reg;
    EditText email_reg;
    EditText password_reg;
    Button reg_button;
    ProgressBar registerProgress;


    String name;
    String surname;
    String email;
    String telNo;
    boolean notification;

    ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private Task uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        progressDialog = new ProgressDialog(this);

        fullname_reg = findViewById(R.id.name_reg);
        telno_reg = findViewById(R.id.phonenum_reg);
        email_reg = findViewById(R.id.email_reg);
        password_reg = findViewById(R.id.newPassword);
        reg_button = findViewById(R.id.registerBut);
        registerProgress = findViewById(R.id.register_progress);



        //entry button action
        fullname_reg.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View arg0, int arg1, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
                        (arg1           == KeyEvent.KEYCODE_ENTER)   )
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(fullname_reg.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        } );



        telno_reg.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View arg0, int arg1, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
                        (arg1           == KeyEvent.KEYCODE_ENTER)   )
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(telno_reg.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        } );

        email_reg.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View arg0, int arg1, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
                        (arg1           == KeyEvent.KEYCODE_ENTER)   )
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(email_reg.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        } );

        password_reg.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View arg0, int arg1, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
                        (arg1           == KeyEvent.KEYCODE_ENTER)   )
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(password_reg.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        } );

        ////
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();


        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = fullname_reg.getText().toString().trim();
                telNo = telno_reg.getText().toString().trim();
                email = email_reg.getText().toString().trim();
                String password = password_reg.getText().toString().trim();

                progressDialog.setMessage("Kayıt Olunuyor...");

                registerProgress.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Lütfen isim giriniz", Toast.LENGTH_SHORT).show();
                    registerProgress.setVisibility(View.INVISIBLE);
                    return;
                }

                if (TextUtils.isEmpty(telNo)) {
                    Toast.makeText(getApplicationContext(), "Lütfen telefon numaranızı giriniz", Toast.LENGTH_SHORT).show();
                    registerProgress.setVisibility(View.INVISIBLE);
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Lütfen şifre giriniz", Toast.LENGTH_SHORT).show();
                    registerProgress.setVisibility(View.INVISIBLE);
                    return;
                }



                if(password.length() < 6){

                    Toast.makeText(RegisterActivity.this , "Password too short..." , Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    sendUserData();
                                    finish();
                                    startActivity(new Intent(getApplicationContext() , EntryActivity.class));
                                    Toast.makeText(RegisterActivity.this , "Kayıt başarılı.", Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(RegisterActivity.this , "Kayıt kabul edilmedi.", Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });


            }
        });

    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        User user = new User(name, email ,telNo , notification );
        myRef.setValue(user);
    }



}


