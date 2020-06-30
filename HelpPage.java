package com.example.drn20;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HelpPage extends AppCompatActivity {


    ImageView ımageView1;
    private ImageView backButton;
    private LinearLayout help1;
    private LinearLayout help2;
    private LinearLayout help3;
    private LinearLayout help4;
    private LinearLayout help5;
    private LinearLayout help6;
    private LinearLayout help7;
    private LinearLayout help8;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);

        help1 = findViewById(R.id.help1);
        help2 = findViewById(R.id.help2);
        help3 = findViewById(R.id.help3);
        help4 = findViewById(R.id.help4);
        help5 = findViewById(R.id.help5);
        help6 = findViewById(R.id.help6);
        help7 = findViewById(R.id.help7);
        help8 = findViewById(R.id.help8);

        help1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTohelp1();
            }
        });

        help2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTohelp2();
            }
        });

        help3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTohelp3();
            }
        });

        help4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTohelp4();
            }
        });

        help5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTohelp5();
            }
        });

        help6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTohelp6();
            }
        });

        help7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTohelp7();
            }
        });

        help8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTohelp8();
            }
        });



        System.gc();
    }



    // functions for accessing websites
    private void goTohelp1(){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.kargomkolay.com/yurtdisi-kargo-islemlerinde-yasanan-gumruk-problemleri/"));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(HelpPage.this, "Sayfaya bağalanılamadı.", Toast.LENGTH_SHORT).show();
        }
    }

    private void goTohelp2(){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kargomkolay.com/ptt-yurt-disi-kargo-fiyatlari-2019-yurt-disi-kargo-fiyati-hesaplama/"));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(HelpPage.this, "Sayfaya bağalanılamadı.", Toast.LENGTH_SHORT).show();
        }
    }

    private void goTohelp3(){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kargomkolay.com/yurtdisina-kargo-gonderirken-dikkat-edilecek-9-kural/"));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(HelpPage.this, "Sayfaya bağalanılamadı.", Toast.LENGTH_SHORT).show();
        }
    }

    private void goTohelp4(){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kargomkolay.com/eihracat-nedir/"));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(HelpPage.this, "Sayfaya bağalanılamadı.", Toast.LENGTH_SHORT).show();
        }
    }

    private void goTohelp5(){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kargomkolay.com/kargo-nasil-paketlenir/"));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(HelpPage.this, "Sayfaya bağalanılamadı.", Toast.LENGTH_SHORT).show();
        }
    }

    private void goTohelp6(){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kargomkolay.com/gumruk-vergisi-hesaplama/"));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(HelpPage.this, "Sayfaya bağalanılamadı.", Toast.LENGTH_SHORT).show();
        }
    }

    private void goTohelp7(){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kargomkolay.com/express-kargo-ile-hava-kargo-arasindaki-fark-nedir/"));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(HelpPage.this, "Sayfaya bağalanılamadı.", Toast.LENGTH_SHORT).show();
        }
    }

    private void goTohelp8(){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.kargomkolay.com/kargo-kolilerinin-zarar-gormemesi/"));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(HelpPage.this, "Sayfaya bağalanılamadı.", Toast.LENGTH_SHORT).show();
        }
    }



}
