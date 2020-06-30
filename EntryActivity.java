package com.example.drn20;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EntryActivity extends AppCompatActivity implements Tab1.OnFragmentInteractionListener,Tab2.OnFragmentInteractionListener {
       private static final String TAG = "EntryActivity";

    Toolbar toolbar;
    //ImageButton calculator;
    //ImageButton accountButton;

    BottomNavigationView bottomNavigationView;

    private FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthStateLitener;
    FirebaseUser firebaseUser;
    DatabaseReference myRef;
    FirebaseDatabase database;


    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_layout);


        //calculator = findViewById(R.id.calculator_button);
        toolbar = findViewById(R.id.toolBar);
      //  accountButton = findViewById(R.id.hesabim_button);
        bottomNavigationView = findViewById(R.id.bottomNavView);


        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(null);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("KARGOLARIM"));
        tabLayout.addTab(tabLayout.newTab().setText("ARŞİVİM"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        myAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        firebaseUser = myAuth.getCurrentUser();
        myRef = database.getReference();

        userID = firebaseUser.getUid();

        myRef = FirebaseDatabase.getInstance().getReference().child(firebaseUser.getUid());

        myAuthStateLitener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                } else {
                    // User is signed out
                    Intent intent = new Intent(EntryActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                // ...
            }
        };

        // for adding tabs
        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        // fetching data from firebase
       /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                personName.setText(Objects.requireNonNull(dataSnapshot.child("isim").getValue()).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

       // bottom navigation listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.trackSection:
                        break;
                    case R.id.getPrice_button:
                        Intent a = new Intent(EntryActivity.this,CalculatorActivity.class);
                        startActivity(a);
                        finish();
                        break;
                    case R.id.goToaccount_button:
                        Intent b = new Intent(EntryActivity.this,UserPage.class);
                        startActivity(b);
                        finish();
                        break;
                }
                return false;
            }
        });



        // GO TO settings for now it is sign out
        /*accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntryActivity.this, UserPage.class);
                startActivity(intent);
            }
        });*/

        //Go to calculator layout
        /*calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntryActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });*/


    }

    //for putting tabs
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //functions for fetching data

    //override for menu icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_signOut){
            Intent intent = new Intent(EntryActivity.this, MainActivity.class);
            startActivity(intent);
            myAuth.signOut();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



    //android back button pressed
    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        backButtonHandler();
        return;
    }

    private void backButtonHandler() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
