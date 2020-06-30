package com.example.drn20;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

public class UserPage extends AppCompatActivity implements Tab3.OnFragmentInteractionListener,Tab4.OnFragmentInteractionListener {

    Toolbar toolbarAcc;
    BottomNavigationView bottomView;



    private FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthStateLitener;
    FirebaseUser firebaseUser;
    DatabaseReference myRef;
    FirebaseDatabase database;


    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userpage_layout);

        toolbarAcc = findViewById(R.id.toolBar_account);
        bottomView = findViewById(R.id.userPageButtom);

        setSupportActionBar(toolbarAcc);

        getSupportActionBar().setTitle(null);

        TabLayout tabLayoutAccount = (TabLayout)findViewById(R.id.tablayoutAccount);
        tabLayoutAccount.addTab(tabLayoutAccount.newTab().setText("HESABIM"));
        tabLayoutAccount.addTab(tabLayoutAccount.newTab().setText("DİĞER"));
        tabLayoutAccount.setTabGravity(TabLayout.GRAVITY_FILL);




        //bottom navigation
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.goToaccount_button:
                        break;
                    case R.id.getPrice_button:
                        Intent a = new Intent(UserPage.this,CalculatorActivity.class);
                        startActivity(a);
                        finish();
                        break;
                    case R.id.trackSection:
                        Intent b = new Intent(UserPage.this,EntryActivity.class);
                        startActivity(b);
                        finish();
                        break;
                }
                return false;
            }
        });

        //for adding tabs
        final ViewPager viewPager1 = (ViewPager)findViewById(R.id.pagerAccount);
        final PagerAdapter2 adapter = new PagerAdapter2(getSupportFragmentManager(),tabLayoutAccount.getTabCount());
        viewPager1.setAdapter(adapter);
        viewPager1.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutAccount));
        tabLayoutAccount.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager1.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //for signout action
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
                    Intent intent = new Intent(UserPage.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                // ...
            }
        };
        ////////////


    }

    // android back button pressed
    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        backButtonHandler();
        return;
    }

    private void backButtonHandler() {
        Intent z = new Intent(UserPage.this,EntryActivity.class);
        startActivity(z);
    }



    //for putting tabs
    @Override
    public void onFragmentInteraction(Uri uri) {

    }



}
