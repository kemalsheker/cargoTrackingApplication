package com.example.drn20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CargoCompanyAdapter extends RecyclerView.Adapter<CargoCompanyAdapter.MyViewHolder> {

    private EventHandler eventHandler;
    private List<CargoCompanies> companiesList;

    public CargoCompanyAdapter(EventHandler eventHandler, List<CargoCompanies> cargoCompanies) {
        this.eventHandler = eventHandler;
        this.companiesList = cargoCompanies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.company_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final CargoCompanies companies = companiesList.get(i);


       // myViewHolder.logo.setImageAlpha(companiesList.get(i).getLogoImage());
        myViewHolder.company_name.setText(String.valueOf(companiesList.get(i).getCompanyName()));

        final int j = i;
        myViewHolder.rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventHandler.onCompanyClick(j);
            }
        });
    }

    @Override
    public int getItemCount() {
        return companiesList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private View rowView;
      //  private ImageView logo;
        private TextView company_name;

        MyViewHolder(View rowView) {
            super(rowView);
            this.rowView = rowView;
        //    logo = rowView.findViewById(R.id.company_logo);
            company_name = rowView.findViewById(R.id.company_name);
        }
    }

    interface EventHandler{
        void onCompanyClick(int index);
    }
}
