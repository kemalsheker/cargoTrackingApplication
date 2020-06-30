package com.example.drn20;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class CalculatorActivity  extends AppCompatActivity {

    String countries[] = {"ABD",
            "Afganistan",
            "Almanya",
            "Amerikan Samoa",
            "Andorra",
            "Angola",
            "Anguilla",
            "Antigua",
            "Arjantin",
            "Arnavutluk",
            "Aruba",
            "Avustralya",
            "Avusturya",
            "Azerbeycan",
            "Bahamalar",
            "Bahreyn",
            "Bangladeş",
            "Barbados",
            "Batı Samova",
            "Belarus",
            "Belçika",
            "Belize",
            "Benin",
            "Bermuda",
            "Birleşik Arab Emirlikleri",
            "Bolivya",
            "Bosna Hersek",
            "Botsvana",
            "Brezilya",
            "Brunei Sultanlığı",
            "Bulgaristan",
            "Burkina Faso",
            "Burundi",
            "Butan",
            "Cap Verde",
            "Cayman Adaları",
            "Cebelitarık",
            "Cezayir",
            "Cibuti",
            "Cook Adaları",
            "Çad",
            "Çek Cumhuriyeti",
            "Çin",
            "Danimarka",
            "Dominik",
            "Dominik Cumhuriyeti",
            "Ekvador",
            "Ekvator Ginesi",
            "El Salvador",
            "Endonezya",
            "Eritre",
            "Ermenistan",
            "Estonya",
            "Ethiopya",
            "Faroe Adaları",
            "Fas",
            "Fiji",
            "Fildişi Sahili",
            "Filipinler",
            "Finlandiya",
            "Fransa",
            "Fransız Guenası",
            "Gabon",
            "Gambiya",
            "Gana",
            "Gine",
            "Greenland",
            "Grenada",
            "Guadeloupe",
            "Guam",
            "Guatemala",
            "Guyana",
            "Güney Afrika",
            "Gürcistan",
            "Haiti",
            "Hırvatistan",
            "Hindistan",
            "Hollanda",
            "Honduras",
            "Hong Kong",
            "Irak",
            "İngiltere",
            "İran",
            "İrlanda",
            "İspanya",
            "İsrail",
            "İsveç",
            "İsviçre",
            "İtalya",
            "İzlanda",
            "Jamayka",
            "Japonya",
            "Kamboçya",
            "Kamerun",
            "Kanada",
            "Katar",
            "Kazakistan",
            "Kenya",
            "Kıbrıs Rum Kesimi",
            "Kırgızistan",
            "Kiribati",
            "Kolombiya",
            "Kongo Cumhuriyeti",
            "Kore (Güney)",
            "Kosova",
            "Kosta Rika",
            "Kuveyt",
            "Küba",
            "Lao Halkın Demokratik Cumhuriyeti",
            "Lesoto",
            "Letonya",
            "Liberya",
            "Libya Arab Cemahiriyesi",
            "Lihtenştayn",
            "Litvanya",
            "Lübnan",
            "Lüksemburg",
            "Macaristan",
            "Macau",
            "Madagaskar",
            "Makedonya",
            "Malavi",
            "Maldivler",
            "Malezya",
            "Mali",
            "Malta",
            "Marshall Adaları",
            "Martinik",
            "Meksika",
            "Mısır",
            "Mikronezya, Federal Eyaleti",
            "Miyanmar",
            "Moğolistan",
            "Moldova Cumhuriyeti",
            "Monako",
            "Montserrat",
            "Moritanya",
            "Moritius",
            "Mozambik",
            "Namibya",
            "Nauru",
            "Nepal",
            "Nijer",
            "Nijerya",
            "Nikaragua",
            "Norfolk Adası",
            "Norveç",
           " Orta Afrika Cumhuriyeti",
           " Özbekistan",
           " Pakistan",
            "Panama",
           "Papua Yeni Gine",
            "Paraguay",
            "Peru",
            "Polonya",
            "Portekiz",
            "Porto Riko",
            "Reunion Adası",
            "Romanya",
            "Ruanda",
            "Rusya Federasyonu",
            "Saint Kitts ve Nevis",
            "Saint Vincent ve the Grenadines",
            "San Marino",
            "Santa Luçia",
            "Senegal",
            "Seyşeller",
            "Sierra Leone",
            "Singapur",
            "Sirbistan",
            "Slovak Cumhuriyeti",
            "Slovenya",
            "Solomon Adaları",
            "Sri Lanka",
            "Sudan",
            "Surinam",
            "Suriye Arab Cumhuriyeti",
            "Suudi Arabistan",
            "Svaziland",
            "Şili",
            "Tacikistan",
            "Tanzanya",
            "Tayland",
            "Tayvan",
            "Togo",
            "Tonga",
            "Trinidad ve Tobago",
            "Tunus",
            "Turks ve Caicos Islands",
            "Tuvalu",
            "Uganda",
            "Ukrayna",
            "Umman",
            "Uruguay",
            "Ürdün",
            "Vanuatu",
            "Venezuela",
            "Vietnam",
            "Virjin Adaları",
            "Yemen",
            "Yeni Kaledonya",
            "Yeni Zelanda",
            "Yunanistan",
            "Zambia",
            "Zimbabve"};


    String mass[] = {
            "0.5 Kg",
            "1.0 Kg",
            "1.5 Kg",
            "2.0 Kg",
            "2.5 Kg",
            "3.0 Kg",
            "3.5 Kg",
            "4.0 Kg",
            "4.5 Kg",
            "5.0 Kg",
            "5.5 Kg",
            "6.0 Kg",
            "6.5 Kg",
            "7.0 Kg",
            "7.5 Kg",
            "8.0 Kg",
            "8.5 Kg",
            "9.0 Kg",
            "9.5 Kg",
            "10.0 Kg",
            "11.0 Kg",
            "12.0 Kg",
            "13.0 Kg",
            "14.0 Kg",
            "15.0 Kg",
            "16.0 Kg",
            "17.0 Kg",
            "18.0 Kg",
            "19.0 Kg",
            "20.0 Kg",
            "21.0 Kg",
            "22.0 Kg",
            "23.0 Kg",
            "24.0 Kg",
            "25.0 Kg",
            "26.0 Kg",
            "27.0 Kg",
            "28.0 Kg",
            "29.0 Kg",
            "30.0 Kg",
    };

    double numeric[] = {
             0.5 ,
            1.0 ,
            1.5 ,
            2.0 ,
            2.5 ,
            3.0 ,
            3.5 ,
            4.0 ,
            4.5 ,
            5.0 ,
            5.5 ,
            6.0 ,
            6.5 ,
            7.0 ,
            7.5 ,
            8.0 ,
            8.5 ,
            9.0 ,
            9.5 ,
            10.0 ,
            11.0 ,
            12.0 ,
            13.0 ,
            14.0 ,
            15.0 ,
            16.0 ,
            17.0 ,
            18.0 ,
            19.0 ,
            20.0 ,
            21.0 ,
            22.0 ,
            23.0 ,
            24.0 ,
            25.0 ,
            26.0 ,
            27.0 ,
            28.0 ,
            29.0 ,
            30.0 ,
    };



    String massTransition [] = {

            "halfKg",
            "oneKg",
            "oneAndHalfKg",
            "twoKg",
            "twoAndHalfKg",
            "threeKg",
            "threeAndhalfKg",
            "fourKg",
            "fourAndhalfKg",
            "fiveKg",
            "fiveAndhalfKg",
            "sixKg",
            "sixAndhalfKg",
            "sevenKg",
            "sevenAndhalfKg",
            "eightKg",
            "eightAndhalfKg",
            "nineKg",
            "nineAndhalfKg",
            "tenKg",
            "elevenKg",
            "twelveKg",
            "thirteenKg",
            "fourtennKg",
            "fiftennKg",
            "sixteenKg",
            "seventeenKg",
            "eighteenKg",
            "nineteenKg",
            "twentyKg",
            "twentyOneKg",
            "twentyTwoKg",
            "twentyThreeKg",
            "twentyFourKg",
            "twentyFiveKg",
            "twentySixKg",
            "twentySevenKg",
            "twentyEightKg",
            "twentyNineKg",
            "thirtyKg",
    };


    LinearLayout one;
    TextView test;
    TextView choose;
    EditText country;
    EditText kg_button;
    EditText en_text;
    EditText boy_text;
    EditText yukseklik_text;
    Button price_button;
    Spinner countryName;
    Spinner weightSpinner;
    RadioGroup radioGroup;
    RadioGroup radioGroup2;

    BottomNavigationView bottomview;

    private FirebaseAuth myAuth;

    DatabaseReference myRef;
    FirebaseDatabase database;


    String str1;
    String str2;
    String choice;
    String str3;
    int b = 1;
    int compare;
    int p;
    int forCountry;
    int forMass;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        countryName = findViewById(R.id.countrySpinner);
        price_button = findViewById(R.id.price_button);
        weightSpinner = findViewById(R.id.WeightSpinner);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup2 = findViewById(R.id.radioGroup2);
        choose = findViewById(R.id.choose);
        en_text = findViewById(R.id.en_text);
        boy_text = findViewById(R.id.boy_text);
        yukseklik_text = findViewById(R.id.yukseklik_text);
        one = findViewById(R.id.infos);
        bottomview = findViewById(R.id.calculatorBottom);

        myAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference();

        int selectedId = radioGroup.getCheckedRadioButtonId();


        en_text.setText("0");
        boy_text.setText("0");
        yukseklik_text.setText("0");


        one.setVisibility(View.INVISIBLE);
        radioGroup.check(R.id.send_button);
        radioGroup2.check(R.id.evrak_button);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.send_button) {
                    b = 1;
                    choose.setText("Nereye:");
                } else if(checkedId == R.id.get_button) {
                    b=2;
                    choose.setText("Nereden:");
                }
            }
        });


        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.evrak_button){
                    one.setVisibility(View.INVISIBLE);
                    en_text.setText("0");
                    boy_text.setText("0");
                    yukseklik_text.setText("0");
                }
                else{
                    en_text.setText("");
                    boy_text.setText("");
                    yukseklik_text.setText("");
                    one.setVisibility(View.VISIBLE);
                }
            }
        });



        // another adding
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, countries);
        countryName.setAdapter(spinnerAdapter);
        countryName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                str1 = countries[i];

                forCountry = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> spinnerAdapter_2 = new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, mass);
        weightSpinner.setAdapter(spinnerAdapter_2);
        weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                str2 = massTransition[i];

                compare = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //press enter
        en_text.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View arg0, int arg1, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
                        (arg1           == KeyEvent.KEYCODE_ENTER)   )
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(en_text.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        } );


        boy_text.setOnKeyListener(new View.OnKeyListener()
        {

            public boolean onKey(View arg0, int arg1, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
                        (arg1           == KeyEvent.KEYCODE_ENTER)   )
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(boy_text.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        } );


        yukseklik_text.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View arg0, int arg1, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
                        (arg1           == KeyEvent.KEYCODE_ENTER)   )
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(yukseklik_text.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        } );



        // getting the final price
        price_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String en = en_text.getText().toString();
                String boy = boy_text.getText().toString();
                String yukseklik = yukseklik_text.getText().toString();


                if (TextUtils.isEmpty(en)) {
                    Toast.makeText(getApplicationContext(), "Lütfen en giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Parola girilmemiş ise kullanıcıyı uyarıyoruz.
                if (TextUtils.isEmpty(boy)) {
                    Toast.makeText(getApplicationContext(), "Lütfen boy giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(yukseklik)) {
                    Toast.makeText(getApplicationContext(), "Lütfen yükseklik giriniz giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }


                double en_result = Integer.parseInt(en);

                double boy_result = Integer.parseInt(boy);

                double yukseklik_result = Integer.parseInt(yukseklik);

                final double result = en_result*boy_result*yukseklik_result /5000;

                if(result > 30.0){
                    Toast.makeText(getApplicationContext(), "Kargo boyutu 30kg'yi geçemez", Toast.LENGTH_SHORT).show();
                    return;
                }

                double rslt = roundUp(result);

                for (int k = 0 ; k < numeric.length ; k++){

                    if( rslt == numeric[k]){
                        p = k;
                    }

                }

                if(p > compare){
                    str2 = massTransition[p];
                    forMass = p;
                }
                else {
                    str2 = massTransition[compare];
                    forMass = compare;
                }



                if(b == 1) {
                    myRef = FirebaseDatabase.getInstance().getReference().child("ihracat").child(str1);
                    myRef.addValueEventListener(new ValueEventListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Double value = Double.parseDouble(Objects.requireNonNull(dataSnapshot.child(str2).getValue()).toString());
                            Double sonuc = Math.round(value * 100d) / 100d;
                            Intent intent = new Intent(CalculatorActivity.this , PriceActivity.class);
                            intent.putExtra("RESULT" , sonuc.toString());
                            intent.putExtra("COUNTRY" , str1);
                            intent.putExtra("MASS" , mass[forMass]);
                            intent.putExtra("TYPE" , "Gönder");
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    else if(b == 2) {
                        myRef = FirebaseDatabase.getInstance().getReference().child("ithalat").child(str1);
                        myRef.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Double value = Double.parseDouble(Objects.requireNonNull(dataSnapshot.child(str2).getValue()).toString());
                                Double sonuc = Math.round(value * 100d) / 100d;
                                Intent intent = new Intent(CalculatorActivity.this , PriceActivity.class);
                                intent.putExtra("RESULT" , sonuc.toString());
                                intent.putExtra("COUNTRY" , str1);
                                intent.putExtra("MASS" , mass[forMass]);
                                intent.putExtra("TYPE" , "Getirt");
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                }
            }
        });

        // navigation bar listener
        bottomview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.getPrice_button:
                        break;
                    case R.id.trackSection:
                        Intent a = new Intent(CalculatorActivity.this,EntryActivity.class);
                        startActivity(a);
                        finish();
                        break;
                    case R.id.goToaccount_button:
                        Intent b = new Intent(CalculatorActivity.this,UserPage.class);
                        startActivity(b);
                        finish();
                        break;
                }
                return false;
            }
        });

    }

    //android back button pressed
    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        backButtonHandler();
        return;
    }

    private void backButtonHandler() {
        Intent z = new Intent(CalculatorActivity.this,EntryActivity.class);
        startActivity(z);
    }


    private double roundUp (double a){

        if(a < 0.5){
            return 0.5;
        }
        else if (a == 0.5){
            return 0.5;
        }
        else if (a > 0.5  && a < 1.0){
            return 1.0;
        }
        else if (a == 1.0){
            return 1.0;
        }
        else if (a > 1.0 && a < 1.5){
            return 1.5;
        }
        else if (a == 1.5){
            return 1.5;
        }
        else if (a > 1.5 && a < 2.0){
            return 2.0;
        }
        else if (a == 2.0){
            return 2.0;
        }
        else if (a > 2.0 && a < 2.5){
            return 2.5;
        }
        else if (a == 2.5){
            return 2.5;
        }
        else if (a > 2.5 && a < 3.0){
            return 3.0;
        }
        else if (a == 3.0){
            return 3.0;
        }
        else if ( a > 3.0 && a < 3.5){
            return 3.5;
        }
        else if (a == 3.5){
            return 3.5;
        }
        else if (a > 3.5 && a < 4.0){
            return 4.0;
        }
        else if (a == 4.0){
            return 4.0;
        }
        else if (a > 4.0 && a < 4.5){
            return 4.5;
        }
        else if (a == 4.5){
            return 4.5;
        }
        else if (a  > 4.5 && a < 5.0){
            return 5.0;
        }
        else if (a == 5.0){
            return 5.0;
        }
        else if (a > 5.0 && a < 5.5 ){
            return 5.5;
        }
        else if (a == 5.5){
            return 5.5;
        }
        else if (a > 5.5 && a < 6.0){
            return 6.0;
        }
        else if (a == 6.0){
            return 6.0;
        }
        else if (a > 6.0 && a < 6.5){
            return 6.5;
        }
        else if (a == 6.5){
            return 6.5;
        }
        else if (a > 6.5 && a < 7.0){
            return 7.0;
        }
        else if (a == 7.0){
            return 7.0;
        }
        else if (a > 7.0 && a < 7.5){
            return 7.5;
        }
        else if (a > 7.5 && a < 8.0){
            return 8.0;
        }else if (a == 8.0){
            return 8.0;
        }else if (a > 8.0 && a < 8.5){
            return 8.5;
        }else if (a == 8.5){
            return 8.5;
        }else if (a > 8.5 && a < 9.0){
            return 9.0;
        }else if (a == 9.0){
            return 9.0;
        }else if (a > 9.0 && a < 9.5){
            return 9.5;
        }else if (a == 9.5){
            return 9.5;
        }else if (a > 9.5 && a < 10.0){
            return 10.0;
        }
        else if (a == 10.0){
            return 10.0;
        }
        else if (a > 10.0 && a <= 11.0){
            return 11.0;
        }
        else if (a > 11.0 && a <= 12.0){
            return 12.0;
        }
        else if (a > 12.0 && a <= 13.0){
            return 13.0;
        }
        else if (a > 13.0 && a <= 14.0){
            return 14.0;
        }else if (a > 14.0 && a <= 15.0){
            return 15.0;
        }
        else if (a > 15.0 && a <= 16.0){
            return 16.0;
        }else if (a > 16.0 && a <=17.0){
            return 17.0;
        }
        else if ( a > 17.0 && a <= 18.0){
            return 18.0;
        }
        else if (a > 18.0 && a <= 19.0){
            return 19.0;
        }
        else if (a > 19.0 && a <= 20.0 ){
            return 20.0;
        }else if (a > 20.0 && a <= 21.0){
            return 21.0;
        }
        else if (a > 21.0 && a <= 22.0){
            return 22.0;
        }
        else if (a > 22.0 && a <= 23.0){
            return 23.0;
        }
        else if (a > 23.0 && a <= 24.0){
            return 24.0;
        }
        else if (a > 24.0 && a <= 25.0){
            return 25.0;
        }
        else if (a > 25.0 && a <= 26.0){
            return 26.0;
        }
        else if (a > 26.0 && a <= 27.0){
            return 27.0;
        }
        else if (a > 27.0 && a <= 28.0){
            return 28.0;
        }
        else if (a > 28.0 && a <= 29.0){
            return 29.0;
        }
        else if (a > 29.0 && a <= 30.0){
            return 30.0;
        }

        return 0;
    }

}
