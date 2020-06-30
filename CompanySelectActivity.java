package com.example.drn20;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class CompanySelectActivity  extends AppCompatActivity implements CargoCompanyAdapter.EventHandler {


    private  static List<CargoCompanies> cargoCompaniesList;
    RecyclerView myrecyclerview_company;
    Toolbar toolbar_selector;
    private CargoCompanyAdapter cargoCompanyAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cargocompanies_layout);

        CargoCompanies [] companies = {
                new CargoCompanies("DHL"),
                new CargoCompanies("UPS"),
                new CargoCompanies("FEDEX"),
                new CargoCompanies("TNT"),
                new CargoCompanies("PTT"),
                new CargoCompanies("ARAS KARGO"),
                new CargoCompanies("MNG KARGO"),
                new CargoCompanies("YURTİÇİ KARGO"),
                new CargoCompanies("SÜRAT KARGO")
        };

        cargoCompaniesList = Arrays.asList(companies);


        myrecyclerview_company = findViewById(R.id.companies_view);
        toolbar_selector = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar_selector);
        getSupportActionBar().setTitle(null);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        myrecyclerview_company.setLayoutManager(layoutManager);
        cargoCompanyAdapter = new CargoCompanyAdapter(this, cargoCompaniesList);
        myrecyclerview_company.setAdapter(cargoCompanyAdapter);

        System.gc();
    }

    public void updateUI(){

    }

    @Override
    public void onCompanyClick(int index) {
      //  Intent intent = new Intent(CompanySelectActivity.this, CompanySelectActivity.class);
     //   startActivity(intent);
        showAddDialog(index);
    }

    //kargo numarasını alma diyaloğunu açar
    private void showAddDialog(int index) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CompanySelectActivity.this);

        LayoutInflater inf = getLayoutInflater();

        final View dialogView = inf.inflate(R.layout.add_dialog, null);

        dialogBuilder.setView(dialogView);

        final EditText trackingNo = dialogView.findViewById(R.id.trackingNo_button);
        final Button sorgula = dialogView.findViewById(R.id.sorgula_button);
        final TextView companyName = dialogView.findViewById(R.id.company_name_dialog);


        dialogBuilder.setTitle("Durum Sorgula");

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        companyName.setText(cargoCompaniesList.get(index).getCompanyName());

        sorgula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trackingNo.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Takip Numarası Giriniz.", Toast.LENGTH_SHORT).show();
                }
                else{
                    alertDialog.dismiss();
                    Intent intent = new Intent(CompanySelectActivity.this, GetData.class);
                    intent.putExtra("TRACKNO" , trackingNo.getText().toString());
                    startActivity(intent);
                    CompanySelectActivity.this.finish();
                }
            }
        });
    }

}
