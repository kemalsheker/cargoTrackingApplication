package com.example.drn20;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class PriceActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;

    TextView price_text;
    ImageButton make_call;
    ImageButton send_email;
    ImageButton send_whatsapp;


    String userName;
    String userID;

    private FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthStateLitener;
    FirebaseUser firebaseUser;
    DatabaseReference myRef;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price_layout);

        myAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        firebaseUser = myAuth.getCurrentUser();
        myRef = database.getReference();
        userID = firebaseUser.getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child(firebaseUser.getUid());



        price_text = findViewById(R.id.price_text);
        make_call = findViewById(R.id.make_call);
        send_email = findViewById(R.id.send_email);
        send_whatsapp = findViewById(R.id.send_whatsapp);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userName = (Objects.requireNonNull(dataSnapshot.child("isim").getValue()).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        price_text.setText("Merhabalar,\n Talebiniz için teşekkür ederiz.Fiyat teklifimiz aşağıda bulabilirsiniz.Müşteri temsilcimiz sizinle mutlaka " +
                "iletişime geçecektir.\n\n İşlem tipi:" + getIntent().getStringExtra("TYPE") + "\n\n" + "Ülke: "+ getIntent().getStringExtra("COUNTRY") +
                "\n\n" + "Toplam ağırlık: "+ getIntent().getStringExtra("MASS") + "\n\n" + "Toplam İndirimli Teklifimiz: "+getIntent().getStringExtra("RESULT")+
                "€\n\n Kapıdan kapıya ulaşım fiyatıdır.\nGümrüklerde oluşabilecek uygulama ve vergiler hariçtir.\nUnutmayın, şirketseniz veya düzenli gönderileriniz " +
                "varsa, daha özel fiyatlar alabilirsiniz. ");

        make_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getStringExtra("TYPE").equals("Getirt"))
                    sendEmail();
                else
                    sendEmail2();
            }
        });

        send_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getIntent().getStringExtra("TYPE").equals("Gönder"))
                    sendMessage();
                else
                    sendMessage2();

            }
        });


    }

    //Whatsapp'e mesaj gönderme dialogları
    @SuppressLint("IntentReset")
    private void sendMessage2() {

        String number = "+905053663700";

        String whatsAppMessage = " Merhabalar,\n\n Aşağıda detayları olan gönderim ile ilgili yardımcı olabilir misiniz?  \n\n Çıkış Ülkesi: " +  getIntent().getStringExtra("COUNTRY") + "\n\n Varış Ülke: Türkiye \n\n Toplam ağırlık: "+getIntent().getStringExtra("MASS")
                + "\n\n Toplam indirimli teklifimiz: " + getIntent().getStringExtra("RESULT") + "€" ;

        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(
                        String.format("https://api.whatsapp.com/send?phone=%s&text=%s",
                                number,whatsAppMessage ))));

    }

    @SuppressLint("IntentReset")
    private void sendMessage() {

        String number = "+905053663700";


        String whatsAppMessage = " Merhabalar,\n\n Aşağıda detayları olan gönderim ile ilgili yardımcı olabilir misiniz? \n\n Çıkış Ülkesi: Türkiye\n\n Varış Ülke: "+getIntent().getStringExtra("COUNTRY") +"\n\n Toplam ağırlık: "+getIntent().getStringExtra("MASS")
                + "\n\n Toplam indirimli teklifimiz: " + getIntent().getStringExtra("RESULT") + "€" ;
        /*
        Uri uri = Uri.parse("smsto:" + number );
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.putExtra(Intent.EXTRA_TEXT, whatsAppMessage);
        i.setType("text/plain");
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
        Uri uri = Uri.parse("smsto:" + "+905053663700");
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
        sendIntent.putExtra(Intent.EXTRA_TEXT, " Merhabalar,\n\n Aşağıda detayları olan gönderim ile ilgili yardımcı olabilirmisinzi? \n\n Çıkış Ülkesi: Türkiye\n\n Varış Ülke: "+getIntent().getStringExtra("COUNTRY") +"\n\n Toplam ağırlık: "+getIntent().getStringExtra("MASS")
                + "\n\n Toplam indirimli teklifimiz: " + getIntent().getStringExtra("RESULT") + "€");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);*/
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(
                        String.format("https://api.whatsapp.com/send?phone=%s&text=%s",
                                number,whatsAppMessage ))));
    }

    //mail göderme methodları
    private void sendEmail2() {
        String to= "info@kargomkolay.com" ;
        String subject = "Fiyat Sorgulama";
        String message = " Merhabalar,\n\n Aşağıda detayları olan gönderim ile ilgili yardımcı olabilir misiniz? \n\n Çıkış Ülkesi: Türkiye\n\n Varış Ülke: "+getIntent().getStringExtra("COUNTRY") +"\n\n Toplam ağırlık: "+getIntent().getStringExtra("MASS")
                + "\n\n Toplam indirimli teklifimiz: " + getIntent().getStringExtra("RESULT") + "€" ;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL ,new String[]{ to});
        intent.putExtra(Intent.EXTRA_SUBJECT , subject);
        intent.putExtra(Intent.EXTRA_TEXT , message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent , "Choose an email client"));
    }

    private void sendEmail() {

        String to= "info@kargomkolay.com" ;
        String subject = "Fiyat Sorgulama";
        String message = " Merhabalar,\n\n Aşağıda detayları olan gönderim ile ilgili yardımcı olabilir misiniz?  \n\n Çıkış Ülkesi: " +  getIntent().getStringExtra("COUNTRY") + "\n\n Varış Ülke: Türkiye \n\n Toplam ağırlık: "+getIntent().getStringExtra("MASS")
                + "\n\n Toplam indirimli teklifimiz: " + getIntent().getStringExtra("RESULT") + "€" ;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL ,new String[]{ to});
        intent.putExtra(Intent.EXTRA_SUBJECT , subject);
        intent.putExtra(Intent.EXTRA_TEXT , message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent , "Choose an email client"));

    }

    //telefon araması yapma ve izin isteme
    private void makePhoneCall() {


            if (ContextCompat.checkSelfPermission(PriceActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(PriceActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String number = "08505505500";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));//change the number
                startActivity(callIntent);
            }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

